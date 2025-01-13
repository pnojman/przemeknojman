package pl.przemeknojman.apiservices;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.przemeknojman.dto.PostDTO;
import pl.przemeknojman.dto.TestParametersDTO;
import pl.przemeknojman.factory.ApiRequestFactory;
import pl.przemeknojman.util.restapi.GetRequest;
import pl.przemeknojman.util.restapi.PostRequest;

import java.util.List;

public class PostsService {

    private final TestParametersDTO testParameters;
    private static final Logger logger = LoggerFactory.getLogger(PostsService.class);


    public PostsService(TestParametersDTO testParameters) {
        this.testParameters = testParameters;
    }

    public List<PostDTO> getAllPosts() {

        String GETTING_ALL_POSTS = "posts";

        logger.info("Preparing get request for POSTS");
        GetRequest getRequest = (GetRequest) ApiRequestFactory.createRequest("GET", testParameters, GETTING_ALL_POSTS);
        logger.info("Executing the POSTS request");
        Response result = getRequest.execute();
        return result.body().jsonPath().getList(".", PostDTO.class);
    }

    public PostDTO getPostById(int id) {

        String GETTING_SINGLE_POST = "posts/{postId}";

        GetRequest getRequest = (GetRequest) ApiRequestFactory.createRequest("GET", testParameters, GETTING_SINGLE_POST);
        getRequest.setPathParam("postId", id);

        return getRequest.execute().as(PostDTO.class);

    }

    public int addPost(PostDTO body) {

        String CREATING_SINGLE_POST = "posts";

        PostRequest postRequest = (PostRequest) ApiRequestFactory.createRequest("POST", testParameters, CREATING_SINGLE_POST);
        postRequest.setRequestBody(body.toString());

        Response result = postRequest.execute();
        return Integer.parseInt(result.body().path("id").toString());
    }
}
