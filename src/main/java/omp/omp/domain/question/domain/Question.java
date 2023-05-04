package omp.omp.domain.question.domain;

import lombok.*;
import omp.omp.domain.choice.application.ChoiceGroup;
import omp.omp.domain.choice.domain.Choice;
import omp.omp.domain.question.application.QuestionGroup;
import omp.omp.domain.userquestion.domain.ParentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Choice> choices = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    public String plusParentType(ParentType parentType) {
        return String.format(this.content, parentType.getParent());
    }

    public void addChoice(Choice choice) {
        choices.add(choice);
        choice.setQuestion(this);
    }

    public static Question createQuestionByQuestionGroup(QuestionGroup questionGroup) {
        // 빌더 패턴 사용이 choices 가 new ArrayList 로 초기화 되지않습니다.. 왤까요..
        Question question = Question.builder()
                .content(questionGroup.getMessage())
                .id(questionGroup.getOrder())
                .questionType(questionGroup.getQuestionType())
                .choices(new ArrayList<>())
                .build();

        List<ChoiceGroup> choiceGroups = questionGroup.getChoiceGroups();
        for (ChoiceGroup choiceGroup : choiceGroups) {
            if (choiceGroup != ChoiceGroup.NOTHING) {
                question.addChoice(Choice.createChoiceByChoiceGroup(choiceGroup));
            }
        }

        return question;
    }
}
