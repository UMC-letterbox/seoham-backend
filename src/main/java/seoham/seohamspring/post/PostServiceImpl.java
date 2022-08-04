package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시물 작성
    @Override
    public void post(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(int postIdx) {
        postRepository.delete(postIdx);
    }

    @Override
    public List<Tag> TagList() {
        return postRepository.getTagList();
    }

    @Override
    public Optional<Post> findByTag(int tagIdx) {
        return postRepository.findByTag(tagIdx);
    }

    @Override
    public Optional<Post> findByDate(int date) {
        return postRepository.findByDate(date);
    }

    @Override
    public List<Sender> SenderList() {
        return postRepository.getSenderList();
    }

    @Override
    public Optional<Post> findBySender(String sender) {
        return postRepository.findBySender(sender);
    }





    @Override
    public Optional<Post> findByPostIdx(long postIdx) {
        return postRepository.findByPostId(postIdx);
    }
}
