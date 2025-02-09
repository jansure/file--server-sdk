package com.cabr.pkpm.clusterRedis;

public interface JedisService {
	//String类型
	public String set(String key,String value);
	//String get
	public String get(String key);
	//hash类型
	public Long hset(String key, String field, String value);
	//hash get
	public String hget(String key, String field);
	//hash delete
	public Long hdel(String key,String fields);
	//设置数据过期
	public Long expire(String key,int seconds);
	
}
