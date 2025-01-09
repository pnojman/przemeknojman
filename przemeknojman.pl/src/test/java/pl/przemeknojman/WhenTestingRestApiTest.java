package pl.przemeknojman;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.przemeknojman.apiservices.PostsService;
import pl.przemeknojman.builders.PostBuilder;
import pl.przemeknojman.dto.PostDTO;
import pl.przemeknojman.util.JsonUtils;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class WhenTestingRestApiTest {

    PostsService postsService;
    @BeforeClass
    @Parameters({"apiUrl"})
    public void setUp(String apiUrl) {
        postsService = new PostsService(apiUrl);
    }


    @Test
    public void getPosts() {

        List<PostDTO> posts = postsService.getAllPosts();
        Assert.assertFalse(posts.isEmpty());

    }


    @Test
    public void getSinglePost() {

        int postIdToGet = 1;

        PostDTO post = postsService.getPostById(postIdToGet);

        String postJson = JsonUtils.readJsonFromResource("posts/post.json");
        PostDTO expectedPost = JsonUtils.parseJsonToObject(postJson, PostDTO.class);

        assertThat(post).isEqualTo(expectedPost);

    }

    @Test
    public void addPost() {

        int expectedNewPostId = 101;

        PostBuilder postBuilder = new PostBuilder();
        PostDTO randomPost = postBuilder
                .withRandomUserId()
                .withRandomTitle()
                .withRandomBody().build();

        int newPostId = postsService.addPost(randomPost);

        Assert.assertEquals(expectedNewPostId, newPostId);

    }
}
