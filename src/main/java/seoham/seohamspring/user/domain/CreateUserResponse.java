package seoham.seohamspring.user.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse{
    private long userIdx;  // 유저 Idx

}
