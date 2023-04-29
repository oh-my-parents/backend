package omp.omp.domain.question.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.dao.QuestionRepository;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long makeQuestion(String content, int orderNumber) {
        Question question = Question.builder()
                .content(content)
                .orderNumber(orderNumber)
                .build();

        questionRepository.save(question);

        return question.getId();
    }

    public List<String> findQuestionContentsWithParent(ParentType parentType) {
        List<Question> questions = questionRepository.findAll();

        sortQuestionByOrder(questions);

        return questions.stream()
                .map(m -> m.plusParentType(parentType))
                .collect(Collectors.toList());
    }

    private void sortQuestionByOrder(List<Question> questions) {
        questions.sort(Comparator.comparingInt(Question::getOrderNumber));
    }

}
