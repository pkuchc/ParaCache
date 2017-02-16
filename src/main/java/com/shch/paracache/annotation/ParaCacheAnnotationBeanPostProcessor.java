package com.shch.paracache.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.shch.paracache.data.DataSourceImp;
import com.shch.paracache.data.IDataSource;
import com.shch.paracache.route.Route;

@Component//定义匿名组件，从而将该类在启动时通过扫描组件的方式加入Spring IOC容器中，并创建Bean
public class ParaCacheAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter{
	@Autowired //自动注入该对象Bean，用于获取配置资源(application.properties文件中的配置信息)
	private ParaCachePropertyPlaceholderConfigurer propertyConfigurer; //ParaCachePropertyPlaceholderConfigurer类本身的Bean是
	                                                                   //通过.xml文件注入到IOC容器中的，然后才能通过Autowired注解注入给属性值
	private SimpleTypeConverter typeConverter=new SimpleTypeConverter(); //简单类型转换器
	private IDataSource dataSource=new DataSourceImp(); //数据层接口及数据实现类
	
	protected final Log logger = LogFactory.getLog(getClass());	
	
	@Override
	public boolean postProcessAfterInstantiation(final Object bean,String beanName)throws BeansException{
		//注意嵌套了三层方法：
		//第一层：postProcessAfterInstantiation方法
		//第二层：ReflectionUtils类的doWithFields方法
		//第三层：ReflectionUtils类的FieldCallback接口中定义的doWtith方法（处理Field属性），且
		ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback()/*Spring某生命周期的回调方法，待研究*/ {
			
			
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				// TODO Auto-generated method stub
				//属性Field，通过反射得到注解类
				ParaCache paraCache=field.getAnnotation(ParaCache.class);//利用被注解的属性得到注解类
				if(paraCache!=null){ //注解不为空
					if(Modifier.isStatic(field.getModifiers())){ //检查被注解的属性是否为Static，静态属性不能被注解，因为静态属性属
						                                         //于类，不属于某个实例，而注解是对某个实例的属性进行注解
						throw new IllegalStateException("@ParaCache annotation is not supported on static fields!");						
					}
					//如果没有设置@paraCache的key,则key取被注解的属性名作值，然后通过key去application.properties文件中查找value
					//String key=paraCache.key().length()<=0?field.getName():paraCache.key();
					String key=paraCache.key();
					
					//路由策略
					Route route=new Route(propertyConfigurer);
					String cacheType=route.resolver(key);
					//Object value=propertyConfigurer.getProperty(key);

					if(logger.isDebugEnabled()){ //logger是debug层级时才输出信息
						//测试任务：输出配置信息的kyes
						propertyConfigurer.printKeys();
					}

				
					//logger.debug("从application.properties文件中读取的配置信息:"+"key-"+key+"\t value-"+value);
					
					if(cacheType!=null){
						//关键步骤，
						//
						Object cacheValue=dataSource.getCacheData(key,cacheType);//从缓存中取出数据
					    Object	valueConvert=typeConverter.convertIfNecessary(cacheValue, field.getType()/*属性的申明的类型*/); //将value类型转为属性申明的类型
					    ReflectionUtils.makeAccessible(field);//通过反射使被注解的属性能够被设置，即能够为属性注入值
					    field.set(bean, valueConvert);//为bean的属性注入值
					}
				}
			}
		});
		return true;
	}

}
