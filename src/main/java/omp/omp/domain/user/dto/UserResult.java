package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;
import omp.omp.domain.userquestion.domain.UserQuestion;

@Data
public class UserResult {

    private Long questionNumber;

    private String question;

    private ParentType parentType;

    private String childAnswer;

    private int parentScore;

    public UserResult(UserQuestion userQuestion) {
        this.questionNumber = userQuestion.getQuestion().getId();
        this.parentType = userQuestion.getParentType();
        this.question = userQuestion.getQuestion().plusParentType(parentType);
        this.childAnswer = userQuestion.getChildAnswer().getChildContent();
        this.parentScore = userQuestion.getParentAnswer().getScore();
    }
}
