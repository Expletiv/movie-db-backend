package org.example.watchlist.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RestController
public class RegistrationController {

    private final static String INVITATION_ENDPOINT = "https://graph.microsoft.com/v1.0/invitations";

    @Value("${FRONTEND_ANGULAR_URL}")
    private String redirectURL;

    private final WebClient webClient = WebClient.create(INVITATION_ENDPOINT);

    public record RegistrationRequest(
            String email,
            String name) {
    }

    public record Invitation(
            String invitedUserDisplayName,
            String invitedUserEmailAddress,
            Object invitedUserMessageInfo,
            Boolean sendInvitationMessage,
            String inviteRedirectUrl,
            String inviteRedeemUrl,
            Boolean resetRedemption,
            String status,
            Object invitedUser,
            String invitedUserType
    ) {
    }

    private record Registration(
            String invitedUserDisplayName,
            String invitedUserEmailAddress,
            String inviteRedirectUrl
    ) {
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Invitation sendRegistration(@RequestBody RegistrationRequest request,
                                       @RegisteredOAuth2AuthorizedClient("graph-invitation") OAuth2AuthorizedClient graphClient) {
        OAuth2AccessToken accessToken = graphClient.getAccessToken();

        Registration registration = new Registration(request.name, request.email, redirectURL);

        return webClient.post()
                .header("Authorization", "Bearer %s".formatted(accessToken.getTokenValue()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registration)
                .retrieve()
                .bodyToMono(Invitation.class)
                .block(Duration.ofSeconds(30));
    }

}
