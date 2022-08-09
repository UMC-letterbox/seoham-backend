package seoham.seohamspring.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import seoham.seohamspring.config.SpringConfig;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired DataSource dataSource;

    @BeforeEach
    public void beforeEach(){
        SpringConfig springConfig= new SpringConfig(dataSource);
        postService = springConfig.postService();
    }

    @Test
    void 등록확인() {

        //given
        Post post = new Post();

        post.setUserIdx(1);
        post.setDate(20190429);
        post.setContent("안녕은서야");
        post.setTagIdx(1);
        post.setSender("친구");
        post.setLetterIdx(1);


        //when
        postService.post(post);
        Optional<Post> post1 = postService.findByPostIdx(1);



        //then
        Assertions.assertThat(post).isEqualTo(post1);
    }

}

