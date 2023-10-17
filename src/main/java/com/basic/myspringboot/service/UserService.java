package com.basic.myspringboot.service;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserReqForm;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//static import
import static java.util.stream.Collectors.toList;

@Service //컴포넌트해도됨
@RequiredArgsConstructor // final로 선언된 변수에 대한 생성자 만들어줌
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Constructor Injection > alt+insert
    /*public UserService(UserRepository userRepository, ModelMapper modeMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper; // 생성자를 계속 수정해줘야함 > 그래서 lombak를 써서 위에처럼 final을 줌
    }*/
    //저장 insert
    public UserResDTO saveUser(UserReqDTO userReqDto) {
        //reqDto => entity mapping
        User user = modelMapper.map(userReqDto, User.class);
        //DB에 저장
        User savedUser = userRepository.save(user);
        //entity => resDto mapping
        return modelMapper.map(savedUser, UserResDTO.class);
    }

    //조회 메서드인 경우에 readOnly=true 를 설정하면 성능향상에 도움이 됨
    @Transactional(readOnly = true)
    public UserResDTO getUserById(Long id){
        User userEntity = userRepository.findById(id) //Optional<User>
                .orElseThrow(() -> new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        //Entity -> ResDTO
        UserResDTO userResDTO = modelMapper.map(userEntity, UserResDTO.class);
        return userResDTO;
    }

    //전체조회
    @Transactional(readOnly = true)
    public List<UserResDTO> getUsers(){
        List<User> userList = userRepository.findAll();//List<User>
        //List<User> => List<UserResDTO>
        List<UserResDTO> userResDTOList = userList.stream()//Stream<User>
                //map(Function) Function의 추상메서드 R apply(T t)
                //     T          ->           R
                .map(user -> modelMapper.map(user, UserResDTO.class))//Stream<UserResDTO>
                // 람다식 추상메서드에 오버라이딩
                .collect(toList());// List<UserResDTO>
        return userResDTOList;//
    }

    //수정 update
    public UserResDTO updateUser(String email, UserReqDTO userReqDto) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        //Dirty Checking 변경감지를 하므로 setter method만 호출해도 update query가 실행된다.
        existUser.setName(userReqDto.getName());
        return modelMapper.map(existUser, UserResDTO.class);
    }

    //삭제 delete
    public void deleteUser(Long id) {
        User user = userRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

    public void updateUserForm(UserReqForm userReqForm){
        User existUser = userRepository.findById(userReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(userReqForm.getId() + " User Not Found", HttpStatus.NOT_FOUND));
        existUser.setName(userReqForm.getName());
    }

}
