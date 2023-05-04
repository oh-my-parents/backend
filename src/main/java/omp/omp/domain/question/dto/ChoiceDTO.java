package omp.omp.domain.question.dto;

import lombok.Data;
import omp.omp.domain.choice.domain.Choice;

@Data
public class ChoiceDTO {

    private int number;

    private String content;

    public ChoiceDTO(Choice choice) {
        this.number = choice.getNumber();
        this.content = choice.getContent();
    }
}
