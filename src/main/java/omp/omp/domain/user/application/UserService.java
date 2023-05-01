package omp.omp.domain.user.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public int confirmScore(Long userId, ParentType parentType) {

        Optional<User> optionalUser = userRepository.findByParentType(userId, parentType);

        User user = checkUserNullAndGetUser(optionalUser);
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
}
