package omp.omp.domain.user.application;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.dao.UserRepository;
import omp.omp.domain.user.domain.KakaoUser;
import omp.omp.domain.user.domain.User;
import omp.omp.domain.user.exception.KakaoUserException;
import omp.omp.domain.user.exception.KakaoUserExceptionGroup;
import omp.omp.domain.user.exception.UserException;
import omp.omp.domain.user.exception.UserExceptionGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoUserService {

    private final UserRepository userRepository;

    @Transactional
    public void signInByKakao(String token) {
        //무엇보다 가장 먼저 kakaoSignIn 실시! ??? 어라 이상하다 우리 처음에 JWT 토큰 가지고 있어서 자동 로그인 기능이랑 어떻게 구분하지?? 고민에 빠졌었다
        //하지만 signInByKakao()가 호출된다는 것, 즉 프론트엔드 기준으로 카카오 로그인 페이지가 뜬다는 것은 기존 가입한 회원이라고 하더라도
        //JWT 토큰이 비정상적이거나 만료되었다는 것을 의미한다. 왜냐하면 JWT가 유효하다면 로그인 페이지로 리다이렉션 될리가 없기 때문이다.
        //그러므로 signInByKakao에서 해줘야 될 것은 기가입자인지 아닌지만 판별하고 기가입자면 새로 디비에 회원가입을 하지 말고 JWT 토큰만 재발행해주고
        //기존의 가입자가 아니라면 회원가입 및 JWT 토큰 발행을 모두 해줘야한다. 카카오 아이디 없으면 signUpByKakao 호출하면 된다.

        Map kakaoResponse = kakaoSignIn(token);


    }

    private Long signUpByKakao(Map<String, String> kakaoResponse) {
        KakaoUser kakaoUser = KakaoUser.builder()
                .name(kakaoResponse.get("nickname"))
                .kakaoId(kakaoResponse.get("kakaoId"))
                .build();
        userRepository.save(kakaoUser);
        return kakaoUser.getId();
    }

    private Map kakaoSignIn(String token) { //전달 받은 kakao AccessToken을 가지고 카카오 서버를 거쳐 kakaoId와 nickname을 얻어온다
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        Map kakaoResponse = new HashMap<String, String>();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new KakaoUserException(KakaoUserExceptionGroup.KAKAO_USER_NULL);
            }

            System.out.println("responseCode: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body: " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String kakaoId = element.getAsJsonObject().get("id").getAsString();
            String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();

            if (nickname == null || nickname.isEmpty()) {
                throw new KakaoUserException(KakaoUserExceptionGroup.KAKAO_USER_NICKNAME_NULL);
            }

            kakaoResponse.put("kakaoId", kakaoId);
            kakaoResponse.put("nickname", nickname);

            System.out.println("kakaoId: " + kakaoId);
            System.out.println("nickname: " + nickname);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kakaoResponse;
    }

    private boolean isDuplicatedKakaoUser(KakaoUser kakaoUser) {
        List<KakaoUser> findKakaoUsers = userRepository.findByKakaoId(kakaoUser.getKakaoId());
        if (!findKakaoUsers.isEmpty()) {
            return true;
        } else{
            return false;
        }
    }

}
