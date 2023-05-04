package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserQuestionWithChildAnswerResponse {

    private List<UserQuestionWithChildAnswer> userQuestionWithChildAnswers;

    private String name;

    public UserQuestionWithChildAnswerResponse(User user) {

        this.name = user.getName();

        this.userQuestionWithChildAnswers = user.getUserQuestions().stream()
                .map(UserQuestionWithChildAnswer::new)
                .collect(Collectors.toList());
    }
}
