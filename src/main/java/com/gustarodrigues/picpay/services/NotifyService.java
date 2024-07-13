package com.gustarodrigues.picpay.services;

import com.gustarodrigues.picpay.clients.NotifyClient;
import com.gustarodrigues.picpay.entities.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    private static final Logger logger = LoggerFactory.getLogger(NotifyService.class);

    private final NotifyClient notifyClient;

    public NotifyService(NotifyClient notifyClient) {
        this.notifyClient = notifyClient;
    }

    public void sendNotify(Transfer transfer) {
        try {
            logger.info("sending notification");

           var res =  notifyClient.sendNotify(transfer);

           if (res.getStatusCode().isError()) {
               logger.error("Error while sending notification, status code is not ok");
           }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }

}
