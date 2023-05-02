package omp.omp.domain.userquestion.domain;

import lombok.*;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserQuestion {

    @Id
    @GeneratedValue
    @Column(name = "user_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ParentType parentType;

    @Embedded
    private ChildAnswer childAnswer;

    @Embedded
    private ParentAnswer parentAnswer;

    // 연관관계 메소드
    public void setUser(User user) {
        this.user = user;
    }

    public static UserQuestion createUserQuestion(String answer, ParentType parentType, Question question, User user) {

        UserQuestion userQuestion = UserQuestion.builder()
                .question(question)
                .user(user)
                .parentType(parentType)
                .childAnswer(new ChildAnswer(answer))
                .build();

        user.getUserQuestions().add(userQuestion);
        return userQuestion;
    }

    public void updateUserQuestionWithChildAnswer(String answer) {
        this.childAnswer = new ChildAnswer(answer);
    }
}
