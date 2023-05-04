package omp.omp;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.application.QuestionGroup;
import omp.omp.domain.question.domain.Question;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbQuestion();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbQuestion() {
            for (QuestionGroup questionGroup : QuestionGroup.values()) {
                Question question = Question.createQuestionByQuestionGroup(questionGroup);
                em.persist(question);
            }
        }
    }
}
