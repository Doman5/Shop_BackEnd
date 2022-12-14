package com.domanski.backend.common.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class DefaultErrorDto {
    private Date timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
