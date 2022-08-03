package seoham.seohamspring.repository;


import seoham.seohamspring.domain.CreateUserRequest;

public interface UserRepository {

    int createUser(CreateUserRequest createUserRequest);

}
