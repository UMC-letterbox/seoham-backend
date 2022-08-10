<<<<<<< HEAD:src/main/java/seoham/seohamspring/user/User.java
package seoham.seohamspring.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userIdx;

    private String email;

    private String passWord;

    private String nickName;
}
=======

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userIdx;

}
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e:src/main/java/seoham/seohamspring/user/domain/User.java
