package com.drzk.utils;

import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * ClassName:JNAUtils <br>
 * Date: 2018年5月18日 上午9:33:50 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class JNAUtils {

	public static void GetPointerData(Pointer pNativeData, Structure pJavaStu) {
		GetPointerDataToStruct(pNativeData, 0, pJavaStu);
	}

	public static void GetPointerDataToStruct(Pointer pNativeData, long OffsetOfpNativeData, Structure pJavaStu) {
		pJavaStu.write();
		Pointer pJavaMem = pJavaStu.getPointer();
		pJavaMem.write(0, pNativeData.getByteArray(OffsetOfpNativeData, pJavaStu.size()), 0, pJavaStu.size());
		pJavaStu.read();
	}

	// 获取操作平台信息
	public static String getOsPrefix() {
		String arch = System.getProperty("os.arch").toLowerCase();
		final String name = System.getProperty("os.name");
		String osPrefix;
		switch (Platform.getOSType()) {
		case Platform.WINDOWS: {
			if ("i386".equals(arch))
				arch = "x86";
			osPrefix = "win32-" + arch;
		}
			break;
		case Platform.LINUX: {
			if ("x86".equals(arch)) {
				arch = "i386";
			} else if ("x86_64".equals(arch)) {
				arch = "amd64";
			}
			osPrefix = "linux-" + arch;
		}
			break;
		default: {
			osPrefix = name.toLowerCase();
			if ("x86".equals(arch)) {
				arch = "i386";
			}
			if ("x86_64".equals(arch)) {
				arch = "amd64";
			}
			int space = osPrefix.indexOf(" ");
			if (space != -1) {
				osPrefix = osPrefix.substring(0, space);
			}
			osPrefix += "-" + arch;
		}
			break;

		}

		return osPrefix;
	}

	public static String getOsName() {
		String osName = "";
		String osPrefix = getOsPrefix();
		if (osPrefix.toLowerCase().startsWith("win32-x86") || osPrefix.toLowerCase().startsWith("win32-amd64")) {
			osName = "win";
		} else if (osPrefix.toLowerCase().startsWith("linux-i386")
				|| osPrefix.toLowerCase().startsWith("linux-amd64")) {
			osName = "linux";
		}

		return osName;
	}

	// 获取加载库
	public static String getLoadLibrary(String library) {
		String loadLibrary = "";
		String osPrefix = getOsPrefix();
		if (osPrefix.toLowerCase().startsWith("win32-x86")) {
			//loadLibrary = "./libs/win32-x86/";
		} else if (osPrefix.toLowerCase().startsWith("win32-amd64")) {
			//loadLibrary = "";
			loadLibrary = "camera/dahua/win64/"; //windows 64位的目录
		} else if (osPrefix.toLowerCase().startsWith("linux-i386")) {
			loadLibrary = "";
		} else if (osPrefix.toLowerCase().startsWith("linux-amd64")) {
			//loadLibrary = "camera/dahua/linux64/";
			//library = "lib"+library;
		}

		return loadLibrary + library;
	}
	
	public static void main(String[] args) {
		System.out.println(getOsPrefix());
		System.out.println(getOsName());
		System.out.println(getLoadLibrary("dhnetsdk"));
	}

}
