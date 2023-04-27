package omp.omp.domain.parentanswer;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentAnswer {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int score;
}
