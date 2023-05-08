package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResultResponse {

    private List<UserResult> userResults;

    private String name;

    public UserResultResponse(User user, ParentType parentType) {
        this.name = user.getName();

        this.userResults = user.getUserQuestions().stream()
                .filter(ur -> ur.getParentType().equals(parentType))
                .map(ur -> new UserResult(ur, parentType))
                .collect(Collectors.toList());
    }
}
