package omp.omp.global.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {

    public static final String MESSAGE_BAD = "잘못된 요청 입니다.";

    public static final int CODE_CLIENT_ERROR = 400;
    public static final int CODE_SERVER_ERROR = 500;

    private int code;
    private String message;
}
