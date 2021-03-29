package com.smart119;


import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class ChinaUnicomApiTest {

    private static String url = "";

    /**
     * 状态码 - 查询成功
     */
    static String respCode_OK = "0";


    private static String getSendXmlString(){

        String theClient = "theClient"; //LCS Client(客户端)的标识
        String ThePass = "ThePass"; //LCS Client 接入密码
        String ORID = "13300000001"; //发起定位请求的用户标识
        String ORID_TYPE = "0"; //号码发起定位请求的用户标识类型 0-表示ID类型为MDN
        String ORIGUSER_ACCESSTYPE = "1"; //Originator的接入类型 0 － 保留     1 — 表示WAP方式接入    2 － 表示SMS方式接入      3 － 表示网站侧接入
        String FINDME_INDIC = "1"; //这里1表示自我位置查询——【注释：该单元的数值、个数和顺序应与<MSID>一一对应
        String MSID = "13300000001"; //移动台标识
        String MSID_TYPE = "0"; //移动台标识类型 这里0表示MDN号码
        String QUERYPASSWORD = "888888";//第三方查询密码
        String POSREQTYPE = "1"; //位置请求类型 1：初始位置；2：最新位置；3：最新或最近已知位置   这里1表示取初始位置信息【注释：1表示初始位置，2表示最新位置,0暂时不用，见PN4747】
        String COORD_SYS = "LL";//用于定义在位置答复中使用哪个坐标系统  LL(latitude&longitude)-经度纬度格式
        String DATUM = "WGS-84";//用于定义大地测量数据  WGS-84-全球大地测量系统 1984
        /**
         * 经纬度的表示单位
         * 定义值：	 [I]?[D | DM | DMS | M | MS | S] [0-9] [I]?
         * D——Degrees only——45.403
         * DM——Degrees and minutes——4515.557
         * DMS——Degrees, minutes and seconds——451533.431
         * M——Minutes only——16215.557
         * MS——Minutes and seconds——1621533.431
         * S——Seconds only——972933.431
         * I——Output direction indicator
         * 该指示有两个正确的位置——在字符串的开头或末尾。
         * 如果出现方向标指示（direction indicator），则 (N | S | E | W)应加在输出中——45.403W， N16215.557
         * 数字[0-9]定义了小数的精度。小数末尾中值是0的数应被删除。
         */
        String LL_FORMAT = "DMS3";
        /**
         * 定义响应时间要求
         * NO_DELAY	    无延迟：返回用户的初始位置或最后已知位置。
         * LOW_DELAY	低延迟：以最低延迟返回当前位置。移动服务应该尽量满足任何准确定要求，但不应该增加延迟。
         * DELAY_TOL	延迟容忍度：获得满足准确定要求的当前位置
         * 这里0表示NO_DELAY
         */
        String RESP_REQ = "0";
        /**
         * 为响应定义时间要求，在这段时间内，必须获得当前位置并返回到LCS客户机。
         * 如果定的时间太短，LCS将接收到MPC发出的 RESPONSE_TIMEOUT 错误消息。
         * 因此，这个值应该足够大，使MPC能找到MS的位置并将结果返回到LCS客户机。
         * 建议最小值为0010，最大值为0030。 格式为：mmss
         */
        String RESP_TIMER = "0010";
        /**
         * 请求的位置信息的水平准确度（米）
         * 在CDMA系统的定位中，大于300米表示基于CELL ID的定位方式，小于等于300米表示混合定位（AGPS和AFLT）
         */
        String HOR_ACC = "200";
        /**
         * 请求的位置信息的高度准确度（米）
         */
        String ALT_ACC = "200";
        /**
         * 定义位置请求的优先级
         * 0: NORMAL	以普通优先级处理请求
         * 1: HIGH	以高优先级处理请求
         */
        String PRIO = "1";

        Document document = DocumentHelper.createDocument();

        Element REQ = document.addElement("REQ");// 创建根节点

        Element CLIENT = REQ.addElement("CLIENT");
        CLIENT.addElement("LCSCLIENTID").addText(theClient);
        CLIENT.addElement("PASSWORD").addText(ThePass);

        Element ORIGINATOR = REQ.addElement("ORIGINATOR");
        ORIGINATOR.addElement("ORID").addText(ORID);
        ORIGINATOR.addElement("ORID_TYPE").addText(ORID_TYPE);

        Element LIR = REQ.addElement("LIR");

        LIR.addElement("ORIGUSER_ACCESSTYPE").addText(ORIGUSER_ACCESSTYPE);
        LIR.addElement("FINDME_INDIC").addText(FINDME_INDIC);

        Element MSIDS = LIR.addElement("MSIDS");
        MSIDS.addElement("MSID").addText(MSID);
        MSIDS.addElement("MSID_TYPE").addText(MSID_TYPE);
        MSIDS.addElement("QUERYPASSWORD").addText(QUERYPASSWORD);

        LIR.addElement("POSREQTYPE").addText(POSREQTYPE);

        Element GEO_INFO = LIR.addElement("GEO_INFO");
        GEO_INFO.addElement("COORD_SYS").addText(COORD_SYS);
        GEO_INFO.addElement("DATUM").addText(DATUM);
        GEO_INFO.addElement("LL_FORMAT").addText(LL_FORMAT);

        Element PQOS = LIR.addElement("PQOS");
        PQOS.addElement("RESP_REQ").addText(RESP_REQ);
        PQOS.addElement("RESP_TIMER").addText(RESP_TIMER);
        PQOS.addElement("HOR_ACC").addText(HOR_ACC);
        PQOS.addElement("ALT_ACC").addText(ALT_ACC);

        LIR.addElement("PRIO").addText(PRIO);

        return document.asXML();

    }

    public static void main(String[] args) throws DocumentException {

        /*String xmlStr = getSendXmlString();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> formEntity = new HttpEntity<>(xmlStr, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, formEntity, String.class);

        Document resultDocument = DocumentHelper.parseText(responseEntity.getBody());*/

        Document resultDocument = load("D:\\idea_workspace\\smart119_bms_backend\\src\\test\\java\\com\\smart119\\ChinaUnicomApiResultTest.xml");

        String RESULT = resultDocument.getRootElement().element("LIA").elementText("RESULT");
        if(!StringUtils.equals(RESULT,respCode_OK)){
            System.out.println("请求错误");
        }

        Element POSINFO = resultDocument.getRootElement().element("LIA").element("POSINFOS").element("POSINFO");
        //1表示取到了移动台的初始位置
        String POSITIONRESULT = POSINFO.elementText("POSITIONRESULT");
        if(!StringUtils.equals(POSITIONRESULT,"1")){
            System.out.println("没有取到移动台的初始位置");
        }
        //0:南纬；1:北纬 北纬为正数，南纬为负数。
        String LATITUDETYPE = POSINFO.elementText("LATITUDETYPE");
        //纬度
        String LATITUDE = POSINFO.elementText("LATITUDE");
        Double latitude = Double.parseDouble(LATITUDE);
        if(StringUtils.equals(LATITUDETYPE,"0")){
            latitude =  - latitude;
        }

        //0:东经，1:西经  东经正数，西经为负数
        String LONGITUDETYPE = POSINFO.elementText("LONGITUDETYPE");
        //经度
        String LONGITUDE = POSINFO.elementText("LONGITUDE");
        Double longitude = Double.parseDouble(LONGITUDE);
        if(StringUtils.equals(LONGITUDETYPE,"1")){
            longitude = - longitude;
        }

        System.out.println("经度："+ longitude + "     纬度：" + latitude);




    }

    public static Document load(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }
}
