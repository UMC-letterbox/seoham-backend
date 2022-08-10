package seoham.seohamspring.post;

import seoham.seohamspring.post.domain.*;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    CreatePostResponse save(CreatePostRequest createPostRequest);
    void delete(int postIdx);
    List<Tag> getTagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> getSenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(long postIdx);
}
