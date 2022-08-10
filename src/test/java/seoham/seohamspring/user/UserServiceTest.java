package seoham.seohamspring.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.SpringConfig;
import seoham.seohamspring.user.domain.*;

import javax.sql.DataSource;
import java.util.Optional;

public class UserServiceTest {

    UserService userService;
    @Autowired
    DataSource dataSource;

    @BeforeEach
    public void beforeEach(){
        SpringConfig springConfig= new SpringConfig(dataSource);
        userService = springConfig.userService();
    }

    @Test
    void createUser1() throws BaseException {

        //given
        //User user = new User(0,"dh@gmail.com","123456","dhdhdh");


        //when
        CreateUserRequest user1 = new CreateUserRequest("dh@gmail.com","123456","dhdhdh");
        userService.createUser(user1);



        //then
    }


}

