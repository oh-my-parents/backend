package omp.omp.domain.question.domain;

import lombok.*;
import omp.omp.domain.userquestion.domain.ParentType;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question {

    @Id
    @Column(name = "question_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    public String plusParentType(ParentType parentType) {
        return String.format(this.content, parentType.getParent());
    }
}
