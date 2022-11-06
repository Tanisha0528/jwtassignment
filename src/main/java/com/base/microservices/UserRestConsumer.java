package com.base.microservices;

import com.dummy.service.dummyService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient("admin-service/user")
public interface UserRestConsumer {

    @PostMapping("/add-user")
    String signup(@RequestBody User user);

    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map);


    @GetMapping("/get-users")
    List<User> getUsers();


}
