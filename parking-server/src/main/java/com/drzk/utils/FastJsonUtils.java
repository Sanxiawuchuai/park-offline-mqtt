
package com.drzk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.drzk.service.entity.MainBoardMessage;

/**
 * 采用阿里的fastjson对json进行转换<br>
 * Date: 2018年6月28日 上午9:01:56 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class FastJsonUtils {

	/**
	 * 
	 * 对象转换成json字符串 <br>
	 *
	 * @author chenlong
	 * @param obj
	 * @return
	 * @since JDK 1.8
	 */
	public static String objectToJsonStr(Object obj) {
		//JSON.toJSONString(obj);
		return JSON.toJSONString(obj);
	}
	
	/**
	 */
	public static <H,B> MainBoardMessage<H,B> jsonStrToObject(String jsonStr,Class<H> head,Class<B> body) {
		MainBoardMessage<H,B> obj = JSONObject.parseObject(jsonStr, new TypeReference<MainBoardMessage<H,B>>(head,body) {
	    });
		return obj;
	}
	
	/**
	private static <T,H,B> Result<List<T>> parseListResult(String json, Class<T> clazz) {
	    return JSONObject.parseObject(json, buildType(Result.class, List.class, Item.class));
	}

	private static Type buildType(Type... types) {
	    ParameterizedTypeImpl beforeType = null;
	    if (types != null && types.length > 0) {
	        for (int i = types.length - 1; i > 0; i--) {
	            beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
	        }
	    }
	    return beforeType;
	}
	*/
	

}
