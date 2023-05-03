package omp.omp.domain.user.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.application.QuestionService;
import omp.omp.domain.question.domain.Question;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.dto.UserChildAnswer;
import omp.omp.domain.user.dto.UserParentAnswer;
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
        checkParentAnswered(user);

        return user.getTotalScore();
    }

    public User confirmResult(Long userId, ParentType parentType) {

        Optional<User> optionalUser = userRepository.findByParentType(userId, parentType);

        User user = checkUserNullAndGetUser(optionalUser);
        checkMadeQuestion(user);
        checkParentAnswered(user);

        return user;
    }

    private User checkUserNullAndGetUser(Optional<User> user) {
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException(UserExceptionGroup.USER_NULL);
    }

    private void checkParentAnswered(User user) {
        if (user.isParentAnswerNull()) {
            throw new UserException(UserExceptionGroup.USER_SCORE_NULL);
        }
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

        if (user.isMadeUserQuestion()) {
            updateUserQuestionWithChildAnswer(userChildAnswers, user);
        } else {
            List<Question> questions = questionService.findSortedQuestion();
            createUserQuestionWithChildAnswer(parentType, userChildAnswers, user, questions, user.getUserQuestions());
        }

        return user.getId();
    }

    private void createUserQuestionWithChildAnswer(ParentType parentType, List<UserChildAnswer> userChildAnswers, User user, List<Question> questions, List<UserQuestion> userQuestions) {
        // UserQuestion 생성
        for (UserChildAnswer userChildAnswer : userChildAnswers) {
            Question question = QuestionMatchedNumber(questions, userChildAnswer);

            userQuestions.add(UserQuestion.createUserQuestion(userChildAnswer.getAnswer(), parentType, question, user));
        }
    }

    private void updateUserQuestionWithChildAnswer(List<UserChildAnswer> userChildAnswers, User user) {
        // UserQuestion 업데이트
        for (UserChildAnswer userChildAnswer : userChildAnswers) {

            UserQuestion userQuestionByQuestionId = user.findUserQuestionByQuestionId((long) userChildAnswer.getNumber());
            userQuestionByQuestionId.updateUserQuestionWithChildAnswer(userChildAnswer.getAnswer());
        }
    }

    private Question QuestionMatchedNumber(List<Question> questions, UserChildAnswer userChildAnswer) {
        return questions.stream()
                .filter(q -> q.getId() == userChildAnswer.getNumber())
                .findFirst()
                .orElseThrow(() -> new UserException(UserExceptionGroup.USER_QUESTION_NULL));
    }

    @Transactional
    public Long saveParentAnswer(Long userId, ParentType parentType, List<UserParentAnswer> userParentAnswers) {

        Optional<User> optionalUser = userRepository.findByParentType(userId, parentType);
        User user = checkUserNullAndGetUser(optionalUser);

        if (user.isMadeUserQuestion()) {
            updateUserQuestionWithParentAnswer(userParentAnswers, user);
        }

        return user.getId();
    }

    private void updateUserQuestionWithParentAnswer(List<UserParentAnswer> userParentAnswers, User user) {
        // UserQuestion 업데이트
        for (UserParentAnswer userParentAnswer : userParentAnswers) {

            UserQuestion userQuestionByQuestionId = user.findUserQuestionByQuestionId((long) userParentAnswer.getNumber());
            userQuestionByQuestionId.updateUserQuestionWithParentAnswer(userParentAnswer.getScore());
        }
    }
}
