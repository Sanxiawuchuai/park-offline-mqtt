
package com.drzk.common;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.drzk.utils.JsonUtil;



/**
 * ClassName:StringUtilsTest <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年5月29日 下午5:48:43 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class StringUtilsTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		/**
		 * 
		 
		int temp = 5/0;
		System.out.println(temp);
		
		//byte test[] = {};
		//System.out.println(CommonUtils.bytesToHex(test));
		//System.out.println(new String(test,"utf-8"));
		//String te = "你";
		//System.out.println(CommonUtils.bytesToHex(te.getBytes("gb2312")));
		//E4BDA0  C4E3
		String carNo = "NOP12345";
		System.out.println(carNo.substring(0,3).equals("NOP"));
		//ProcessStatus s = ProcessStatus.FLAG_DEFAULT;
		Properties test = new Properties();
		test.setProperty("w",carNo);
		test.setProperty("y",carNo);
		String charge = JsonUtil.objectToJsonStr(test);
		System.out.println(charge);
		String json = "{\"w\":\"NOP12345\",\"y\":\"NOP12345\",\"z\":\"NOP12345\"}";	
		Properties vo = JsonUtil.jsonStrToObject(json, Properties.class);
		System.out.println(vo.get("w"));
		*/
		Properties properties = new Properties();
		properties.setProperty("test","1");
		System.out.println(properties.getProperty("test"));
		properties.setProperty("test", "2");
		System.out.println(properties.getProperty("test"));
		
	}
		
}
