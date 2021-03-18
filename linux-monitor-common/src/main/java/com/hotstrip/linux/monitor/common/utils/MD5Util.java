/**
 * Package Name:com.idiot.mgadmin.common.utils
 * Date:2016年8月12日上午11:51:39
 * Copyright (c) 2016, www.chaincar.com All Rights Reserved.
 */
package com.seestech.sell.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * ClassName:EncodeMD5 <br/>
 * Function: MD5加密 <br/>
 * Date:     2016年8月12日 上午11:51:39 <br/>
 * @author   idiot
 * @version  
 * @see 	 
 */
public class EncodeMD5 {
    private static Logger logger = LoggerFactory.getLogger(EncodeMD5.class);

	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return resultString;
    }

    public static void main(String[] args) {
    	//随机密码生成
    	Random rand = new Random();
    	StringBuffer str = new StringBuffer("");
    	for(int i=0; i<6; i++){
    		int x = rand.nextInt(16);
    		str.append(strDigits[x]);
    	}
    	System.out.println("随机密码【未加密】=="+str.toString());
    	System.out.println("随机密码【加密】=="+ EncodeMD5.GetMD5Code(str.toString()));
        System.out.println(EncodeMD5.GetMD5Code("123456"));
    }
}

