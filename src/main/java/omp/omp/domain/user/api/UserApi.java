package omp.omp.domain.user.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.application.KakaoUserService;
import omp.omp.domain.user.application.UserService;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.dto.*;
import omp.omp.global.util.Result;
import omp.omp.global.util.SecurityUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @PostMapping("/api/v1/user/score")
    public Result<UserScoreResponse> confirmScore(@RequestBody UserScoreRequest userScoreRequest) {

        UserScoreResponse userScoreResponse = new UserScoreResponse(userService.confirmScore(SecurityUtil.getCurrentUserId(), userScoreRequest.getParentType()));
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userScoreResponse);
    }

    @PostMapping("/api/v1/auth/kakao")
    public Result<TokenResponse> kakaoAuth(@RequestBody KakaoAuthRequest kakaoAuthRequest) {
        TokenResponse tokenResponse = kakaoUserService.signInByKakao(kakaoAuthRequest.getToken());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, tokenResponse);
    }

    @PatchMapping("/api/v1/user/name")
    public Result<String> updateUserName(@RequestBody UpdateUserNameRequest updateUserNameRequest) {
        String currentUserId = SecurityUtil.getCurrentUserId();
        System.out.println(currentUserId);
        userService.updateUserName(currentUserId, updateUserNameRequest.getName());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, null);
    }

    @GetMapping("/api/v1/user/name")
    public Result<String> findUserName() {
        String currentUserId = SecurityUtil.getCurrentUserId();
        String userName = userService.findUserName(currentUserId);
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userName);
    }

    @DeleteMapping("/api/v1/user")
    public Result<String> deleteUser() {
        String currentUserId = SecurityUtil.getCurrentUserId();
        userService.deleteUser(currentUserId);
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, null);
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
        String currentUserId = SecurityUtil.getCurrentUserId();
        System.out.println(currentUserId);
        return currentUserId;
    }

    @PostMapping("/api/v1/user/result")
    public Result<UserResultResponse> confirmResult(@RequestBody UserResultRequest userResultRequest) {

        User user = userService.confirmResult(SecurityUtil.getCurrentUserId(), userResultRequest.getParentType());

        UserResultResponse userResultResponse = new UserResultResponse(user);
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userResultResponse);
    }

    @PostMapping("/api/v1/child/answer")
    public Result<String> saveChildAnswer(@RequestBody UserChildAnswerRequest userChildAnswerRequest) {

        userService.saveChildAnswer(SecurityUtil.getCurrentUserId(), userChildAnswerRequest.getParentType(), userChildAnswerRequest.getUserChildAnswers());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, null);
    }

    @PostMapping("/api/v1/parent/answer")
    public Result<String> saveParentAnswer(@RequestBody UserParentAnswerRequest userParentAnswerRequest) {

        userService.saveParentAnswer(SecurityUtil.getCurrentUserId(), userParentAnswerRequest.getParentType(), userParentAnswerRequest.getUserParentAnswers());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, null);
    }

    @PostMapping("/api/v1/question/child/answer")
    public Result<UserQuestionWithChildAnswerResponse> findQuestionWithChildAnswer(@RequestBody UserQuestionWithChildAnswerRequest userQuestionWithChildAnswerRequest) {

        UserQuestionWithChildAnswerResponse userQuestionWithChildAnswerResponse = new UserQuestionWithChildAnswerResponse(
                userService.findQuestionWithChildAnswer(SecurityUtil.getCurrentUserId()
                        , userQuestionWithChildAnswerRequest.getParentType()));

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userQuestionWithChildAnswerResponse);
    }
}
