package com.huy8895.dictionaryapi.config.aop;

import java.util.Arrays;

public class CacheKeyGenerator {

    /**
     * Append the method name , param to an array and create a deepHashCode of the array as redis cache key
     *
     * @param methodName
     * @param params
     * @return
     */
    public static String generateKey(String methodName, Object... params) {
        if (params.length == 0) {
            return Integer.toString(methodName.hashCode());
        }
        Object[] paramList = new Object[params.length + 1];
        paramList[0] = methodName;
        System.arraycopy(params, 0, paramList, 1, params.length);
//        Arrays.copyOf(params, params.length + 1)
        int hashCode = Arrays.deepHashCode(paramList);
        return Integer.toString(hashCode);
    }
}
