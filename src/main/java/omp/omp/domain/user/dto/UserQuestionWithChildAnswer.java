package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;
import omp.omp.domain.userquestion.domain.UserQuestion;

@Data
public class UserQuestionWithChildAnswer {

    private Long questionNumber;

    private String question;

    private ParentType parentType;

    private String childAnswer;

    private int score;


    public UserQuestionWithChildAnswer(UserQuestion userQuestion, boolean isAnswered, ParentType parentType) {

        this.parentType = userQuestion.getParentType();
        this.questionNumber = userQuestion.getQuestion().getId();
        this.question = userQuestion.getQuestion().plusParentType(parentType);
        this.childAnswer = userQuestion.getChildAnswer().getChildContent();
        if (isAnswered) {
            this.score = userQuestion.getParentAnswer().getScore();
        }
    }
}
