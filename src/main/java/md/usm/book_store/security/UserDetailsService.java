package md.usm.book_store.security;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                        new UsernameNotFoundException("Username not found with username:" + username));
        return UserCustomDetail.build(user);
    }
}
