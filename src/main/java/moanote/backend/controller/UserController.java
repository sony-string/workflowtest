package moanote.backend.controller;

import moanote.backend.entity.UserData;
import moanote.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public UserData registerUser(Map<String, String> userData) {
    // TODO@ 적절한 DTO 객체를 받도록 변경
    return userService.createUser(userData.get("username"), userData.get("password"));
  }
}