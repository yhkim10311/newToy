package com.bulletin.toy.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiResult<T> {

    private final boolean success;

    private final T response;

    private final ApiError error;

    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> ERROR(Throwable throwable, HttpStatus httpStatus){
        return new ApiResult<>(false, null, new ApiError(throwable, httpStatus.value()));
    }

    public static ApiResult<?> ERROR(String errorMessage, HttpStatus httpStatus){
        return new ApiResult<>(false, null, new ApiError(errorMessage, httpStatus.value()));
    }
}