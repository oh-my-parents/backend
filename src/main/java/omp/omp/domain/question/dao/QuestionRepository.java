package omp.omp.domain.question.dao;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.domain.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager em;

    public void save(Question question) {
        em.persist(question);
    }

}
