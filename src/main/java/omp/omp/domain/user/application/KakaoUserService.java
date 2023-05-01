package omp.omp.domain.user.application;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoUserService {

    private Map kakaoSignIn(String token) {
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

}
