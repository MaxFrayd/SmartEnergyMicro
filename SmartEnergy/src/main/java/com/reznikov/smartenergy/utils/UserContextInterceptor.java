package com.reznikov.smartenergy.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class UserContextInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add("Authorization", UserContextHolder.getContext().getAuthToken());
            System.out.println("Request: " + request.getMethod() + " " + request.getURI());

        return execution.execute(request, body);
    }
}
