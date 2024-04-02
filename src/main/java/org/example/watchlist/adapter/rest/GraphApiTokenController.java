package org.example.watchlist.adapter.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('SCOPE_Watchlist.User')")
public class GraphApiTokenController {

    @GetMapping("/graph/token")
    public OAuth2AccessToken graph(@RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphClient) {
        return graphClient.getAccessToken();
    }

}
