package com.smart119.util;

import com.smart119.common.utils.MD5Utils;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class MD5Util {



    public static String md5Encode(String content){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            return (Hex.encodeHexString(messageDigest.digest())).toLowerCase();
        }catch(Exception e){
            return "";
        }
    }

    public static String md5EncodetoUpperCase(String content){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            return (Hex.encodeHexString(messageDigest.digest())).toUpperCase();
        }catch(Exception e){
            return "";
        }
    }

}
