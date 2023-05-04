package omp.omp.domain.question.dao;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.domain.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager em;

    public void save(Question question) {
        em.persist(question);
    }

    public List<Question> findAll() {
        return em.createQuery("select distinct q from Question q" +
                        " left join fetch q.choices c", Question.class)
                .getResultList();
    }
}
