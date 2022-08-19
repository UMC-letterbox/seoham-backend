package seoham.seohamspring.user.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class User {

    private int userIdx;
    private String email;
    private String passWord;
    private String nickName;

}
