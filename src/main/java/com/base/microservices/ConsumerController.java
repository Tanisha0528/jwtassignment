package com.base.microservices;


import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    UserRestConsumer restConsumer;

    @Autowired
    AuthenticationConsumer authenticationConsumer;

    @GetMapping("/getAllUsers")
    List<User> getUsers(){
        System.out.println(restConsumer.getClass().getSimpleName());
        System.out.println(" from admin-service");
        return restConsumer.getUsers();
    }

    @PostMapping("/addUser")
    public String signup(@RequestBody User user){
        return restConsumer.signup(user);
    }

    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map){

        String initial_response =  restConsumer.login(map);

        if(!initial_response.contains("data")) return initial_response;
        int id_index = initial_response.indexOf("id")+5;
        String id = initial_response.substring(id_index,initial_response.indexOf(",", id_index));
        String token = createToken(Integer.parseInt(id));
        StringBuilder response = new StringBuilder(initial_response);
        int token_index = initial_response.indexOf('}', id_index)-4;
        response.insert(token_index, ",\n"+"       token : "+token);
        return response.toString();

    }
    @GetMapping("/getToken/{id}")
    String createToken(@PathVariable("id") int id){
        return authenticationConsumer.createToken(id);
    }

}
