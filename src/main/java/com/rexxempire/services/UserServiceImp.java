package com.rexxempire.services;


import com.rexxempire.data.models.User;
import com.rexxempire.data.repositories.UserRepository;
import com.rexxempire.dtos.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.dtos.responses.UserResponse;
import java.util.Optional;


@Service
public class UserServiceImp{
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setUsername(savedUser.getUsername());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setRole(savedUser.getRole());
        return  userResponse;
    }
    public Optional<User> login(LoginRequest loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()));
    }

//    @NonNull
//    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException{
//        return userRepository.findByUsername(email)
//                .map(user -> new org.springframework.security.core.userdetails.User(
//                        user.getEmail(),
//                        user.getPassword(),
//                        getAuthorities(user.getRole())
//                ))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return List.of(new SimpleGrantedAuthority(role));
//    }
}

//    @Override
//    public User validateUser(String email, String password) {
//        return userRepository.findByUsername(email)
//                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
//                .orElse(null);
//
//    }
