package purcio.purcio.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.common.exception.EmailIsAlreadyExist;
import purcio.purcio.common.exception.NickNameIsAlreadyExist;
import purcio.purcio.common.exception.NotFoundUserException;
import purcio.purcio.user.domain.User;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.user.dto.user.UserCreateReqDTO;
import purcio.purcio.user.dto.user.UserUpdateReqDTO;
import purcio.purcio.user.repository.UserRepository;

import java.util.List;

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

    /**
     * 유저를 생성한다.
     * @param userCreateReqDTO 등록 정보
     * @return 생성된 유저의 아이디
     */
    @Transactional
    public ResponseDTO<Object> createUser(UserCreateReqDTO userCreateReqDTO){

        if(emailIsExist(userCreateReqDTO.getEmail())) {
            throw new EmailIsAlreadyExist("이미 존재하는 이메일입니다.");
        }

        if(nickNameIsExist(userCreateReqDTO.getNickName())){
            throw new NickNameIsAlreadyExist("이미 존재하는 닉네임입니다.");
        }

        // TODO: password 암호화 작업 필요
        User user = User.createUser(userCreateReqDTO.getName(), userCreateReqDTO.getPassword(), userCreateReqDTO.getEmail(),
                userCreateReqDTO.getPicture(), userCreateReqDTO.getNickName(),
                userCreateReqDTO.getPhoneNumber(), userCreateReqDTO.getAge(),
                userCreateReqDTO.getSex(), userCreateReqDTO.getAddress());

        userRepository.save(user);

        return ResponseDTO.builder()
                .message("유저가 생성되었습니다.")
                .content(user.getId())
                .build();
    }

    /** 유저를 변경한다.
     * @param id 유저 아이디
     * @param userUpdateReqDTO 변경 정보
     * @return 변경된 유저의 아이디
     */
    @Transactional
    public ResponseDTO<Object> updateUser(Long id, UserUpdateReqDTO userUpdateReqDTO){
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        if(nickNameIsExist(userUpdateReqDTO.getNickName())){
            throw new NickNameIsAlreadyExist("이미 존재하는 닉네임입니다.");
        }

        user.updateUser(userUpdateReqDTO.getName(), userUpdateReqDTO.getPassword(),
                userUpdateReqDTO.getPicture(), userUpdateReqDTO.getNickName(),
                userUpdateReqDTO.getPhoneNumber(), userUpdateReqDTO.getAge(),
                userUpdateReqDTO.getSex(), userUpdateReqDTO.getAddress());

        userRepository.save(user);

        return ResponseDTO.builder()
                .message("유저 변경이 완료되었습니다.")
                .content(user.getId())
                .build();
    }

    /**
     * 유저를 삭제한다.
     * @param id
     * @return 삭제된 유저의 아이디
     */
    @Transactional
    public ResponseDTO<Object> deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        userRepository.delete(user);

        return ResponseDTO.builder()
                .message("유저 삭제가 완료되었습니다.")
                .content(user.getId())
                .build();
    }

    /**
     * 현재 해당 이메일이 존재하는지 판별한다.
     * @param email
     * @return 존재한다면 TRUE & 없다면 False
     */
    @Transactional(readOnly = true)
    public Boolean emailIsExist(String email){
        User user = userRepository.findUserByEmail(email).orElse(null);

        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 해당 닉네임이 존재하는지 판별한다.
     * @param nickName
     * @return 존재한다면 TRUE & 없다면 FALSE
     */
    @Transactional(readOnly = true)
    public Boolean nickNameIsExist(String nickName){
        User user = userRepository.findUserByNickName(nickName).orElse(null);

        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }


}
