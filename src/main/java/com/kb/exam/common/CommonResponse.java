package com.kb.exam.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommonResponse {
    private final int code;
    private String message;
    private Object data;

    // 데이터 없는 성공
    public static CommonResponse success() {
        return new CommonResponse();
    }

    // 데이터 있는 성공
    public static CommonResponse success(Object data) {
        return new CommonResponse(data);
    }

    // 실패는 메시지 설정
    public static CommonResponse fail(String message) {
        return new CommonResponse(message);
    }

    // 데이터 없는 성공
    public CommonResponse() {
        this.code = 200;
    }

    // 데이터 있는 성공
    public CommonResponse(Object data) {
        this.code = 200;
        this.data = data;
    }

    // 실패 코드는 500으로 통일
    public CommonResponse(String message) {
        this.code = 500;
        this.message = message;
    }
}
