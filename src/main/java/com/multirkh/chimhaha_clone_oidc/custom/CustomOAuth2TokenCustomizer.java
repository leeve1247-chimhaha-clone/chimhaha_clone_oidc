package com.multirkh.chimhaha_clone_oidc.custom;

import com.multirkh.chimhaha_clone_oidc.user.UserEntity;
import com.multirkh.chimhaha_clone_oidc.user.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.time.Instant;


@Configuration
@RequiredArgsConstructor
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final UserEntityRepository userEntityRepository;

    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            String principalName = context.getAuthorization().getPrincipalName();
            System.out.println("principalName = " + principalName);
            UserEntity byUserId = userEntityRepository.findByUserId(principalName);
            context.getClaims().claims((claims) -> {
                claims.put("roles", byUserId.getUserRole().toString());
                Instant iat = (Instant) claims.get("iat");
                claims.put("exp", iat.plusSeconds(15));
            });
        }
    }
}
