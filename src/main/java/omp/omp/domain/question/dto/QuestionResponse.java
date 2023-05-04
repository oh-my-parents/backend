package omp.omp.domain.question.dto;

import lombok.Data;
import omp.omp.domain.question.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionResponse {

    List<QuestionDTO> questionDTOs;

    public QuestionResponse(List<Question> questions) {

        this.questionDTOs = questions.stream()
                .map(QuestionDTO::new)
                .collect(Collectors.toList());
    }
}
