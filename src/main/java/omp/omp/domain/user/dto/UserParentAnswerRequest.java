package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;

@Data
public class UserParentAnswerRequest {

    String id;
    ParentType parentType;
    List<UserParentAnswer> userParentAnswers;

    public UserParentAnswerRequest(String id, ParentType parentType, List<UserParentAnswer> userParentAnswers) {
        this.id = id;
        this.parentType = parentType;
        this.userParentAnswers = userParentAnswers;
    }
}
