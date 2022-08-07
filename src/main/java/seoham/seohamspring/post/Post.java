package seoham.seohamspring.post;


public class Post {

    private int postIdx;
    private String sender; //보낸이
    private int date; //날짜
    private int tagIdx; //태그
    private String content; //편지 내용
    private int letterIdx; //편지지 번호



    public long getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(int postIdx) {
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


}
