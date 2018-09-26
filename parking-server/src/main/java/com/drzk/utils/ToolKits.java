
package com.drzk.utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.sun.jna.Pointer;

/**
 * ClassName:ToolKits <br>
 * Date: 2018年5月25日 下午2:35:08 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class ToolKits {
	public static void savePicture(Pointer pBuf, int dwBufOffset, int dwBufSize, String sDstFile) {

		try {
			File path = new File(sDstFile);
			if (!path.exists()) {
				path.createNewFile();
			}
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(sDstFile)));
			out.write(pBuf.getByteArray(dwBufOffset, dwBufSize), 0, dwBufSize);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
