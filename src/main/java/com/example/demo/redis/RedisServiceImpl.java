package com.example.demo.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service("redisService")
public class RedisServiceImpl implements RedisService {
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	private static Log logger = LogFactory.getLog(RedisServiceImpl.class);
	
	@Override
	public void setRedisObjValueForMapByKey(String key,Map<String, Object> mapObj){
		try {
			redisTemplate.opsForValue().set(key, mapObj);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisObjValueForMapByKey=>key=>"+key+"=>mapObj=>"+mapObj.toString(), e);
		}
	}
	
	@Override
	public Map<String, Object> getRedisObjValueForMapByKey(String key){
		try {
			Object ret_msg= redisTemplate.opsForValue().get(key);
			if(ret_msg!=null){
				return (Map<String, Object>) ret_msg;
			}
			return null;
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisObjValueForMapByKey=>key=>"+key, e);
			return null;
		}
	}
	
	@Override
	public void removeRedisObjByKey(String key){
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>removeRedisObjByKey=>key=>"+key, e);
		}
	}
	
	
	@Override
	public int getRedisIntValueByKey(String key,String field) {
		try {
			Object ret_msg= redisTemplate.opsForHash().get(key, field);
			if(ret_msg==null){
				return 0;
			}else{
				return Integer.parseInt(ret_msg.toString());
			}
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisIntValueByKey=>key=>"+key+"=>field=>"+field, e);
			return 0;
		}
	}
	
	@Override
	public Object getRedisObjValueByKey(String key,String field){
		try {
			return redisTemplate.opsForHash().get(key, field);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisIntValueByKey=>key=>"+key+"=>field=>"+field, e);
			return null;
		}
	}
	
	@Override
	public long getCountForObjSize(String key){
		try {
			return redisTemplate.opsForHash().size(key);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getCountForObjSize=>key=>"+key, e);
			return 0;
		}
	}
	
	@Override
	public boolean isExistKey(String key){
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>isExistKey=>key=>"+key, e);
			return false;
		}
	}
	
	@Override
	public void setRedisHashValueByKey(String key,String field,Object objValue){
		try {
			redisTemplate.opsForHash().put(key, field, objValue);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisHashValueByKey=>key=>"+key+"=>field=>"+field+"=>objValue=>"+objValue.toString(), e);
		}
	}
	
	@Override
	public String setRedisStringValueByKey(String key,String strValue){
		/*try {
			stringRedisTemplate.opsForValue().set(key, strValue);
			return ResultJsonUtil.getSuccessResultJson();
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisStringValueByKey=>key=>"+key+"=>strValue=>"+strValue,e);
			return ResultJsonUtil.getFailResultJson("ERROR["+e.getMessage()+"]");
		}*/
		return null;
	}
	
	@Override
	public String getRedisStringValueByKey(String key){
		/*try {
			String ret_str=stringRedisTemplate.opsForValue().get(key);
			return ResultJsonUtil.getSuccessResultJson(ret_str);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisStringValueByKey=>key=>"+key,e);
			return ResultJsonUtil.getFailResultJson("ERROR["+e.getMessage()+"]");
		}*/
		return null;
	}
	
	@Override
	public void removeRedisStringValueByKey(String key){
		try {
			stringRedisTemplate.delete(key);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>removeRedisStringValueByKey=>key=>"+key,e);
		}
	}
	
	@Override
	public void removeRedisStringValueByKey(List<String> keyList){
		try {
			stringRedisTemplate.delete(keyList);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>removeRedisStringValueByKey=>keyList=>"+keyList,e);
		}
	}
	
	@Override
	public void removeRedisObjByKey(String key,String field){
		try {
			redisTemplate.opsForHash().delete(key, field);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>removeRedisObjByKey=>key=>"+key,e);
		}
	}
	
	@Override
	public Map<String, Object> getRedisMapObjByKey(String key,String field){
		try {
			Object ret_msg= redisTemplate.opsForHash().get(key, field);
			if(ret_msg==null){
				return null;
			}else{
				return (Map<String,Object>)ret_msg;
			}
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisMapObjByKey=>key=>"+key+"=>field=>"+field,e);
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getRedisMapListByKey(String key,String field){
		try {
			Object ret_msg= redisTemplate.opsForHash().get(key, field);
			if(ret_msg==null){
				return null;
			}else{
				return (List<Map<String, Object>>)ret_msg;
			}
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>getRedisMapListByKey=>key=>"+key+"=>field=>"+field,e);
			return null;
		}
	}

	@Override
	public void setRedisTemplateKeyExpirationTimeByMinute(String key, long expiraTime) {
		try {
			redisTemplate.expire(key, expiraTime, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisTemplateKeyExpirationTimeByMinute=>key=>"+key+"=>expiraTime=>"+expiraTime,e);
		}
	}
	
	@Override
	public void setRedisTemplateKeyExpirationTime(String key,TimeUnit timeUnit,long expiraTime){
		try {
			redisTemplate.expire(key, expiraTime, timeUnit);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisTemplateKeyExpirationTime=>key=>"+key+"=>timeUnit=>"+timeUnit+"=>expiraTime=>"+expiraTime,e);
		}
	}
	
	@Override
	public void setRedisTemplateKeyExpirationDate(String key,Date date){
		try {
			redisTemplate.expireAt(key, date);
		} catch (Exception e) {
			logger.error("ERROR=>RedisCommServiceImpl=>setRedisTemplateKeyExpirationDate=>key=>"+key+"=>date=>"+date,e);
		}
	}
}
