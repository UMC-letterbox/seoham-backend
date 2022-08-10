package seoham.seohamspring.post;

import seoham.seohamspring.post.domain.*;

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
