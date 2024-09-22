package com.weather.app.utils;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<?> success(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("timestamp", LocalDateTime.now());
        if (data != null)
            map.put("data", data);
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> error(Object err) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "error");
        map.put("timestamp", LocalDateTime.now());
        if (err != null)
            map.put("error", err);
        return ResponseEntity.ok(map);
    }
}
