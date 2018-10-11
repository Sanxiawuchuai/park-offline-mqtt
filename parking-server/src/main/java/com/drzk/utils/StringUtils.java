package com.drzk.utils;

import com.drzk.common.AESUtil;
import com.drzk.online.constant.ConstantUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * 工具类
 * 
 * @author Administrator
 * @since 2018-05-09
 */
public class StringUtils {
	/**
	 * 判断是否为空
	 * 
	 * @param characters
	 * @return
	 */
	public static boolean isNullOrEempty(String characters) {
		return "".equals(characters) || null == characters;

	}

	public static int getDistance(String strA, String strB) {
		int distance = -1;
		/* 输入参数合法性检查 */
		if (null == strA || null == strB || strA.isEmpty() || strB.isEmpty()) {
			return distance;
		}
		/* 两个字符串相等，编辑距离为0 */
		if (strA.equals(strB)) {
			return 0;
		}
		int lengthA = strA.length();
		int lengthB = strB.length();
		int length = Math.max(lengthA, lengthB);
		/* 申请一个二维数组，存储转移矩阵 */
		int array[][] = new int[length + 1][length + 1];
		/* 边界条件初始化 */
		for (int i = 0; i <= length; i++) {
			array[i][0] = i;

		}
		/* 边界条件初始化 */
		for (int j = 0; j <= length; j++) {
			array[0][j] = j;
		}
		/* 状态转移方程 */
		for (int i = 1; i <= lengthA; i++) {
			for (int j = 1; j <= lengthB; j++) {
				array[i][j] = min(array[i - 1][j] + 1, array[i][j - 1] + 1,
						array[i - 1][j - 1] + (strA.charAt(i - 1) == strB.charAt(j - 1) ? 0 : 1));
			}
		}
		return array[lengthA][lengthB];

	}

	/* 取三个数中的最小值 */
	public static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

    /**
     * base64数据加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        if (isNullOrEempty(str)) return "";
        str = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        return str;
    }

    /**
     * base64数据解密
     *
     * @param str
     * @return
     */
    public static String dencode(String str) {
        if (isNullOrEempty(str)) return "";
        str = new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8 );
        return str;
    }

	/**
	 * Map转成实体对象
	 * @param map map实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void main(String[] args) {
		String expirtDate=DateTimeUtils.formatDate(new Date(1570696101205L),"yyyyMMddHHmmss");
		String license= "51060400FFFBEBBF"+"00:E0:4C:65:43:53" +expirtDate;
		String ss=AESUtil.encrypt(license,ConstantUtil.PUBLIC_KEY);
		System.out.println(ss);

	}
}