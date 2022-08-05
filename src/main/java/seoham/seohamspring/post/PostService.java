package seoham.seohamspring.post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    //게시물 작성
    void post(Post post);
    //게시물 삭제
    void delete(int postIdx);
    List<Tag> TagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> SenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostIdx(long postIdx);



}
