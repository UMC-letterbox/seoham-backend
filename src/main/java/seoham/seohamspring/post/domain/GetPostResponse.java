package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor

public class GetPostResponse {

    private int postIdx;
    private String sender; //보낸이
    private Timestamp date; //날짜
    private String tagIdx; //태그 => list 형태로 변환
    //private String tagName;
    //private String tagColor;
    private int letterIdx; //편지지 번호

}
