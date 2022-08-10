package seoham.seohamspring.user.domain;

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