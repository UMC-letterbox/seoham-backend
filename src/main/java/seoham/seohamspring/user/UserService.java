package seoham.seohamspring.user;


import seoham.seohamspring.user.CreateUserRequest;
import seoham.seohamspring.user.CreateUserResponse;

public interface UserService {
    //회원가입
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

}