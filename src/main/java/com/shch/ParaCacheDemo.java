package com.shch;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shch.paracache.annotation.ParaCache;

//@Service("demo")
@Component("demo")
@ImportResource({"classpath:applicationContext.xml"}) //引入xml配置文件
public class ParaCacheDemo {
	@ParaCache(key="stock.info")
	//@ParaCache  //没有参数默认将被注解的属性作为key
	public Hashtable<Integer,String> info;

	public void printInfo(){
		System.out.println("Debug:"+info);
		for(Object key:info.keySet()){
			System.out.println("key:"+key+"\t"+"value:"+info.get(key));
		}
	}
}
