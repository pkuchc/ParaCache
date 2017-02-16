package com.shch.paracache.data;

public interface IDataSource {  //获得缓存数据接口
	Object getCacheData(Object key,Object cacheType);

}
