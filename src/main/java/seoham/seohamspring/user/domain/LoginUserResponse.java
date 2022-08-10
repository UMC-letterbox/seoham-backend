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
public class LoginUserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userIdx;

}