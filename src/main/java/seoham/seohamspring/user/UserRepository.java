package seoham.seohamspring.user;


import seoham.seohamspring.user.CreateUserRequest;

public interface UserRepository {

    int createUser(CreateUserRequest createUserRequest);

    public int checkEmail(String email);

}
