package com.multirkh.chimhaha_clone_oidc.custom;

import com.multirkh.chimhaha_clone_oidc.utils.IdGenerator;
import com.multirkh.chimhaha_clone_oidc.user.UserEntity;
import com.multirkh.chimhaha_clone_oidc.user.UserEntityRepository;
import com.multirkh.chimhaha_clone_oidc.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserEntityRepository userEntityRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> originalAttributes = oAuth2User.getAttributes();
        Map<String, Object> attributes = new HashMap<>(originalAttributes);
        System.out.println("attributes = " + attributes);
        String clientName = userRequest.getClientRegistration().getClientName();
        if (clientName.equals("naver")){
            LinkedHashMap<String, Object> response = (LinkedHashMap<String, Object>) attributes.get("response");
            String id = (String)response.getOrDefault("id", "");

            UserEntity foundUser = userEntityRepository.findByNaverId(id);
            if (foundUser == null){
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(IdGenerator.generateUniqueId());
                userEntity.setNaverId(id);
                userEntity.setUserRole(UserRole.USER);
                userEntityRepository.save(userEntity);
                foundUser = userEntity;
            }
            attributes.put("customId", foundUser.getUserId());
        }
        else if (clientName.equals("github")) {
            UserEntity foundUser = userEntityRepository.findByGithubId(oAuth2User.getName());
            if (foundUser == null) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(IdGenerator.generateUniqueId());
                userEntity.setGithubId(oAuth2User.getName());
                userEntity.setUserRole(UserRole.USER);
                userEntityRepository.save(userEntity);
                foundUser = userEntity;
            }
            attributes.put("customId", foundUser.getUserId());
        }
        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                attributes,
                "customId"
        );
    }
}
