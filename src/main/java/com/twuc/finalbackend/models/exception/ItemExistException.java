package com.twuc.finalbackend.models.exception;

import org.springframework.http.HttpStatus;

public class ItemExistException extends CustomBasicException {

    public ItemExistException(String message) {
        super(HttpStatus.BAD_REQUEST ,CustomCode.ITEM_NOT_EXIST, message);
    }
}
