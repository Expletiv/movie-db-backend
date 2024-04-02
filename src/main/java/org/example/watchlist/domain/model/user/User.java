package org.example.watchlist.domain.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Map;

public record User(@Id String id, String name, String preferredUsername, @Version Integer version) {

    public static User fromTokenAttributes(Map<String, Object> tokenAttributes) {
        return new User(
                (String) tokenAttributes.get("oid"),
                (String) tokenAttributes.get("name"),
                (String) tokenAttributes.get("preferred_username"),
                null);
    }

}
