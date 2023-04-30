package omp.omp.domain.user.dao;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User findOne(Long id) {
        return em.find(User.class, id);
    }
}
