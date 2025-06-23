package com.telzz.sub.response;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    public static Map<String, Object> success(Object object){
        if(object instanceof String){
            return response(true, "message", object);
        }
        return response(true, "data", object);
    }

    public static Map<String, Object> error(Object object){
        if(object instanceof String){
            return response(false, "message", object);
        }
        return response(false, "errors", object);
    }

    private static Map<String, Object> response(boolean success, String objectKey, Object object){
        Map<String, Object> data = new HashMap<>();
        data.put("success", success);
        data.put(objectKey, object);
        return data;
    }
}
