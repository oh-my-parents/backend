package omp.omp.domain.question.application;

import lombok.Getter;
import omp.omp.domain.choice.application.ChoiceGroup;
import omp.omp.domain.question.domain.QuestionType;

import java.util.List;

/**
 * 본 클래스는 질문지를 저장하기위한 곳입니다.
 * 질문을 추가 하거나 질문 순서를 변경 하고 싶다면 여기를 수정하면 됩니다.
 */
@Getter
public enum QuestionGroup {

    PARENT_CALL_WHEN("%s와 언제 마지막으로 통화했나요?", 1L, QuestionType.SELECT, ChoiceGroup.TODAY, ChoiceGroup.LAST_WEEK, ChoiceGroup.LAST_MONTH, ChoiceGroup.WHEN_WHEN),
    PARENT_AGE("%s의 올해 연세를 알고 있나요?", 2L, QuestionType.DROPDOWN, ChoiceGroup.NOTHING),
    PARENT_FAVORITE_FOOD("%s가 가장 좋아하는 음식이 무엇인가요?", 3L, QuestionType.WORD, ChoiceGroup.NOTHING),
    PARENT_FAVORITE_SONG("%s가 최근에 빠져있는 음악은 무엇인가요?", 4L, QuestionType.WORD, ChoiceGroup.NOTHING),
    PARENT_FAVORITE_MY_MEMORY("%s가 가장 기억에 남으셨을만한 나와의 기억이 뭔지 아시나요?", 5L, QuestionType.SENTENCE, ChoiceGroup.NOTHING),
    PARENT_HOBBY("%s의 취미가 뭔지 아시나요?", 6L, QuestionType.WORD, ChoiceGroup.NOTHING),
    PARENT_DREAM("%s의 꿈이 뭔지 아시나요?", 7L, QuestionType.SENTENCE, ChoiceGroup.NOTHING),
    PARENT_THINK_ME("%s가 바라는 미래의 나의 모습을 적어주세요", 8L, QuestionType.SENTENCE, ChoiceGroup.NOTHING);

    private final String message;
    private final Long order;
    private final QuestionType questionType;
    private final List<ChoiceGroup> choiceGroups;

    QuestionGroup(String message, Long order, QuestionType questionType, ChoiceGroup... choiceGroups) {
        this.message = message;
        this.order = order;
        this.questionType = questionType;
        this.choiceGroups = List.of(choiceGroups);
    }
}
