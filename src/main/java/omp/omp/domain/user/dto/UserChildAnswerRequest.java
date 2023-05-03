package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;

@Data
public class UserChildAnswerRequest {

    Long id;
    ParentType parentType;
    List<UserChildAnswer> userChildAnswers;

    public UserChildAnswerRequest(Long id, ParentType parentType, List<UserChildAnswer> userChildAnswers) {
        this.id = id;
        this.parentType = parentType;
        this.userChildAnswers = userChildAnswers;
    }
}
