package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class GetPostInfoResponse {

    private int postIdx;
    private String sender; //보낸이
    private int date; //날짜
    private String tagIdx; //태그 번호 리스트
    private int letterIdx; //편지지 번호
    private String image;
    private String content;
}
