package com.example.cellphones.service.impl;
import com.example.cellphones.dto.UserDto;
import com.example.cellphones.dto.request.user.CreateUserReq;
import com.example.cellphones.dto.request.user.UpdateUserReq;
import com.example.cellphones.exception.UserNotFoundByIdException;
import com.example.cellphones.exception.UserNotFoundByUsername;
import com.example.cellphones.mapper.UserMapper;
import com.example.cellphones.model.Cart;
import com.example.cellphones.model.Role;
import com.example.cellphones.model.User;
import com.example.cellphones.repository.UserRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final private UserRepository userRepo;

    final private PasswordEncoder passwordEncoder;

    @Override
    public ResponseObject<List<UserDto>> getUsers() {
        ResponseObject<List<UserDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<User> users = this.userRepo.findAll();
        res.setData(users.stream().map(UserMapper::responseUserDtoFromModel).collect(Collectors.toList()));
        return res;
    }

    @Override
    public ResponseObject<UserDto> getUserInfo(Long id) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        User user = this.userRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundByIdException(id));
        res.setData(UserMapper.responseUserDtoFromModel(user));
        return res;
    }

    @Override
    public boolean createUser(CreateUserReq request) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .build();
            user.setCart(Cart.builder()
                    .user(user)
                    .build());
            user = this.userRepo.save(user);
            res.setData(UserMapper.responseUserDtoFromModel(user));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //return ;
    }

    @Override
    public boolean activeAccount(String username) {
        try {
            User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundByUsername(username));
            user.setEnabled(true);
            this.userRepo.saveAndFlush(user);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean setRoleForUser(String username, Role role) {
        try{
            User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundByUsername(username));
            user.setRole(role);
            this.userRepo.saveAndFlush(user);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

//    @Override
//    public UserDto findByUsername(String username) {
//        User user = this.userRepo.findByUsername(username);
//        return UserMapper.responseUserDtoFromModel(user);
//    }

    @Override
    public ResponseObject<UserDto> updateUser(UpdateUserReq request, Long userId) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new UserNotFoundByIdException(userId));
        res.setData(UserMapper.responseUserDtoFromModel(user));
        return res;
    }

    @Override
    public boolean deleteUser(String username) {
        try {
            this.userRepo.deleteUserByUsername(username);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
