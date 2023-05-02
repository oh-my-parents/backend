package omp.omp.domain.userquestion.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildAnswer {

    @Column(columnDefinition = "TEXT")
    private String childContent;
}
