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

    @GetMapping("/get-users")
    List<User> getUsers(){
        System.out.println(restConsumer.getClass().getSimpleName());
        System.out.println("accessing from admin-service");
        return restConsumer.getUsers();
    }

    @PostMapping("/add-user")
    public String signup(@RequestBody User user){
        return restConsumer.signup(user);
    }

    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map){

        String initial_response =  restConsumer.login(map);

        // if email not found || password is incorrect
        if(!initial_response.contains("data")) return initial_response;

        // else insert token with the initial response
        int id_index = initial_response.indexOf("id")+5;
        String id = initial_response.substring(  id_index,
                                                    initial_response.indexOf(",", id_index));


        String token = createToken(Integer.parseInt(id));

        StringBuilder response = new StringBuilder(initial_response);
        int token_index = initial_response.indexOf('}', id_index)-4;
        response.insert(token_index, ",\n"+"       token : "+token);

        return response.toString();

    }
    @GetMapping("/get-token/{id}")
    String createToken(@PathVariable("id") int id){
        return authenticationConsumer.createToken(id);
    }

}
