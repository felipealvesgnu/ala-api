package br.org.ala.api.config.security.authserver;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getPrincipal() instanceof AuthUser) { //just for passowrd credentials flow
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            Map<String, Object> info = new HashMap<>();
            info.put("nome_completo", authUser.getFullName());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        }
        return accessToken;
    }

}
