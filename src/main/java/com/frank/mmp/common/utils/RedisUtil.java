package com.frank.mmp.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;

/**
* @author 耶律齐
* @version 创建时间：2017年11月6日 上午9:31:46
* redis 工具类
*/
public class RedisUtil {
	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
	/**
	 * 从redis 中的list头部开始获取数据
	 * @param jedisCluster redis连接实例
	 * @param key 要获取的key
	 * @param num 获取长度
	 * @return 没有时返回null
	 */
	public static List<String> getListFromRedis(JedisCluster jedisCluster,String key,int num){
		if(null == key || "".equals(key.trim()) || 0 == num){
			return null;
		}
		List<String> results = new ArrayList<>();
		for(int i=0; i<num; i++){
			List<String> list = jedisCluster.brpop(1, key);
			if(list.isEmpty()){
				break;
			}else{
				results.add(list.get(1));
			}
		}
		if(results.isEmpty()){
			return null;
		}
		return results;
	}
	
	/**
	 * 获取指定key的list列表长度
	 * @param jedisCluster redis连接实例
	 * @param key 要获取的key
	 * @param num 获取长度
	 * @return 没有时返回null
	 */
	public static Long getListSize(JedisCluster jedisCluster,String key){
		if(null == key || "".equals(key.trim())){
			return null;
		}
		Long result = null;
		try{
			result = jedisCluster.llen(key);
			return result;
		}catch(Exception e){
			throw new RuntimeException("Redis key："+key+" is not a \"List\" type");
		}
	}
	
	/**
	 * 将一个数据插入redis list列表的尾部 返回当前list长度
	 * @param jedisCluster redis连接实例
	 * @param key 要获取的key
	 * @param data 要保存的数据
	 * @return 失败时返回null
	 */
	public static Long putDataToRedisList(JedisCluster jedisCluster,String key,String data){
		if(null == key || "".equals(key.trim()) || null == data || "".equals(data.trim())){
			return null;
		}
		try{
			Long result = jedisCluster.rpush(key,data);
			return result;
		}catch(Exception e){
			throw new RuntimeException("Redis key："+key+" is not a \"List\" type");
		}
	}
	
	public static void deleteKey(JedisCluster jedisCluster,String key){
		try{
			jedisCluster.del(key);
		}catch(Exception e){
			log.error("删除redisKey异常：",e);
		}
	}
	
}
