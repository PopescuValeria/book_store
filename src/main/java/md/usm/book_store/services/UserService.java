package md.usm.book_store.services;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User saveOrUpdateUser(User user){
        user.setUser_id(UUID.randomUUID());
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

    public User findByUsername(String email){
        return userRepository.findByEmail(email);
    }
}
