package com.aoty.matinalnew.api;

/**
 * Created by AotY on 2014/12/23.
 * <p/>
 * 用于存放请求服务器的api接口
 */
public class MatinalApi {


    //模拟器访问本电脑用的ip是10.0.2.2:8080 而不是localhost
//    public static final String HOST = "http://10.0.2.2/:8080/NewSystem/test.jsp";
    public static final String HOST = "http://192.168.114.1:8080/NewSystem/test.jsp";
    /*
    获取不同频道的新闻列表
     */
    public static final String GETHEADLINEURL = HOST + "?type=0";
    public static final String GETSPORTURL = HOST + "?type=1";
    public static final String GETTECHNOLOGYURL = HOST + "?type=2";
    public static final String GETECONOMICURL = HOST + "?type=3";
    public static final String GETENTERTAINMENTURL = HOST + "?type=4";
    public static final String GETGUIYANGURL = HOST + "?type=5";

}
