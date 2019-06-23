package com.byk.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Title: Bean 工具类
 *
 * @author yikai.bi
 */
public class BeanUtil {

    public static void copyProperties(Object source, Object target) {
        Class s = source.getClass();
        Class t = target.getClass();

        Method[] ms = s.getDeclaredMethods();
        Method[] mt = t.getDeclaredMethods();

        HashMap<String, Method> targetMap = new HashMap<String, Method>();
        HashMap<String, Method> sourceMap = new HashMap<String, Method>();

        for (Method m : ms) {
            sourceMap.put(m.getName(), m);
        }
        for (Method m : mt) {
            targetMap.put(m.getName(), m);
        }
        Field[] ft = t.getDeclaredFields();
        Field[] fs = s.getDeclaredFields();
        for (Field f : fs) {
            String name = f.getName();
            String getMethodName = "get" + name.substring(0, 1).toUpperCase()
                    + name.substring(1);
            Method getMethod = sourceMap.get(getMethodName);

            String setMethodName = "set" + name.substring(0, 1).toUpperCase()
                    + name.substring(1);
            Method setMethod = targetMap.get(setMethodName);

            try {
                Object value = getMethod.invoke(source, null);
                setMethod.invoke(target, value);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
