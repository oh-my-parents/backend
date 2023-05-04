package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResultResponse {

    private List<UserResult> userResults;

    private String name;

    public UserResultResponse(User user) {
        this.name = user.getName();

        this.userResults = user.getUserQuestions().stream()
                .map(UserResult::new)
                .collect(Collectors.toList());
    }
}
