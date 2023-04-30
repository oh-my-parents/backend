package omp.omp.domain.question.api;

import omp.omp.global.util.ErrorResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * QuestionApi Controller 전용 예외처리 입니다.
 */
@RestControllerAdvice(assignableTypes = {QuestionApi.class})
public class QuestionExceptionController {

    @ExceptionHandler(BindException.class)
    public ErrorResult MethodArgNotValidExHandler(BindException e) {
        return new ErrorResult(ErrorResult.CODE_CLIENT_ERROR, ErrorResult.MESSAGE_BAD);
    }
}