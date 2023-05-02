package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public enum UserExceptionGroup {

    USER_NULL("U001", "유저가 없습니다."),
    USER_SCORE_NULL("U002", "아직 채점이 완료되지 않은 상태입니다."),
    USER_QUESTION_INVALID_SAVE("U003", "저장된 질문의 순서와 전달받은 질문의 순서가 올바르지 않습니다."),
    USER_QUESTION_NULL("U004", "저장된 질문에 대한 답변이 없습니다.");
    private final String userErrorCode;
    private final String desc;

    UserExceptionGroup(String userErrorCode, String desc) {
        this.userErrorCode = userErrorCode;
        this.desc = desc;
    }
}
