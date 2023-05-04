package omp.omp.domain.choice.domain;

import lombok.*;
import omp.omp.domain.choice.application.ChoiceGroup;
import omp.omp.domain.question.domain.Question;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Choice {

    @Id
    @GeneratedValue
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private int number;

    private String content;

    // 연관 관계 메소드
    public void setQuestion(Question question) {
        this.question = question;
    }

    public static Choice createChoiceByChoiceGroup(ChoiceGroup choiceGroup) {

        return Choice.builder()
                .number(choiceGroup.getNumber())
                .content(choiceGroup.getAnswer())
                .build();
    }
}
