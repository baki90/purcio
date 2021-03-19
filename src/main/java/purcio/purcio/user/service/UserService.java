package purcio.purcio.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.user.domain.User;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.user.dto.UserCreateReqDTO;
import purcio.purcio.user.dto.UserUpdateReqDTO;
import purcio.purcio.user.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveUserList(){
        List<User> userList = userRepository.findAll();

        return ResponseDTO.builder()
                .message("유저가 조회되었습니다.")
                .content(userList)
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveUser(Long id){
        User user = userRepository.findById(id).orElse(null);

        return ResponseDTO.builder()
                .message("유저가 조회되었습니다.")
                .content(user)
                .build();
    }

    @Transactional
    public ResponseDTO<Object> createUser(UserCreateReqDTO userCreateReqDTO){

        User user = User.builder()
                .name(userCreateReqDTO.getName())
                .email(userCreateReqDTO.getEmail())
                .picture(userCreateReqDTO.getPicture())
                .nickName(userCreateReqDTO.getNickName())
                .phoneNumber(userCreateReqDTO.getPhoneNumber())
                .age(userCreateReqDTO.getAge())
                .address(userCreateReqDTO.getAddress())
                .build();

        userRepository.save(user);

        return ResponseDTO.builder()
                .message("유저가 생성되었습니다.")
                .content(user.getId())
                .build();
    }

    @Transactional
    public ResponseDTO<Object> updateUser(Long id, UserUpdateReqDTO userUpdateReqDTO){
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        user.updateUser(userUpdateReqDTO.getName(), userUpdateReqDTO.getEmail(),
                userUpdateReqDTO.getPicture(), userUpdateReqDTO.getNickName(),
                userUpdateReqDTO.getPhoneNumber(), userUpdateReqDTO.getAge(),
                userUpdateReqDTO.getSex(), userUpdateReqDTO.getAddress());

        userRepository.save(user);

        return ResponseDTO.builder()
                .message("유저 변경이 완료되었습니다.")
                .content(user.getId())
                .build();
    }

    @Transactional
    public ResponseDTO<Object> deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        userRepository.delete(user);

        return ResponseDTO.builder()
                .message("유저 삭제가 완료되었습니다.")
                .content(user.getId())
                .build();
    }


}
