package md.usm.book_store.services;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.models.dto.LoginUserDto;
import md.usm.book_store.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static md.usm.book_store.security.jwt.JwtUtil.generateToken;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User saveOrUpdateUser(User user){
        if(user.getUser_id() == null){
            user.setUser_id(UUID.randomUUID());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public User findUserById(UUID user_id){
        return userRepository.findById(user_id).orElse(new User());
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(UUID user_id){
        userRepository.deleteById(user_id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(new User());
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(new User());
    }
}
