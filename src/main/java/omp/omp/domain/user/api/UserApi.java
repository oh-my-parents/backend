package omp.omp.domain.user.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.application.KakaoUserService;
import omp.omp.domain.user.application.UserService;
import omp.omp.domain.user.dto.TokenResponse;
import omp.omp.domain.user.dto.UserScoreRequest;
import omp.omp.domain.user.dto.UserScoreResponse;
import omp.omp.domain.user.dto.UserSignInRequest;
import omp.omp.global.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @PostMapping("/api/v1/user/score/{id}")
    public Result<UserScoreResponse> confirmScore(@PathVariable("id") Long id,
                                                  UserScoreRequest userScoreRequest) {

        UserScoreResponse userScoreResponse = new UserScoreResponse(userService.confirmScore(id, userScoreRequest.getParentType()));
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userScoreResponse);
    }

//    @PostMapping("/api/v1/auth/kakao")
//    public void kakaoAuth(@RequestParam("token") String token) {
//        kakaoUserService.kakaoSignIn(token);
//    }

    @PostMapping("/login")
    public TokenResponse signIn(@RequestBody UserSignInRequest userSignInRequest) {
        String id = userSignInRequest.getId();
        String password = userSignInRequest.getPassword();
        TokenResponse tokenResponse = userService.signIn(id, password);
        return tokenResponse;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/test2")
    public String test2() {
        return "success2";
    }
}
