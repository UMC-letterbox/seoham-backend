package seoham.seohamspring.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seoham.seohamspring.config.SpringConfig;

import javax.sql.DataSource;
import java.util.Optional;

public class PostServiceTest {

    /*PostService postService;
    DataSource dataSource

    @BeforeEach
    public void beforeEach(){
        SpringConfig springConfig= new SpringConfig(dataSource);
        postService = springConfig.postService();
    }

    @Test
    void 등록확인() {

        //given
        Post post = new Post();
        post.setDate(20190429);
        post.setContent("안녕은서야");
        post.setTagIdx(1);
        post.setSender("친구");
        post.setLetterIdx(1);


        //when
        postService.post(post);
        Optional<Post> post1 = postService.findByPostIdx(1L);



        //then
        Assertions.assertThat(post).isEqualTo(post1);
    }
    */

}

