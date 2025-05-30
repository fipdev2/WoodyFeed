package com.woodyfeed.quiz.Http.Responses;

import com.woodyfeed.quiz.User.Role;
import com.woodyfeed.quiz.User.User;
import com.woodyfeed.quiz.util.Client;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Client
public class UserResponse {
    private Long id;
    private Role role;
    @Nullable
    private String name;
    private String email;
    private List<String> authorities = new ArrayList<>();

    public UserResponse(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UserResponse(User user, Collection<? extends GrantedAuthority> authorities) {
        this.id = user.getId();
        this.role = user.getRole();
        this.name = user.getName();
        this.email = user.getEmail();
        authorities.forEach(authority -> {
            this.authorities.add(authority.getAuthority());
        });
    }

    public record ConnectedAccountResponse(LocalDateTime connectedAt) {
    }
}
