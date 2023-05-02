package omp.omp.domain.user.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.application.QuestionService;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.dto.UserChildAnswer;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import omp.omp.domain.userquestion.domain.ParentType;
import omp.omp.domain.userquestion.domain.UserQuestion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final QuestionService questionService;


    public int confirmScore(Long userId, ParentType parentType) {

        Optional<User> optionalUser = userRepository.findByParentType(userId, parentType);

        User user = checkUserNullAndGetUser(optionalUser);
        checkMadeQuestion(user);
        checkScoreNull(user);

        return user.getTotalScore();
    }

    private User checkUserNullAndGetUser(Optional<User> user) {
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException(UserExceptionGroup.USER_NULL);
    }

    private void checkScoreNull(User user) {
        if (user.isScoreNull()) {
            throw new UserException(UserExceptionGroup.USER_SCORE_NULL);
        }
    }

    private void checkMadeQuestion(User user) {
        if (!user.isMadeUserQuestion()) {
            throw new UserException(UserExceptionGroup.USER_QUESTION_NULL);
        }
    }

    @Transactional
    public Long saveChildAnswer(Long userId, ParentType parentType, List<UserChildAnswer> userChildAnswers) {

        Optional<User> optionalUser = userRepository.findByParentType(userId, parentType);
        User user = checkUserNullAndGetUser(optionalUser);


        List<Question> questions = questionService.findSortedQuestion();

        List<UserQuestion> userQuestions = user.getUserQuestions();

        if (user.isMadeUserQuestion()) {
            updateUserQuestion(userChildAnswers, user, questions);
        } else {
            createUserQuestion(parentType, userChildAnswers, user, questions, userQuestions);
        }

        return user.getId();
    }

    private void createUserQuestion(ParentType parentType, List<UserChildAnswer> userChildAnswers, User user, List<Question> questions, List<UserQuestion> userQuestions) {
        // UserQuestion 생성
        for (UserChildAnswer userChildAnswer : userChildAnswers) {
            Question question = QuestionMatchedNumber(questions, userChildAnswer);

            userQuestions.add(UserQuestion.createUserQuestion(userChildAnswer.getAnswer(), parentType, question, user));
        }
    }

    private void updateUserQuestion(List<UserChildAnswer> userChildAnswers, User user, List<Question> questions) {
        // UserQuestion 업데이트
        for (UserChildAnswer userChildAnswer : userChildAnswers) {
            Question question = QuestionMatchedNumber(questions, userChildAnswer);

            user.findUserQuestionByQuestionId(question.getId()).updateUserQuestionWithChildAnswer(userChildAnswer.getAnswer());
        }
    }

    private Question QuestionMatchedNumber(List<Question> questions, UserChildAnswer userChildAnswer) {
        return questions.stream()
                .filter(q -> q.getId() == userChildAnswer.getNumber())
                .findFirst()
                .orElseThrow(() -> new UserException(UserExceptionGroup.USER_QUESTION_NULL));
    }

}
