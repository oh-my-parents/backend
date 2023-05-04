package omp.omp.domain.question.dto;

import lombok.Data;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.question.domain.QuestionType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionDTO {

    private Long number;

    private String content;

    private List<ChoiceDTO> choices;

    private QuestionType questionType;

    public QuestionDTO(Question question) {
        this.number = question.getId();
        this.content = question.getContent();
        this.questionType = question.getQuestionType();

        this.choices = question.getChoices().stream()
                .map(ChoiceDTO::new)
                .collect(Collectors.toList());
    }
}
