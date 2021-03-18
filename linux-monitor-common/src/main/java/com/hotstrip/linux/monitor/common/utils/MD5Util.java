package com.hotstrip.linux.monitor.common.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;


/**
 * ClassName:MD5Util <br/>
 * Function: MD5 encode <br/>
 * Date:     2016年8月12日 上午11:51:39 <br/>
 * @author   Hotstrip
 * @version  
 * @see 	 
 */
@Slf4j
public class MD5Util {

    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * byte to String
     * @param bByte
     * @return
     */
    private static String byteToArrayString(final byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // bytes to String
    private static String byteToString(final byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * MD5 encode
     * @param strObj
     * @return encoded string
     */
    @SneakyThrows
    public static String encodeMD5(final String strObj) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        // md.digest() 该函数返回值为存放哈希值结果的 byte 数组
        final String resultString = byteToString(md.digest(strObj.getBytes()));
        return resultString;
    }
}

