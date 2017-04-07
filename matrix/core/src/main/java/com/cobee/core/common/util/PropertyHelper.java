package com.cobee.core.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public abstract class PropertyHelper {
	
	public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException
	{
		String labelTmp = "";
		Integer docIdx = -1;
		while ((docIdx = name.indexOf(".", docIdx + 1)) >= 0)
		{
			labelTmp = name.substring(0, docIdx);
			Object obj = PropertyUtils.getProperty(bean, labelTmp);
			if (obj == null)
			{
				Class<?> clazz = PropertyUtils.getPropertyType(bean, labelTmp);
				Object newObj = clazz.newInstance();
				PropertyUtils.setProperty(bean, labelTmp, newObj);
			}
		}
		PropertyUtils.setProperty(bean, name, value);
	}
	
}
