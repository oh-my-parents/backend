package omp.omp.domain.user.application;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.dto.TokenResponse;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import omp.omp.domain.user.jwt.JwtTokenProvider;
import omp.omp.domain.userquestion.domain.ParentType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Transactional
    public TokenResponse signIn(String id, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
}
