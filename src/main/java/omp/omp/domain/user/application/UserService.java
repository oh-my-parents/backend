package omp.omp.domain.user.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public int confirmScore(Long userId) {

        User user = userRepository.findOne(userId);

        checkUserNull(user);
        checkMadeQuestion(user);
        checkScoreNull(user);

        return user.getTotalScore();
    }

    private void checkMadeQuestion(User user) {
        if (!user.isMadeQuestion()) {
            throw new UserException(UserExceptionGroup.USER_NO_MADE_QUESTION);
        }
    }

    private void checkUserNull(User user) {
        if (user == null) {
            throw new UserException(UserExceptionGroup.USER_NULL);
        }
    }

    private void checkScoreNull(User user) {
        if (user.isScoreNull()) {
            throw new UserException(UserExceptionGroup.USER_SCORE_NULL);
        }
    }
}
