package com.shch.paracache.annotation;

import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class ParaCachePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
                                
	protected final Log logger = LogFactory.getLog(getClass());	
	public Properties props;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory,Properties props)throws BeansException{
		super.processProperties(beanFactory, props);//将配置资源(application.properties文件中的配置信息)应用到Bean的生产工厂对象中
		this.props=props;
	}
	public void  printKeys(){
		if(logger.isDebugEnabled()){ //logger是debug层级时才输出信息
			//logger.debug("配置文件是否包含info："+props.containsKey("info"));
			logger.debug("application.properties文件中的key:");
			for(Object keyConfig:props.keySet()){
				logger.debug(keyConfig+"\t");
			}
			System.out.println();
		}
	}
	public Object getProperty(String key){
		return props.get(key);	
	}


}


