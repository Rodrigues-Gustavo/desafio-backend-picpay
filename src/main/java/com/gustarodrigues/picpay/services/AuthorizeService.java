package com.gustarodrigues.picpay.services;

import com.gustarodrigues.picpay.clients.AuthorizeClient;
import com.gustarodrigues.picpay.controllers.dtos.TransferDto;
import com.gustarodrigues.picpay.entities.Transfer;
import com.gustarodrigues.picpay.exceptions.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {

    private final AuthorizeClient authorizeClient;

    public AuthorizeService(AuthorizeClient authorizeClient) {
        this.authorizeClient = authorizeClient;
    }

    public boolean isAuthorized(TransferDto transfer) {

        var res = authorizeClient.isAuthorized();

        if (res.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return res.getBody().authorized();
    }

}
