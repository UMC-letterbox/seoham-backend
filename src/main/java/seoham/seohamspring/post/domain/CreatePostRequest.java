package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String sender; //보낸이
    private int date; //날짜
    private List<Integer> tagIdx; //태그
    private String content; //편지 내용
    private String image;
    private int letterIdx; //편지지 번호

}