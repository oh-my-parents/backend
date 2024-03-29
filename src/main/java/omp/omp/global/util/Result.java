package omp.omp.global.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 해당 유틸은 API 사용시 결과를 반환할때
 * Result 클래스로 한번 더 감싸주기 위해서 입니다.
 */
@Data
@AllArgsConstructor
public class Result<T> {

    public static final String MESSAGE_OK = "ok";

    public static final int CODE_CONTINUE = 100;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_REDIRECT = 300;
    public static final int CODE_CLIENT_ERROR = 400;
    public static final int CODE_SERVER_ERROR = 500;

    private int code;
    private String message;
    private T data;
}