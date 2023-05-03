package omp.omp.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import omp.omp.domain.userquestion.domain.UserQuestion;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserQuestion> userQuestions = new ArrayList<>();

    @NotNull
    private String name;

    @NotNull
    private String uri;

    public boolean isParentAnswerNull() {
        return userQuestions.stream()
                .map(UserQuestion::getParentAnswer)
                .anyMatch(Objects::isNull);
    }

    public boolean isScoreNull() {
        return userQuestions.stream()
                .map(u -> u.getParentAnswer().getScore())
                .anyMatch(Objects::isNull);
    }

    public int getTotalScore() {
        return userQuestions.stream()
                .mapToInt(u -> u.getParentAnswer().getScore())
                .sum();
    }

    public boolean isMadeUserQuestion() {
        return userQuestions.size() != 0;
    }

    public UserQuestion findUserQuestionByQuestionId(Long id) {
        return userQuestions.stream()
                .filter(uq -> uq.getQuestion().getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserException(UserExceptionGroup.USER_QUESTION_NULL));
    }

}
