<<<<<<< HEAD
package seoham.seohamspring.user;

public class User {




}
=======
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
>>>>>>> main
