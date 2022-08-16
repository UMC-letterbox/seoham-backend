package seoham.seohamspring.post;

import org.springframework.stereotype.Repository;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository {

    int savePost(CreatePostRequest createPostRequest);
    int updatePost(int postIdx, PatchPostRequest patchPostRequest);
    int deletePost(int postIdx);
    int checkPostExist(int postIdx);
    int saveTag(CreateTagRequest createTagRequest);
    int updateTag(int tagIdx, PatchTagRequest patchTagRequest);
    int deleteTag(int tagIdx);
    int checkTagExist(int userIdx, String tagName);
    int checkTagNotExist(int tagIdx);
    int updateSender(String sender, PatchSenderRequest patchSenderRequest);
    int checkSenderExist(int userIdx, String sender);
    int deleteSender(String sender, DeleteSenderRequest deleteSenderRequest);
    /*
    List<Tag> getTagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> getSenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(long postIdx);

     */
}
