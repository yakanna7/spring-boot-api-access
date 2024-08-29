package com.skytech.api_access.util;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtil {
    private static WebClient buildWebClient() {
        return WebClient.create();
    }

    public static <T> T get(String targetUrl, Object params, Class<T> returnType) {

        return WebClientUtil.buildWebClient()
                .get()
                .uri(UrlUtil.getTargetUrl(targetUrl, params))
                .retrieve()
                .bodyToMono(returnType)
                .block();
    }

   public static <T> T get(String targetUrl, Object[] uriVariables, Class<T> returnType) {

        return WebClientUtil.buildWebClient()
                .get()
                .uri(targetUrl, uriVariables)
                .retrieve()
                .bodyToMono(returnType)
                .block();
    }


    // POST Request
    public static <T> T post(String targetUrl, Object request, Class<T> returnType) {
        Mono<Object> reqMono = Mono.just(request);
        return WebClientUtil.buildWebClient()
                .post()
                .uri(targetUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reqMono, Object.class)
                .retrieve()
                .bodyToMono(returnType)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    handleError(ex);
                    return Mono.empty();
                })
                .block();
    }

    // PUT Request
    public static <T> T  put(String targetUrl, Object requestBody, Class<T> responseType) {
        Mono<Object> reqBodyMono = Mono.just(requestBody);
        return WebClientUtil.buildWebClient()
                .put()
                .uri(targetUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reqBodyMono, Object.class)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    handleError(ex);
                    return Mono.empty();
                })
                .block();
    }

    // DELETE Request
    public static void delete(String targetUrl) {
        WebClientUtil.buildWebClient()
                .delete()
                .uri(targetUrl)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    handleError(ex);
                    return Mono.empty();
                })
                .block();
    }

    // Common error handling method
    private static void handleError(WebClientResponseException ex) {
        // You can log the error, throw a custom exception, or handle it as needed
        throw new RuntimeException("Error occurred: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
    }
}

