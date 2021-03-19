package purcio.purcio.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.user.dto.UserCreateReqDTO;
import purcio.purcio.user.dto.UserUpdateReqDTO;
import purcio.purcio.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/{id}")
    public ResponseDTO<Object> retrieveUser(@PathVariable Long id){
        return userService.retrieveUser(id);
    }

    @GetMapping("/api/user")
    public ResponseDTO<Object> retrieveUserList(){
        return userService.retrieveUserList();
    }

    @PostMapping("/api/user")
    public ResponseDTO<Object> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO){
        return userService.createUser(userCreateReqDTO);
    }

    @PutMapping("/api/user/{id}")
    public ResponseDTO<Object> updateUser(@PathVariable Long id, @RequestBody UserUpdateReqDTO userUpdateReqDTO){
        return userService.updateUser(id, userUpdateReqDTO);
    }
}
