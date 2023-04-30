package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public enum UserExceptionGroup {

    USER_NULL("U001", "해당유저가 만든 부모님타입의 설문지가 없습니다."),
    USER_SCORE_NULL("U002", "아직 채점이 완료되지 않은 상태입니다.");

    private final String userErrorCode;
    private final String desc;

    UserExceptionGroup(String userErrorCode, String desc) {
        this.userErrorCode = userErrorCode;
        this.desc = desc;
    }
}
