package com.example.cellphones.mapper;

import com.example.cellphones.dto.UserDto;
import com.example.cellphones.model.User;

public class UserMapper {
    public static UserDto responseUserDtoFromModel(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
