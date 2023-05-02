package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;

@Data
public class UserChildAnswerRequest {

    Long id;
    ParentType parentType;
    List<UserChildAnswer> userChildAnswer;

    public UserChildAnswerRequest(Long id, ParentType parentType, List<UserChildAnswer> userChildAnswer) {
        this.id = id;
        this.parentType = parentType;
        this.userChildAnswer = userChildAnswer;
    }
}
