package com.skpl.mailapp.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author maple
 */
public class Md5Util {

    public static String md5(String plainText)  {
        String md5Hex = DigestUtils.md5Hex(plainText).toUpperCase();
        return md5Hex;
    }

}
