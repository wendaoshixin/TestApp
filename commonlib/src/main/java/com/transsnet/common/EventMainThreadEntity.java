// TOP SECRET
package com.transsnet.common;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 所有的网络请求回调的对象 , 该对象封装了所有的回调参数  。  
 * 
 * @author HJG
 * @date 2016-01-22 下午3:08:45
 * @since 1.0
 * @version 1.0
 * @modify author: 
 * @modify date: 2016-01-22 下午3:08:45
 * @modify content:
 */
public class EventMainThreadEntity {
	
	public static final String def_action = "ABCDEFG67890";
	
	/**
	 * network request action . 
	 */
	private String action = def_action;
	
	/**
	 * request success or failure . 
	 */
	public boolean isSuccess = false;
	
	/**
	 * all the response params map . 
	 */
	private HashMap<String, Object> mapParams = new HashMap<String, Object>();
	
	/**
	 * get action, if isEmpty return default action
	 * @return
	 */
	public String getAction(){
		return TextUtils.isEmpty(action) ? def_action : action;
	}
	
	/**
	 * set Action
	 * @param action
	 */
	public void setAction(String action){
		this.action = action;
	}
	
	public void put(String key, Object value){
		mapParams.put(key, value);
	}
	
	public Object get(String key){
		return mapParams.get(key);
	}
	
	public void putList(String key, List<?> list){
		mapParams.put(key, list);
	}
	
	public List<?> getList(String key){
		return mapParams.containsKey(key) ? mapParams.get(key) == null? null : (List<?>) mapParams.get(key) : null;
	}
	
	public int getInt(String key, int defValue){
		return mapParams.containsKey(key) ? mapParams.get(key)==null ? defValue : (Integer)mapParams.get(key) : defValue;
	}
	
	public String getString(String key){
		return mapParams.containsKey(key) ? mapParams.get(key)==null ? null : (String)mapParams.get(key) : null;
	}
	
	public long getLong(String key, long defValue){
		return mapParams.containsKey(key) ? mapParams.get(key)==null ? defValue : (Long) mapParams.get(key) : defValue;
	}
	
	public boolean getBoolean(String key, boolean defValue){
		return mapParams.containsKey(key) ? mapParams.get(key)==null ? defValue : (Boolean) mapParams.get(key) : defValue;
	}
	
	public boolean containsKey(String key){
		return mapParams.containsKey(key);
	}

	@Override
	public String toString() {
		return "EventMainThreadEntity [action=" + action + ", isSuccess="
				+ isSuccess + ", mapParams=" + mapParams + "]";
	}
}
