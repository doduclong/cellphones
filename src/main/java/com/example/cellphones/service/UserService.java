package com.example.cellphones.service;

import com.example.cellphones.dto.UserDto;
import com.example.cellphones.dto.request.user.CreateUserReq;
import com.example.cellphones.dto.request.user.UpdateUserReq;
import com.example.cellphones.model.Role;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface UserService {
    ResponseObject<List<UserDto>> getUserList();
    ResponseObject<UserDto> getUserInfo(Long id);
    boolean createUser(CreateUserReq request);

    boolean activeAccount(String username);

    boolean setRoleForUser(String username, Role role);

    //UserDto findByUsername(String username);
    ResponseObject<UserDto> updateUser(UpdateUserReq request);
    boolean deleteUser(String username);
}
