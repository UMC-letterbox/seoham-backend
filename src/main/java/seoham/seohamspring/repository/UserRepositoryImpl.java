package seoham.seohamspring.repository;

import seoham.seohamspring.domain.CreateUserRequest;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public int createUser(CreateUserRequest createUserRequest){
        String createUserQuery = "insert into User (email, passWord, nickName) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{createUserRequest.getEmail(), createUserRequest.getPassWord(), createUserRequest.getNickName()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

}
