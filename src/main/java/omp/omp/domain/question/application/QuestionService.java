package omp.omp.domain.question.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.dao.QuestionRepository;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findQuestionContentsWithParent(ParentType parentType) {

        return findSortedQuestion();
    }

    public List<Question> findSortedQuestion() {
        List<Question> questions = questionRepository.findAll();

        sortQuestionByOrder(questions);

        return questions;
    }

    private void sortQuestionByOrder(List<Question> questions) {
        questions.sort(Comparator.comparingLong(Question::getId));
    }
}
