package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserQuestionWithChildAnswerResponse {

    private List<UserQuestionWithChildAnswer> userQuestionWithChildAnswers;

    private String name;

    private boolean isAnswered;

    public UserQuestionWithChildAnswerResponse(User user) {

        this.name = user.getName();
        isAnswered = !user.isParentAnswerNull() && !user.isScoreNull();

        this.userQuestionWithChildAnswers = user.getUserQuestions().stream()
                .map(uqca -> new UserQuestionWithChildAnswer(uqca, isAnswered))
                .collect(Collectors.toList());
    }
}
