package com.shch.paracache.data;

import java.util.Hashtable;

public class DataSourceImp implements IDataSource {
	private Hashtable<Integer,String> hashtable=new Hashtable<Integer,String>();
	//private String[] splitResult=new String[2]; //字符串数组，待优化成StringBuilder
	//private String cacheType="hashtable";
	private String cacheType;
	//private String key="chenci";
	private String key;
	private Object result;

	@Override
	public Object getCacheData(Object key,Object cacheType) { 
		// TODO Auto-generated method stub
		//根据cacheType来实现不同的缓存策略
		if(key!=null&&cacheType!=null){ //该条件待验证是否有问题

			if("hashtable".equals(cacheType)){//当cacheType为空时，cacheType.equals("hashtable")会报错
				                              //而"hashtable".equals(cacheType)不会报错，且返回结果为false								
				result=getCacheDataFromHashTable(key);
				
			}else if("redis".equals(cacheType)){
				//result=getCacheDataFromRedis(key);
			}else if("memcached".equals(cacheType)){
				//result=getCacheDataFromMemcache(key);
			}else{
				
			}
		}		
		return result;
	}
	
	private Object getCacheDataFromHashTable(Object key){
		for(int i=0;i<10;i++){  //直接把数据写死，key并没有起作用返回值是一个Hashtable
			hashtable.put(i, "cc测试   "+i);
		}
		return hashtable;
	}
	private Object getCacheDataFromRedis(Object key){ //从Redis缓存中取数据
		Object result=null;
		return result;		
	}
	private Object getCacheDataFromMemcache(Object key){//从Memcache缓存中取数据
		Object result=null;
		return result;
	}

}
