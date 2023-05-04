package omp.omp.domain.choice.application;

import lombok.Getter;

@Getter
public enum ChoiceGroup {
    TODAY(1,"오늘"),
    LAST_WEEK(2, "지난 주"),
    LAST_MONTH(3, "지난 달"),
    WHEN_WHEN(4, "언제더라..."),
    NOTHING(1000, "이 값은 볼수없다!");

    private final int number;
    private final String answer;

    ChoiceGroup(int number, String answer) {
        this.number = number;
        this.answer = answer;
    }
}
