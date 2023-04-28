package omp.omp.domain.user;

import lombok.*;
import omp.omp.domain.userquestion.UserQuestion;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserQuestion> userQuestions = new ArrayList<>();

    @NotNull
    private String name;

    @NotNull
    private String uri;
}