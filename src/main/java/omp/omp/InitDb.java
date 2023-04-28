package omp.omp;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.application.QuestionGroup;
import omp.omp.domain.question.application.QuestionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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

        private final QuestionService questionService;

        public void dbQuestion() {
            for (QuestionGroup questionGroup : QuestionGroup.values()) {
                questionService.makeQuestion(questionGroup.getMessage(), questionGroup.getOrder());
            }
        }
    }
}
