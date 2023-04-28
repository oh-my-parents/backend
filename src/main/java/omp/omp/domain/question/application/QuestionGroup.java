package omp.omp.domain.question.application;

import lombok.Getter;
import omp.omp.domain.userquestion.domain.QuestionParentType;

/**
 * 본 클래스는 질문지를 저장하기위한 곳입니다.
 * 질문을 추가 하거나 질문 순서를 변경 하고 싶다면 여기를 수정하면 됩니다.
 */
@Getter
public enum QuestionGroup {
    PARENT_AGE("${parent}의 나이는?", 1),
    PARENT_FAVORITE_FOOD("${parent}이 가장 좋아하는 음식은?", 2),
    PARENT_FAVORITE_SONG("${parent}이 가장 좋아하는 노래는?", 3),
    PARENT_HOBBY("${parent}의 취미는 무엇일까요?", 4),
    PARENT_DREAM("${parent}의 꿈은 뭘까요?", 5),
    PARENT_THINK_ME("${parent}이 생각하는 나의 모습은?", 8);

    private final String message;
    private final int order;

    QuestionGroup(String message, int order) {
        this.message = message;
        this.order = order;
    }

    public String getMessagePlusParentType(QuestionParentType questionParentType) {
        return message.replaceAll("\\$\\{parent}", questionParentType.getParent());
    }
}
