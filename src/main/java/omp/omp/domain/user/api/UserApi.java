package omp.omp.domain.user.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.application.KakaoUserService;
import omp.omp.domain.user.application.UserService;
import omp.omp.domain.user.dto.*;
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

    @PostMapping("/api/v1/auth/kakao")
    public Result<TokenResponse> kakaoAuth(@RequestBody KakaoAuthRequest kakaoAuthRequest) {
        TokenResponse tokenResponse = kakaoUserService.signInByKakao(kakaoAuthRequest.getToken());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, tokenResponse);
    }

//    @PostMapping("/login")
//    public TokenResponse signIn(@RequestBody UserSignInRequest userSignInRequest) {
//        String id = userSignInRequest.getId();
//        String password = userSignInRequest.getPassword();
//        TokenResponse tokenResponse = userService.signIn(id, password);
//        return tokenResponse;
//    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/test2")
    public String test2() {
        return "success2";
    }
}
