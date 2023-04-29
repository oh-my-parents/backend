package omp.omp.domain.question.dto;

import lombok.Data;
import omp.omp.domain.userquestion.domain.ParentType;


@Data
public class QuestionRequest {

    private ParentType parentType;

    public QuestionRequest(ParentType parentType) {
        this.parentType = parentType;
    }
}
