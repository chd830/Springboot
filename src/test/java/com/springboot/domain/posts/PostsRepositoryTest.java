package com.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정이 없다면 H2 데이터베이스를 자동으로 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드. 배포 전 전체 테스트를 수행할 대 테스트간 데이터 침범을 막기 위해 사용. 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있음
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title="테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블에 insert/update를 실행. id값이 잇으면 update, 없으면 insert
        .title(title)
        .content(content)
        .author("chd830@naver.com")
        .build());

        List<Posts> postsList = postsRepository.findAll(); // 테이블에 있는 모든 데이터를 조회

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
