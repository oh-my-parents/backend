package omp.omp.domain.question;

import lombok.*;
import omp.omp.domain.childanswer.ChildAnswer;
import omp.omp.domain.parentanswer.ParentAnswer;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "child_answer_id")
    private ChildAnswer childAnswer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_answer_id")
    private ParentAnswer parentAnswer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private QuestionParentType questionParentType;

    @Column(columnDefinition = "TEXT")
    private String content;
}
