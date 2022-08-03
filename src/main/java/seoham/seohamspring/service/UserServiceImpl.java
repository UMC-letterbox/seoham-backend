package seoham.seohamspring.service;


import org.springframework.beans.factory.annotation.Autowired;

import seoham.seohamspring.domain.CreateUserRequest;
import seoham.seohamspring.domain.CreateUserResponse;
import seoham.seohamspring.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //회원가입
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
        try{
            int userIdx = userRepository.createUser(createUserRequest);
            return new CreateUserResponse(userIdx);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
