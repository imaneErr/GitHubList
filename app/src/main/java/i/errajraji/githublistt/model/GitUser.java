package i.errajraji.githublistt.model;

import com.google.gson.annotations.SerializedName;

public class GitUser {
    public int id;
    @SerializedName("login")
    public String username;
    @SerializedName("avatar_url")
    public String avatarUrl;
    public String score;

}
