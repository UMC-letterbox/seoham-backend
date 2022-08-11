package seoham.seohamspring.user.domain;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest{



    private String email;   // 유저 이메일

    private String passWord;  // 유저 비밀번호

    private String nickName; // 유저 닉네임

    public CreateUserRequest(){

    }

}
