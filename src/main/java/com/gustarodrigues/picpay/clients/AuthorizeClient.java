package com.gustarodrigues.picpay.clients;

import com.gustarodrigues.picpay.clients.dtos.AuthorizeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AuthorizeClient",url = "${client.authorize-service.url}")
public interface AuthorizeClient {

    @GetMapping
    ResponseEntity<AuthorizeResponse> isAuthorized();
}
