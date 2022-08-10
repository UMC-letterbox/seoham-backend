package seoham.seohamspring.post;

<<<<<<< HEAD
import seoham.seohamspring.post.domain.*;

=======
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
public interface PostRepository {

    int savePost(int userIdx, CreatePostRequest createPostRequest);
    int updatePost(int postIdx, PatchPostRequest patchPostRequest);
    int checkPostExist(int postIdx);
    int deletePost(int postIdx);
    /*
    List<Tag> getTagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> getSenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(long postIdx);

     */
}
