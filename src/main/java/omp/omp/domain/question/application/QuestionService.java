package omp.omp.domain.question.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.dao.QuestionRepository;
import omp.omp.domain.question.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Long makeQuestion(String content, int orderNumber) {
        Question question = Question.builder()
                .content(content)
                .orderNumber(orderNumber)
                .build();

        questionRepository.save(question);

        return question.getId();
    }
}
