package seoham.seohamspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import seoham.seohamspring.mypage.MypageRepository;
//import seoham.seohamspring.mypage.MypageRepositoryImpl;
//import seoham.seohamspring.mypage.MypageService;
//import seoham.seohamspring.mypage.MypageServiceImpl;
import seoham.seohamspring.post.PostRepository;
import seoham.seohamspring.post.PostRepositoryImpl;
import seoham.seohamspring.post.PostService;
import seoham.seohamspring.post.PostServiceImpl;
import seoham.seohamspring.user.UserRepository;
import seoham.seohamspring.user.UserRepositoryImpl;
import seoham.seohamspring.user.UserService;
import seoham.seohamspring.user.UserServiceImpl;

import javax.sql.DataSource;


@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl(userRepository());
    }
    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl(dataSource);
    }

    @Bean
    public PostService postService(){
        return new PostServiceImpl(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepositoryImpl(dataSource);
    }

    /*
    @Bean
    public MypageService mypageService() { return new MypageServiceImpl(mypageRepository()); }
>>>>>>> 51b7e8ddbcedc1b98ea30f4c6aa0354229fd1a97

    //@Bean
    //public MypageRepository mypageRepository() { return new MypageRepositoryImpl(dataSource); }


     */


}
