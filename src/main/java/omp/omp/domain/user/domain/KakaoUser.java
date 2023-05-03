package omp.omp.domain.user.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("K")
@SuperBuilder   //상속 관계인 KakaoUser에서도 Builder 패턴을 사용하기 위해 SuperBuilder로 변경
public class KakaoUser extends User {
    private String kakaoId; //kakao 로그인을 할 때 넘겨받는 kakao 자체의 Id 값을 저장
    //이렇게 함으로써 omp 프로젝트 내 자체 userId와 각 소셜 로그인 간 고유한 id 값을 구분할 수 있음
}
