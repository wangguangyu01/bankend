package com.smart119.common.utils;

import com.alibaba.fastjson.JSONArray;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class regionUtil {
    private static double EARTH_RADIUS = 6378137d;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    /**
     * 判断一个点是否在圆形区域内
     * @param radius 半径
     * @param lat1  圆心纬度
     * @param lng1  圆心经度
     * @param lat2  坐标纬度
     * @param lng2  坐标经度
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, String radius) {
        return getDistance(lat1, lng1, lat2, lng2) > Double.parseDouble(radius);
    }

    /**
     * 判断是否在多边形区域内
     *
     * @param pointLon
     *            要判断的点的纵坐标
     * @param pointLat
     *            要判断的点的横坐标
     * @param lon
     *            区域各顶点的纵坐标数组
     * @param lat
     *            区域各顶点的横坐标数组
     * @return
     */
    public static boolean isInPolygon(double pointLon, double pointLat, JSONArray points) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        double polygonPoint_x = 0.0, polygonPoint_y = 0.0;
        int len = points.size();
        for (int i = 0; i < len; i++) {
            polygonPoint_x = points.getJSONObject(i).getDouble("x");
            polygonPoint_y = points.getJSONObject(i).getDouble("y");
            Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }

    /**
     * 一个点是否在多边形内
     *
     * @param point
     *            要判断的点的横纵坐标
     * @param polygon
     *            组成的顶点坐标集合
     * @return
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();

        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        peneralPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            peneralPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        peneralPath.lineTo(first.x, first.y);
        peneralPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return peneralPath.contains(point);
    }

    public static void main(String[] args) {
        String points = "[{\"sets\":[{\"name\":\"sswg\",\"value\":\"402881f6681232c00168129b896e0078\"}],\"where\":[{\"name\":\"id\",\"value\":3231735,\"type\":\"=\"}]},{\"sets\":[{\"name\":\"sswg\",\"value\":\"000000006817dd6f0168313b9b89049a\"}],\"where\":[{\"name\":\"id\",\"value\":3231735,\"type\":\"=\"}]}]";
        JSONArray pointArray = (JSONArray)JSONArray.parse(points);
        System.out.println(isInPolygon(120.636514,31.341752, pointArray));
//        String points = "[{\"x\":120.61123416,\"y\":31.32889074,\"z\":137.05},{\"x\":120.61312695,\"y\":31.31892631,\"z\":128.61},{\"x\":120.61455616,\"y\":31.30808702,\"z\":43.66},{\"x\":120.62127327,\"y\":31.30899876,\"z\":62.21},{\"x\":120.63003506,\"y\":31.31057071,\"z\":29.43},{\"x\":120.63726235,\"y\":31.31203339,\"z\":92.90},{\"x\":120.64536616,\"y\":31.31334188,\"z\":78.36},{\"x\":120.64402082,\"y\":31.31947999,\"z\":13.19},{\"x\":120.64136126,\"y\":31.32757908,\"z\":87.36},{\"x\":120.63689776,\"y\":31.33287239,\"z\":60.62},{\"x\":120.63502091,\"y\":31.33742080,\"z\":114.21},{\"x\":120.63071787,\"y\":31.33793104,\"z\":32.99},{\"x\":120.62952446,\"y\":31.34483170,\"z\":164.79},{\"x\":120.62710968,\"y\":31.34801804,\"z\":164.15},{\"x\":120.62731359,\"y\":31.34823458,\"z\":189.53},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62665860,\"y\":31.34861797,\"z\":155.41},{\"x\":120.61706620,\"y\":31.34846463,\"z\":200.05},{\"x\":120.61854348,\"y\":31.34267516,\"z\":138.68},{\"x\":120.62111689,\"y\":31.33313042,\"z\":154.61}]";
//        JSONArray pointArray = (JSONArray)JSONArray.parse(points);
//        System.out.println(isInPolygon(120.66615036,31.33525453, pointArray));//不在范围内
//        System.out.println(isInPolygon(120.62171413,31.32044689, pointArray));//在范围内
    }
}
