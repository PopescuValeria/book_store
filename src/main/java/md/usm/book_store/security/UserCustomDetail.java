package md.usm.book_store.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import md.usm.book_store.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCustomDetail implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";
    private final UUID id;
    private final String username;
    private final String email;

    @JsonIgnore
    private final String password;
    @Setter
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public static UserCustomDetail build(User user) {
        UserCustomDetail userCustomDetail = new UserCustomDetail(user.getUser_id(), user.getUsername(),
                user.getEmail(), user.getPassword());
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole_id()));
        userCustomDetail.setAuthorities(authorities);
        return userCustomDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
