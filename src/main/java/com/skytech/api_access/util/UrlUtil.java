package com.skytech.api_access.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.lang.Nullable;

public class UrlUtil {
    private static String getQueryStringByMap(Map<String, Object> map) {
        if (map.isEmpty()) {
            return "";
        }
        List<String> paramPairs = map.keySet()
                .stream()
                .map(key -> String.format("%s=%s", key, map.get(key)))
                .collect(Collectors.toList());

        return String.join("&", paramPairs);
    }

    @SuppressWarnings("unchecked")
    public static String getTargetUrl(String baseUrl, @Nullable Object params) {
        if (params == null) {
            return baseUrl;
        }

        if (!(params instanceof Map)) {
            throw new IllegalArgumentException("Params must be a map");
        }

        Map map = (Map) params;
        String queryString = getQueryStringByMap(map);
        String targetUrl = baseUrl + "?" + queryString;
        return targetUrl;
    }
}
