package seoham.seohamspring.user.domain;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class CheckCodeRequest {
    private int num;
    public CheckCodeRequest(){

    }

}