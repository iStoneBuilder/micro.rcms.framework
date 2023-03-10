package com.stone.it.rcms.base.util;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/9/4 11:57 AM
 * @Version 1.0
 */
public class MapUtil {

    public static Map<String, String> convertToMap(Object bean)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map returnMap = new HashMap();
        if (bean==null){
            return returnMap;
        }
        Class type = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result.toString());
                }
            }
        }
        return returnMap;
    }

}


