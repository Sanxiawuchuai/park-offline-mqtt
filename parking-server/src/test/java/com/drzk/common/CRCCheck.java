package com.drzk.common;


public class CRCCheck {
	
	public static void main(String[] args){
		String str="Java生成CRC16数据校验码";
		byte[] test = {(byte) 0xFF, (byte)0xFE ,(byte)0xD0, (byte)0x0C, (byte)0xAD, (byte)0x88, (byte)0x36 ,
				(byte)0x01 ,(byte)0x94, (byte)0x00, (byte)0x00, 
				(byte)0x00 ,(byte)0x00, (byte)0x00};
				//(byte)0x6E ,(byte)0x53 ,(byte)0x01 ,(byte)0x67,
				//(byte)0xAB ,(byte)0xAA
		byte[] data=str.getBytes();
		System.out.println(CRCCheck.Make_CRC(test)); //e13b
	}
	/**
     * 计算产生校验码
     * @param data 需要校验的数据
     * @return 校验码
     */
    public static String Make_CRC(byte[] data) {
        byte[] buf = new byte[data.length];// 存储需要产生校验码的数据
        for (int i = 0; i < data.length; i++) {
            buf[i] = data[i];
        }
        int len = buf.length;
        int crc = 0xFFFF;//16位
        for (int pos = 0; pos < len; pos++) {
            if (buf[pos] < 0) {
                crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
                                                // crc
            } else {
                crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
            }
        }
        String c = Integer.toHexString(crc);
        if (c.length() == 4) {
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 3) {
            c = "0" + c;
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 2) {
            c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
        }
        return c;
    }
}
