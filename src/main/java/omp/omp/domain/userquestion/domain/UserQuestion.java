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

    /**
     * 해당 함수가 실행된다는것은 맨처음 유저가 질문에대한 답을하고
     * 두번째로 질문에 대한 답을 새로작성하는 경우입니다.
     * 따라서 parentAnswer 를 비워주어야합니다.
     */
    public void updateUserQuestionWithChildAnswer(String answer) {
        this.childAnswer = new ChildAnswer(answer);
        this.parentAnswer = null;
    }

    public void updateUserQuestionWithParentAnswer(int score) {
        this.parentAnswer = new ParentAnswer(score);
    }
}
