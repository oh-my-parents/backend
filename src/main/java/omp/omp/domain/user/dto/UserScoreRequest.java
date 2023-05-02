package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

@Data
public class UserScoreRequest {

    private Long id;
    private ParentType parentType;

    public UserScoreRequest(Long id, ParentType parentType) {
        this.id = id;
        this.parentType = parentType;
    }
}
