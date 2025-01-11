package com.kb.exam.common;

import lombok.Getter;

@Getter
public class CommonResponse {
    private final int code;
    private String message;
    private Object data;

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
