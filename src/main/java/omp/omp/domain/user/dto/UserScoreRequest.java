package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

@Data
public class UserScoreRequest {

    private ParentType parentType;

    public UserScoreRequest(ParentType parentType) {
        this.parentType = parentType;
    }
}
