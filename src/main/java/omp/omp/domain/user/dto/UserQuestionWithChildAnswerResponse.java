package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserQuestionWithChildAnswerResponse {

    private List<UserQuestionWithChildAnswer> userQuestionWithChildAnswers;

    private String name;

    private boolean isAnswered;

    public UserQuestionWithChildAnswerResponse(User user, ParentType parentType) {

        this.name = user.getName();
        isAnswered = !user.isParentAnswerNull(parentType) && !user.isScoreNull(parentType);

        this.userQuestionWithChildAnswers = user.getUserQuestions().stream()
                .filter(uqca -> uqca.getParentType().equals(parentType))
                .map(uqca -> new UserQuestionWithChildAnswer(uqca, isAnswered, parentType))
                .collect(Collectors.toList());
    }
}
