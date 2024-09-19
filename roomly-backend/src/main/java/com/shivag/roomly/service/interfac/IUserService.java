package com.shivag.roomly.service.interfac;
import com.shivag.roomly.dto.LoginRequest;
import com.shivag.roomly.dto.Response;
import com.shivag.roomly.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

} // methods will be defined in our UserService Class
