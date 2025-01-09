package pl.przemeknojman;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.przemeknojman.apiservices.PostsService;
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

        String addPostJson = JsonUtils.readJsonFromResource("posts/addPost.json");
        PostDTO postBody = JsonUtils.parseJsonToObject(addPostJson, PostDTO.class);

        int newPostId = postsService.addPost(postBody);

        Assert.assertEquals(expectedNewPostId, newPostId);

    }
}
