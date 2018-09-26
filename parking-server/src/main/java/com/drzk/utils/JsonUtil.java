package com.drzk.utils;

import com.drzk.online.vo.ReplayVO;
import com.drzk.service.entity.MainBoardMessage;
import net.sf.ezmorph.ObjectMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * Created by drzk on 2018/06/11.
 */
public class JsonUtil {

	private static Logger logger = Logger.getLogger("userLog");
	
	public static JsonConfig jsonConfig = new JsonConfig();
	static {
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONUtils.getMorpherRegistry().registerMorpher(new UtilDateMorpher(), true);
	}

	/**
	 * 将 Array,list,set 解析成 Json 串
	 * 
	 * @return Json 串
	 */
	public static String arrayToJsonStr(Object objs) {
		JSONArray json = JSONArray.fromObject(objs, jsonConfig);
		return json.toString();
	}

	/***
	 * 将javabean对象或者map对象 解析成 Json 串,使用JsonConfig 过滤属性
	 * 
	 * @param obj
	 * @param
	 * @return
	 */
	public static String objectToJsonStr(Object obj) {
		try {
			JSONObject json = JSONObject.fromObject(obj, jsonConfig);
			return json.toString();
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
			return null;
		}

	}

	/**
	 * 将 Json 串 解析成 Array,list,set
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> jsonStrToArray(String jsonStr) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Object array = JSONArray.toArray(jsonArray);
		return (Collection<T>) array;
	}

	/**
	 * 将 Json 串 解析成 Array
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] jsonStrToArray(String jsonStr, Class<T> clazz) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr, jsonConfig);
		return (T[]) JSONArray.toArray(jsonArray, clazz);
	}

	/**
	 * 将 Json 串 解析成 Collection
	 * 
	 * @return
	 */
	public static <T> Collection<T> jsonStrToCollection(String jsonStr, Class<T> clazz) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr, jsonConfig);
		@SuppressWarnings("unchecked")
		Collection<T> array = JSONArray.toCollection(jsonArray, clazz);
		return array;
	}

	/**
	 * 将 Json 串 解析成 list
	 * 
	 * @return
	 */
	public static <T> List<T> jsonStrToList(String jsonStr, Class<T> clazz) {
		return (List<T>) jsonStrToCollection(jsonStr, clazz);
	}

	/**
	 * 将 Json 串 解析成 Map或者javabean
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonStrToObject(String jsonStr, Class<T> clazz) {
		JSONObject json = JSONObject.fromObject(jsonStr, jsonConfig);
		// Object obj = JSONObject.toBean(json, jsonConfig);
		Object obj = JSONObject.toBean(json, clazz);
		return (T) obj;
	}

	public static <H, B> MainBoardMessage<H, B> jsonToBoardMessage(String jsonStr, Class<H> head, Class<B> body) {
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("head", head);
		classMap.put("body", body);
		JSONObject jsonObject = JSONObject.fromObject(jsonStr,jsonConfig);
		MainBoardMessage<H, B> obj = (MainBoardMessage<H, B>) JSONObject.toBean(jsonObject, MainBoardMessage.class,
				classMap);
		return obj;
	}
	
	public static <H,B> ReplayVO<H, B> jsonToReplayVO(String jsonStr, Class<H> head, Class<B> body){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr,jsonConfig);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("head", head);
		classMap.put("body", body);
		ReplayVO<H, B> obj = (ReplayVO<H, B>) JSONObject.toBean(jsonObject, ReplayVO.class,
				classMap);
		return obj;
	}

	/**
	 *  根据json中获取body中的method字段<br>
	 *
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public static  String getMethodByJsonStr(String jsonStr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONObject jsonHead = JSONObject.fromObject(jsonObj.getString("head"));
		String method = jsonHead.getString("method");
		return  method;
	}
	
	/**
	 *  根据json中获取body中的uId字段<br>
	 *
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public static String getUidByJsonStr(String jsonStr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONObject jsonHead = JSONObject.fromObject(jsonObj.getString("body"));
		String uId = jsonHead.getString("uId");
		return  uId;
	}

	/**
	 *  根据json中获取body中的status字段<br>
	 *
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public static String getStatusByJsonStr(String jsonStr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONObject jsonHead = JSONObject.fromObject(jsonObj.getString("head"));
		String status = jsonHead.getString("status");
		return  status;
	}

	private static class JsonDateValueProcessor implements JsonValueProcessor {

		// 定义转换日期类型的输出格式
		private String format = "yyyy-MM-dd HH:mm:ss";

		public JsonDateValueProcessor() {

		}

		public JsonDateValueProcessor(String format) {
			this.format = format;
		}

		@Override
		public Object processArrayValue(Object arg0, JsonConfig arg1) {
			return process(arg0);
		}

		private Object process(Object arg0) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(arg0);
		}

		@Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			if (value instanceof java.util.Date) {
				String str = new SimpleDateFormat(format).format((Date) value);
				return str;
			}
			if (null != value) {
				return value.toString();
			}
			return "";
		}
	}

	private static class UtilDateMorpher implements ObjectMorpher {
		private String format = "yyyy-MM-dd HH:mm:ss";

		/**
		 * json转换成java object
		 * 
		 * @param value
		 *            json字符串
		 * 
		 */
		@Override
		public Object morph(Object value) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			if (org.springframework.util.StringUtils.isEmpty(value)) {
				return null;
			}
			try {
				return sf.parse((String) value);
			} catch (ParseException e) {
				//e.printStackTrace();
				return null;
			}
		}

		/**
		 * 对哪种java对象进行解析
		 */
		@Override
		public Class morphsTo() {
			return Date.class;
		}

		/**
		 * 支持那种clazz类型的解析
		 */
		@Override
		public boolean supports(Class clazz) {
            return clazz == String.class;
        }

	}

}
