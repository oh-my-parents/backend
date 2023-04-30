package omp.omp.domain.question.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    List<String> questionContents;

    public QuestionResponse(List<String> questionContents) {
        this.questionContents = questionContents;
    }
}
