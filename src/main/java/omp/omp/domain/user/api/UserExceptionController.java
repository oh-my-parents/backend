package omp.omp.domain.user.api;

import omp.omp.domain.user.exception.UserException;
import omp.omp.global.util.ErrorResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * UserApi Controller 전용 예외처리 입니다.
 */
@RestControllerAdvice(assignableTypes = {UserApi.class})
public class UserExceptionController {

    @ExceptionHandler(UserException.class)
    public ErrorResult UserExceptionHandler(UserException e) {
        return new ErrorResult(ErrorResult.CODE_CLIENT_ERROR, e.getUserExceptionGroup().getDesc());
    }
}
