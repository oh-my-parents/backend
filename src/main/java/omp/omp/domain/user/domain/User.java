package omp.omp.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import omp.omp.domain.userquestion.domain.UserQuestion;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@SuperBuilder   //상속 관계인 KakaoUser에서도 Builder 패턴을 사용하기 위해 SuperBuilder로 변경
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//추후 확장성 고려 및 객체 간 응집력을 높이기 위해 Kakao를 별도로 나눠봤습니다.
//상속을 하려면 상속 관계 전략을 부모 클래스에 반드시 잡아줘야 한다. JOINED는 가장 정규화된 상태로 하는 것, SINGLE_TABLE은 한 테이블에 다 합치는 거, TABLE_PER_CLASS 는 클래스 별로 테이블 나누는 것
@DiscriminatorColumn(name = "dtype") //싱글 테이블 전략이다 보니까 서로 다른 소셜 로그인끼리 구분할 수 있게 컬럼을 만들어주는 것
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserQuestion> userQuestions = new ArrayList<>();

    @NotNull
    private String name;

//    @NotNull

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void updateUserName(String name) {
        this.name = name;
    }

}
