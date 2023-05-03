package omp.omp.domain.userquestion.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentAnswer {

    @Column(columnDefinition = "TEXT")
    private String parentContent;

    /**
     * 원시형이 아닌 래퍼형을 쓴 이유는 다음과 같습니다.
     * 원시형의 경우 null 값을 저장하기 못하기에 기본값을 저장하는것이 중요합니다.
     * 하지만 기본으로 0 으로 저장할시 부모님 채점결과 0 점이 나왔을때를 겹치는 문제가있습니다.
     * 그래서 null 로 저장을 해서 채점을 했는지 안했는지 구분할려고합니다.
     * 이 방법이 아닌 다른 방법을 원한다면 채점을 했을때 안했을때의 상태를 검사하는 Status 클래스를 사용할 수 있습니다.
     */
    private Integer score;

    public ParentAnswer(Integer score) {
        this.score = score;
    }
}
