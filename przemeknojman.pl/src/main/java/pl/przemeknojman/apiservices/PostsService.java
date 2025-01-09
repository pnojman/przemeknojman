package pl.przemeknojman.apiservices;

import io.restassured.response.Response;
import pl.przemeknojman.dto.PostDTO;
import pl.przemeknojman.factory.ApiRequestFactory;
import pl.przemeknojman.util.restapi.GetRequest;
import pl.przemeknojman.util.restapi.PostRequest;

import java.util.List;

public class PostsService {

    private final String apiUrl;


    public PostsService(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public List<PostDTO> getAllPosts() {

        String GETTING_ALL_POSTS = "posts";

        GetRequest getRequest = (GetRequest) ApiRequestFactory.createRequest("GET", this.apiUrl, GETTING_ALL_POSTS);
        Response result = getRequest.execute();
        return result.body().jsonPath().getList(".", PostDTO.class);
    }

    public PostDTO getPostById(int id) {

        String GETTING_SINGLE_POST = "posts/{postId}";

        GetRequest getRequest = (GetRequest) ApiRequestFactory.createRequest("GET", this.apiUrl, GETTING_SINGLE_POST);
        getRequest.setPathParam("postId", id);

        return getRequest.execute().as(PostDTO.class);

    }

    public int addPost(PostDTO body) {

        String CREATING_SINGLE_POST = "posts";

        PostRequest postRequest = (PostRequest) ApiRequestFactory.createRequest("POST", this.apiUrl, CREATING_SINGLE_POST);
        postRequest.setRequestBody(body.toString());

        Response result = postRequest.execute();
        return Integer.parseInt(result.body().path("id").toString());
    }
}
