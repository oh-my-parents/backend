package omp.omp.domain.user.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;

@Data
public class UserResultRequest {

    private String id;
    private ParentType parentType;

    public UserResultRequest(String id, ParentType parentType) {
        this.id = id;
        this.parentType = parentType;
    }
}
