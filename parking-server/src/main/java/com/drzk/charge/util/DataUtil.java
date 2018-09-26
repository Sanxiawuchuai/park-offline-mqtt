package com.drzk.charge.util;

/**
 * Created by drzk on 2018/06/20.
 */
public class DataUtil {
    public static boolean retBoolean(byte a){
        boolean b = false;
        try {

            a = (byte) (b ? 0x01 : 0x00);
            b = a == 0x00;

        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return b;
    }
}
