package com.twuc.finalbackend.models.exception;

public enum CustomCode {
    ITEM_EXIST("40001"), ITEM_NOTE_EXIST("40002");
    private final String code;
    CustomCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
