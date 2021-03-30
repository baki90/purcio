package purcio.purcio.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.user.dto.user.UserCreateReqDTO;
import purcio.purcio.user.dto.user.UserUpdateReqDTO;
import purcio.purcio.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseDTO<Object> retrieveUser(@PathVariable Long id){
        return userService.retrieveUser(id);
    }

    @GetMapping("")
    public ResponseDTO<Object> retrieveUserList(){
        return userService.retrieveUserList();
    }

    @PostMapping("")
    public ResponseDTO<Object> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO){
        return userService.createUser(userCreateReqDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO<Object> updateUser(@PathVariable Long id, @RequestBody UserUpdateReqDTO userUpdateReqDTO){
        return userService.updateUser(id, userUpdateReqDTO);
    }

    /**
     * 닉네임의 존재 여부를 알려줍니다.
     * @param nickName
     * @return boolean
     */
    @GetMapping("/isExist/nickname={nickName}")
    public ResponseDTO<Object> isExistNickName(@PathVariable("nickName") String nickName){
        ResponseDTO responseDTO;
        if(userService.nickNameIsExist(nickName)) {
            responseDTO = ResponseDTO.builder()
                    .message("동일한 닉네임이 존재합니다.")
                    .content(Boolean.TRUE)
                    .build();
        }

        else {
            responseDTO = ResponseDTO.builder()
                    .message("동일한 닉네임이 존재하지 않습니다.")
                    .content(Boolean.FALSE)
                    .build();
        }

        return responseDTO;
    }

    /**
     * 이메일의 존재 여부를 알려줍니다.
     * @param email
     * @return void
     */
    @GetMapping("/isExist/email={email}")
    public ResponseDTO<Object> isExistEmail(@PathVariable("email") String email){

        ResponseDTO responseDTO;

        if(userService.emailIsExist(email)) {
            responseDTO = ResponseDTO.builder()
                    .message("이메일이 존재합니다.")
                    .content(Boolean.TRUE)
                    .build();
        }
        else{
            responseDTO = ResponseDTO.builder()
                    .message("이메일이 존재하지 않습니다.")
                    .content(Boolean.FALSE)
                    .build();
        }
        return responseDTO;
    }


}
