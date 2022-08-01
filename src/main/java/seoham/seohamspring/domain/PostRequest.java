package seoham.seohamspring.domain;

import javax.persistence.*;

@Entity
public class PostRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postIdx;

    //User테이블과 조인, 한명의 유저가 여러개의 게시물 작성 가능하다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userIdx")
    private User user;

    private String sender; //보낸이
    private int date; //날짜
    private int tagIdx; //태그
    private String content; //편지 내용
    private int letterIdx; //편지지 번호

    public long getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(long postIdx) {
        this.postIdx = postIdx;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTagIdx() {
        return tagIdx;
    }

    public void setTagIdx(int tagIdx) {
        this.tagIdx = tagIdx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLetterIdx() {
        return letterIdx;
    }

    public void setLetterIdx(int letterIdx) {
        this.letterIdx = letterIdx;
    }


    public PostRequest(int postIdx, String sender, int date, int tagIdx, String content, int letterIdx) {
        super();
        this.postIdx = postIdx;
        this.sender = sender;
        this.date = date;
        this.tagIdx = tagIdx;
        this.content = content;
        this.letterIdx = letterIdx;
    }
}
