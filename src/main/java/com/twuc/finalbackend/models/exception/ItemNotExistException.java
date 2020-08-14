package com.twuc.finalbackend.models.exception;

import org.springframework.http.HttpStatus;

import static com.twuc.finalbackend.models.exception.CustomCode.ITEM_NOTE_EXIST;

public class ItemNotExistException extends CustomBasicException{

    public ItemNotExistException(String message) {
        super(HttpStatus.BAD_REQUEST, ITEM_NOTE_EXIST, message);
    }
}
