package com.hzxy.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class JsonUtil {

    /**
     * 对象格式化为 json
     * @param object
     * @return
     */
    public static String toJson(Object object){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);

    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * @param jsonString
     * @param pojoCalss
     * @param classMap
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class pojoCalss,HashMap classMap){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject,pojoCalss,classMap);
        return pojo;
    }

    /**
     * 从一个JSON 对象字符格式中得到一个MorphDynaBean对象
     * @param jsonString
     * @return
     */
    public static Object getObject4JsonString(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return jsonObject.toBean(jsonObject);
    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * @param jsonString
     * @return
     */
    public static Map getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString );
        Iterator  keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }


    /**
     * 从json数组中得到相应java数组
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();

    }


    /** *//**
     * 从json对象集合表达式中得到一个java对象列表
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List getList4Json(String jsonString, Class pojoClass){

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for ( int i = 0 ; i<jsonArray.size(); i++){

            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);

        }
        return list;

    }

    /**
     * 从json数组中解析出java字符串数组
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);

        }

        return stringArray;
    }





    /**
     * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
     * @param jsonString
     * @return
     */

   /* public static Date[] getDateArray4Json(String jsonString,String DataFormat){

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;

        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            dateString = jsonArray.getString(i);
            date = DateUtil.stringToDate(dateString, DataFormat);
            dateArray[i] = date;

        }
        return dateArray;
    }*/




    /**
     * 将java对象转换成json字符串
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
    }

    /**
     * 将List转换成json字符串
     * @param list
     * @return
     */
    public static String getJsonString4List(List list){
    	if(null==list){
    		list = new ArrayList();
    	}
    	JSONArray json;
    	json = JSONArray.fromObject(list);
    	return json.toString();
    }

    /**
     * 将List转换成json字符串
     * @return
     */
    public static String getJsonString4Map(Map map){
    	JSONArray json;
    	json = JSONArray.fromObject(map);
    	return json.toString();
    }

    /**
     * 将JSONObject对象转为Map对象
     * @param obj
     * @return
     */
    public static Map getMap4JSONObject(Object obj){
    	Map map = new HashMap();
    	JSONObject json = JSONObject.fromObject(obj);
    	Iterator keys=json.keys();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, getMap4JSONObject(JSONObject.fromObject(value)));
			}else if(value.startsWith("[")&&value.endsWith("]")){
				List vs = new ArrayList();
				Iterator vit = JsonUtil.getList4Json(value, Map.class).iterator();
				while(vit.hasNext()){
					vs.add(JsonUtil.getMap4JSONObject(vit.next()));
				}
				map.put(key, vs);
			}else{
				map.put(key, value);
			}
		}
		return map;
    }
}



