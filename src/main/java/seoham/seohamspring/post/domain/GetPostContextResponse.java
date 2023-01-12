package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetPostContextResponse {

    private int postIdx;
    private String sender; //보낸이
    private Timestamp date; //날짜
    private List<Integer> tagIdx; //태그 번호 리스트
    private List<String> tagName; //태그 이름 리스트
    private List<String> tagColor; //태그 색상 리스트
    private int letterIdx; //편지지 번호
    private String content;


}
