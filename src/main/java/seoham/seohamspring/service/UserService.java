package seoham.seohamspring.service;


import seoham.seohamspring.domain.CreateUserRequest;
import seoham.seohamspring.domain.CreateUserResponse;

public interface UserService {
    //회원가입
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

}