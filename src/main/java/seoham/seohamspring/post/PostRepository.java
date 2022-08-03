package seoham.seohamspring.post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    void delete(int postIdx);
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(long postIdx);
}
