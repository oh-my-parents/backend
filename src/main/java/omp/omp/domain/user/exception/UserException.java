package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final UserExceptionGroup userExceptionGroup;

    public UserException(UserExceptionGroup userExceptionGroup) {
        this.userExceptionGroup = userExceptionGroup;
    }
}
