package com.base.microservices;

import com.base.microservices.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient("admin-service/user")
public interface UserRestConsumer {

    @PostMapping("/addUser")
    String signup(@RequestBody User user);

    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map);


    @GetMapping("/getAllUsers")
    List<User> getUsers();


}
