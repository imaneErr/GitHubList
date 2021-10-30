package i.errajraji.githublistt.service;

import i.errajraji.githublistt.model.GitRepo;
import i.errajraji.githublistt.model.GitUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitRepoServiceAPI {
    @GET("search/users")
    public Call<GitUserResponse> searchUsers (@Query("q") String query);

    @GET("users/{u}/repos")
    public Call<GitRepo> userRepos (@Path("u") String username);
}
