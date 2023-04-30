package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public enum UserExceptionGroup {

    USER_NULL("U001", "존재하지 않는 유저입니다."),
    USER_SCORE_NULL("U002", "아직 채점이 완료되지 않은 상태입니다."),
    USER_NO_MADE_QUESTION("U002", "아직 유저가 질문지를 만들지 않았습니다.");

    private final String userErrorCode;
    private final String desc;

    UserExceptionGroup(String userErrorCode, String desc) {
        this.userErrorCode = userErrorCode;
        this.desc = desc;
    }
}
