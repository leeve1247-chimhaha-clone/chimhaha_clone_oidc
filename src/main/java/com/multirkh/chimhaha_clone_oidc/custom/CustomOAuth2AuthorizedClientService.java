package com.multirkh.chimhaha_clone_oidc.custom;

import com.multirkh.chimhaha_clone_oidc.user.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {
    private final UserEntityRepository userEntityRepository;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
    }
}
