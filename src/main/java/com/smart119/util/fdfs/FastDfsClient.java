package com.smart119.util.fdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FastDfsClient {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDfsClient.class);
    private static BlockingQueue<TrackerServer> trackerServerPool =
            new LinkedBlockingQueue<>();

    private static final int maxTrackerConn = 200;

    private static AtomicInteger currentTrackerConn = new AtomicInteger(0);

    private static TrackerClient trackerClient = null;
    /*
     * 初始化加载FastDFS的TrackerServer配置
     */
    static {
        try {
            Properties properties = new Properties(); // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = FastDfsClient.class.getClassLoader().getResourceAsStream("fdfs_client.properties");
            properties.load(in);
            ClientGlobal.initByProperties(properties);
            trackerClient = new TrackerClient();
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }
    /***
     * 文件上传
     * @param file
     * @return 1.文件的组名  2.文件的路径信息
     */
    public static String[] upload(FastDfsFile file) {

        //获取文件的作者
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("author", file.getAuthor());
        //接收返回数据
        String[] uploadResults = null;
        StorageClient storageClient=null;
        TrackerServer trackerServer = null;
        try {
            trackerServer= trackerClient.getConnection();
            //创建StorageClient客户端对象
            storageClient = getTrackerClient(trackerServer);
            /*
             * 文件上传
             * 1)文件字节数组
             * 2)文件扩展名
             * 3)其他参数
             */
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), metaList);
        } catch (Exception e) {
            logger.error("Exception when uploadind the file:" + file.getName(), e);
        }finally {
            closeTrackerServer(trackerServer);
        }

        if (uploadResults == null && storageClient!=null) {
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }
        return uploadResults;
    }
    /***
     * 获取文件信息
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        TrackerServer trackerServer =null;
        try {
             trackerServer= trackerClient.getConnection();
            StorageClient storageClient = getTrackerClient(trackerServer);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("Exception: Get File from Fast DFS failed", e);
        }finally {
            closeTrackerServer(trackerServer);
        }
        return null;
    }
    /***
     * 文件下载
     * @return
     */
    public static InputStream downFile(String fileUrl) {
        TrackerServer trackerServer =null;
        try {
             trackerServer= trackerClient.getConnection();
            //创建StorageClient
            StorageClient storageClient = getTrackerClient(trackerServer);

            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            //下载文件
            byte[] fileByte = storageClient.download_file(storePath.getGroup(), storePath.getPath());
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;


        } catch (Exception e) {
            logger.error("Exception: Get File from Fast DFS failed", e);
        }finally {
            closeTrackerServer(trackerServer);
        }
        return null;
    }
    /***
     * 文件删除
     * @param fileUrl
     * @throws Exception
     */
    public static int deleteFile(String fileUrl)
            throws Exception {
        TrackerServer trackerServer= trackerClient.getConnection();
        //创建StorageClient
        StorageClient storageClient = getTrackerClient(trackerServer);
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        //删除文件
        int i = storageClient.delete_file(storePath.getGroup(), storePath.getPath());
        return i;
    }
    /***
     * 获取Storage组
     * @param groupName
     * @return
     * @throws IOException
     */
    public static StorageServer[] getStoreStorages(String groupName)
            throws IOException {
        //创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //获取TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage组
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }
    /***
     * 获取Storage信息,IP和端口
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getFetchStorages(String groupName,
                                                String remoteFileName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }
    /***
     * 获取Tracker服务地址
     * @return
     * @throws IOException
     */
    public static String getTrackerUrl() throws IOException, InterruptedException {
        return "http://"+getTrackerServer().getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
    }
    /***
     * 获取Storage客户端
     * @return
     * @throws IOException
     */
    private static StorageClient getTrackerClient(TrackerServer trackerServer ) throws IOException, InterruptedException {
       // TrackerServer trackerServer = getTrackerServer();
        return new StorageClient(trackerServer, null);
    }
    /***
     * 获取Tracker
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException, InterruptedException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getConnection();
    }
    /***
     * 文件上传
     * @author yangj
     * @param fileByte 文件二进制
     * @param ext 文件扩展名只需传名称即可不需要.
     * @return /文件组名/路径信息.扩展名
     */
    public static String uploadFileByte(byte[] fileByte,String ext){
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("author", "yangj");
        String[] uploadResults = null;
        StorageClient storageClient=null;
        TrackerServer  trackerServer=null;
        try {
             trackerServer= trackerClient.getConnection();
            //创建StorageClient客户端对象
            storageClient = getTrackerClient(trackerServer);
            /*
             * 文件上传
             * 1)文件字节数组
             * 2)文件扩展名
             * 3)其他参数
             */
            uploadResults = storageClient.upload_file(fileByte, ext, metaList);
        } catch (Exception e) {
            logger.error("文件上传失败", e);
        }finally {
            closeTrackerServer(trackerServer);
        }
        if (uploadResults == null && storageClient!=null) {
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }
        return uploadResults[0]+"/"+uploadResults[1];
    }
    public static String getToken(String fid, String secret_key, String IP){

        String substring = fid.substring(fid.indexOf("/")+1);
        //unix时间戳 以秒为单位
        int ts = (int) (System.currentTimeMillis() / 1000);
        String token=new String();
        try {
            token= ProtoCommon.getToken(substring, ts, secret_key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(IP);
        sb.append(fid);
        sb.append("?token=").append(token);
        sb.append("&ts=").append(ts);
        return sb.toString();
    }
    private static void closeTrackerServer(TrackerServer trackerServer) {
        try {
            if (trackerServer != null) {
                logger.info("关闭trackerServer连接");
                trackerServer.close();
            }
        } catch (IOException e) {
            logger.error("error", e);
        }
    }
}