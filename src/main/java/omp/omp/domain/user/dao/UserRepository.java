package omp.omp.domain.user.dao;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
                        " join fetch u.userQuestions uq" +
                        " where u.id = :id" +
                        " and uq.parentType = :parentType", User.class)
                .setParameter("id", id)
                .setParameter("parentType", parentType)
                .getResultList();

        return result.stream().findAny();
    }
}
