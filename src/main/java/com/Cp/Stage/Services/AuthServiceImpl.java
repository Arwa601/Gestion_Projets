// package com.Cp.Stage.Services;
// import com.Cp.Stage.DTOs.SignupRequest;
// import com.Cp.Stage.DTOs.UserDTO;
// import com.Cp.Stage.Models.User;
// import com.Cp.Stage.Repositories.UserRepo;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthServiceImpl implements AuthService {

//     @Autowired
//     private UserRepo userRepository;


//     @Override
//     public UserDTO createUser(SignupRequest signupRequest) {
//         User user = new User();
//         user.setNom(signupRequest.get());
//         user.setEmail(signupRequest.getEmail());
//         user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
//         User createdUser = userRepository.save(user);
//         UserDTO userDTO = new UserDTO();
//         userDTO.setId(createdUser.getId());
//         userDTO.setEmail(createdUser.getEmail());
//         userDTO.setNom(createdUser.getNom());
//         return userDTO;
//     }
// }


