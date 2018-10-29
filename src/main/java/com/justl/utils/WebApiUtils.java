package com.justl.utils;

import com.justl.domain.response.ResponseData;

import java.util.Collection;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-10-29 12:48
 */
public class WebApiUtils {
    /**
     * 判断传来的参数是否是空的或者空的对象
     * 参数是可变参数 ，可变参数中如果有一个是空或者空的对象，那么也返回true
     *
     * @param objs 待判断的对象
     * @return null或者空的对象都返回true 不为空返回false
     */
    public static boolean isNullOrEmpty(Object... objs) {
        if (objs == null) {
            return true;
        }

        for (Object obj : objs) {
            if (null == obj) {
                return true;
            }
            if (obj instanceof CharSequence) {
                if (obj instanceof String) {
                    if (!((String) obj).trim().isEmpty()) {
                        continue;
                    }
                    return true;
                }
                if (((CharSequence) obj).length() != 0) {
                    continue;
                } else {
                    return true;
                }
            }

            if (obj instanceof Collection) {
                if (!(((Collection<?>) obj).isEmpty())) {
                    continue;
                }
                return ((Collection<?>) obj).isEmpty();
            }

            if (obj instanceof Map) {
                if (!(((Map<?, ?>) obj).isEmpty())) {
                    continue;
                }
                return true;
            }

            if (obj instanceof Object[]) {
                Object[] object = (Object[]) obj;
                if (object.length == 0) {
                    return true;
                }
                boolean empty = true;
                for (int i = 0; i < object.length; i++) {
                    if (!isNullOrEmpty(object[i])) {
                        empty = false;
                        break;
                    }
                }
                if (empty) {
                    return empty;
                }
            }
        }
        return false;
    }

    /**
     * 封装返回的数据 待数据和描述信息
     *
     */
    public static <T> ResponseData<T> getResultInfo(T t, String msg) {
        ResponseData<T> result = getResultInfo(t);
        if (!WebApiUtils.isNullOrEmpty(msg)) {
            result.setMessage(msg);
        }
        return result;
    }

    /**
     * 封装返回的数据 带数据 描述信息  状态码

     */
    public static <T> ResponseData<T> getResultInfo(T t, String msg, int code) {
        ResponseData<T> result = getResultInfo(t, msg);
        result.setCode(code);
        return result;
    }

    /**
     * 封装返回的数据
     */
    public static <T> ResponseData<T> getResultInfo(T t) {
        ResponseData<T> result = new ResponseData<T>(t);
        return result;
    }
}
