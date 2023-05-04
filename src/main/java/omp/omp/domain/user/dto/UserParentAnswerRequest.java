package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;

@Data
public class UserParentAnswerRequest {

    ParentType parentType;
    List<UserParentAnswer> userParentAnswers;

    public UserParentAnswerRequest(ParentType parentType, List<UserParentAnswer> userParentAnswers) {
        this.parentType = parentType;
        this.userParentAnswers = userParentAnswers;
    }
}
