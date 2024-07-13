package com.gustarodrigues.picpay.clients;

import com.gustarodrigues.picpay.entities.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotifyClient", url = "${client.notify-service.url}")
public interface NotifyClient {

    @PostMapping
    ResponseEntity<Void> sendNotify(@RequestBody Transfer transfer);
}
