package com.example.demo.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/***
 * 获取redis数据
 * 
 * @author ytj
 *
 */
public interface RedisService {
	
	/***
	 * 设置redisTemplate过期时间(分钟)
	 * 
	 * @param key
	 * @param expiraTime
	 * @return
	 */
	public void setRedisTemplateKeyExpirationTimeByMinute(String key, long expiraTime);
	
	/***
	 * 设置redis的过期时间
	 * 
	 * @param key
	 * @param timeUnit
	 * @param expiraTime
	 */
	public void setRedisTemplateKeyExpirationTime(String key, TimeUnit timeUnit, long expiraTime);
	
	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param date
	 */
	public void setRedisTemplateKeyExpirationDate(String key, Date date);
	
	/***
	 * 获取key值下面的属性数据信息
	 * 用于获取存放数值为int类型的额数据
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public int getRedisIntValueByKey(String key, String field);
	
	/**
	 * 根据key值获取属性数据
	 * field属性的数据值为object类型
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Object getRedisObjValueByKey(String key, String field);
	
	/**
	 * 获得Redis中存储数据对象为obj的数据大小(如果获取数据异常则返回0)
	 * 
	 * @param key
	 * @return
	 */
	public long getCountForObjSize(String key);
	
	/**
	 * 判断key值是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExistKey(String key);
	
	/**
	 * 设置数据值
	 * object类型
	 * 
	 * @param key
	 * @param field
	 * @param countValue
	 */
	public void setRedisHashValueByKey(String key, String field, Object objValue);
	
	/***
	 * 设置数据值
	 * String类型
	 * 
	 * @param key
	 * @param field
	 * @param objValue
	 */
	public String setRedisStringValueByKey(String key, String strValue);
	
	/***
	 * 获取字符串数据(返回值为JSON格式)
	 * ret_code:0未出现异常
	 * ret_msg:返回结果
	 * 
	 * @param key
	 * @return
	 */
	public String getRedisStringValueByKey(String key);
	
	/***
	 *  移除掉字符数据
	 * 
	 * @param key
	 */
	public void removeRedisStringValueByKey(String key);
	
	/**
	 * 批量移除数据
	 * 
	 * @param keyList
	 */
	public void removeRedisStringValueByKey(List<String> keyList);
	
	/***
	 * 删除Redis中的记录
	 * 
	 * @param key
	 * @param field
	 */
	public void removeRedisObjByKey(String key, String field);
	
	/***
	 * 获取存放的map对象，Redis中数据以map的形式存放
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Map<String, Object> getRedisMapObjByKey(String key, String field);
	
	/***
	 * 根据key值获得列表
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public List<Map<String, Object>> getRedisMapListByKey(String key, String field);
	
	/**
	 * Redis中对象是object对象,传递进来的参数map
	 * 
	 * @param key
	 * @param mapObj
	 */
	public void setRedisObjValueForMapByKey(String key, Map<String, Object> mapObj);
	
	/**
	 * 获取Redis中对象是object对象,传递进来的参数map
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, Object> getRedisObjValueForMapByKey(String key);
	
	/**
	 * 删除Redis中对象是object对象
	 * 
	 * @param key
	 */
	public void removeRedisObjByKey(String key);
	
}
