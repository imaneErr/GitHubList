package i.errajraji.githublistt.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitUserResponse {
    @SerializedName("total_count")
    public int TotalCount;
    @SerializedName("items")
    public List<GitUser> users = new ArrayList<>();

}
