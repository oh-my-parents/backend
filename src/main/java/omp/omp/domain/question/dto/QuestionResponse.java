package omp.omp.domain.question.dto;

import lombok.Data;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.userquestion.domain.ParentType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionResponse {

    List<QuestionDTO> questionDTOs;

    public QuestionResponse(List<Question> questions, ParentType parentType) {

        this.questionDTOs = questions.stream()
                .map(q -> new QuestionDTO(q, parentType))
                .collect(Collectors.toList());
    }
}
