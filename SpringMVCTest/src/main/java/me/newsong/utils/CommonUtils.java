package me.newsong.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {
	/**
	 * 生成一个16位的随机字符串，不重复
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 将表单数据封装为对象
	 * @param properties
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(Map<String, String[]> properties, Class<T> clazz) {
		T bean = null;
		Map<String,Object> output = new HashMap<>();
		for(Entry<String,String[]> p: properties.entrySet()){
			output.put(p.getKey(), p.getValue()[0]);
		}
		try {
			bean = clazz.newInstance();
			BeanUtils.populate(bean, output);
		} catch (Exception e) {
			throw new RuntimeException(e);// 抛出一个运行时异常
		}
		return bean;
	}

}
