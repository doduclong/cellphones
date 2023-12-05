package com.example.cellphones.controller;

import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.UserDto;
import com.example.cellphones.dto.request.user.CreateUserReq;
import com.example.cellphones.dto.request.user.SetRoleReq;
import com.example.cellphones.dto.request.user.UpdateUserReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/info")
    public ResponseEntity<?> getUserInfo() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<UserDto> res = userService.getUserInfo(currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getUsers() {
        ResponseObject<List<UserDto>> res = userService.getUsers();
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserReq req) {
        boolean res = userService.createUser(req);
        if(res)
            return ResponseEntity.ok("Gửi yêu cầu tài khoản thành công");
        else
            return ResponseEntity.ok("Gửi yêu cầu tài khoản thất bại");
    }

    @PostMapping(path = "/active/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> activeAccount(@PathVariable String username) {
        boolean res = userService.activeAccount(username);
        if(res)
            return ResponseEntity.ok("Kích hoạt tài khoản thành công");
        else
            return ResponseEntity.ok("Kích hoạt tài khoản thất bại");
    }

    @PostMapping(path = "/setRole")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> setRole(@RequestBody SetRoleReq req) {
        boolean res = userService.setRoleForUser(req.getUsername(), req.getRole());
        if(res)
            return ResponseEntity.ok("Thiết lập vai trò thành công");
        else
            return ResponseEntity.ok("Thiết lập vai trò thất bại");
    }

    @PostMapping(path = "/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq req, @PathVariable String userId) {
        ResponseObject<UserDto> res = userService.updateUser(req, Long.parseLong(userId));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/delete/{username}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        boolean res = userService.deleteUser(username);
        if(res)
            return ResponseEntity.ok("Xóa tài khoản thành công");
        else
            return ResponseEntity.ok("Xóa tài khoản thất bại");
    }
}
