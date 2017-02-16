package com.shch.paracache.route;

import com.shch.paracache.annotation.ParaCachePropertyPlaceholderConfigurer;

public class Route {
    public ParaCachePropertyPlaceholderConfigurer propertyConfigurer;
	public Route(ParaCachePropertyPlaceholderConfigurer propertyConfigurer){
        this.propertyConfigurer=propertyConfigurer;
	}
	public String resolver(String key){ //路由分路器
		String cacheType=null;
		if(key!=null&&key.toString().contains(".")){ //该条件待验证是否有问题
			//在此处申明String字符串，因为split分割的结果是字符数组，且不知其数组长度
			String[] splitResult=key.toString().split("\\."); 
		    String key_pro=splitResult[0];		
		   cacheType=(String) propertyConfigurer.getProperty(key_pro);
		}
		return cacheType;
	}

}
