package omp.omp.domain.user.dao;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.domain.KakaoUser;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public Optional<User> findByParentType(Long id, ParentType parentType) {
        List<User> result = em.createQuery("select u from User u" +
                        " left join u.userQuestions uq" +
                        " on uq.parentType = :parentType" +
                        " where u.id = :id", User.class)
                .setParameter("id", id)
                .setParameter("parentType", parentType)
                .getResultList();

        return result.stream().findAny();
    }

    public List<KakaoUser> findByKakaoId(String kakaoId) {   //LoginId를 통해 모든 Customer 조회
        return em.createQuery("select k from KakaoUser k where k.kakaoId = :kakaoId", KakaoUser.class)    //:loginId라고 하여 밑에서 setParameter를 통해 "loginId"의 value와 바인딩
                .setParameter("kakaoId", kakaoId)   //위에 있는 :loginId가 여기 파리미터의 Key값과 바인딩 되어서 value가 위로 넘어가게 됌
                .getResultList();
    }

    public Optional<User> findById(String username) {
        List<User> result = em.createQuery("select u from User u" +
                        " where u.id = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }
}
