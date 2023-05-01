package omp.omp.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import omp.omp.domain.userquestion.domain.UserQuestion;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//추후 확장성 고려 및 객체 간 응집력을 높이기 위해 Kakao를 별도로 나눠봤습니다.
//상속을 하려면 상속 관계 전략을 부모 클래스에 반드시 잡아줘야 한다. JOINED는 가장 정규화된 상태로 하는 것, SINGLE_TABLE은 한 테이블에 다 합치는 거, TABLE_PER_CLASS 는 클래스 별로 테이블 나누는 것
@DiscriminatorColumn(name = "dtype") //싱글 테이블 전략이다 보니까 서로 다른 소셜 로그인끼리 구분할 수 있게 컬럼을 만들어주는 것
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
}
