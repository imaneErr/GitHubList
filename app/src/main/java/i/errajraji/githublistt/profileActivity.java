package i.errajraji.githublistt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import i.errajraji.githublistt.model.GitRepo;
import i.errajraji.githublistt.service.GitRepoServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profileActivity extends AppCompatActivity {

    public List<String> data = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setTitle("Profil");
        Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.USERNAME_PARAM);
        TextView textViewProfilUserName = findViewById(R.id.textViewProfilUserName);
        ListView listViewRepos = findViewById(R.id.listViewRepos);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        listViewRepos.setAdapter(stringArrayAdapter);

        textViewProfilUserName.setText(username);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitRepoServiceAPI service = retrofit.create(GitRepoServiceAPI.class);
        Call<GitRepo> repoCall = service.userRepos(username);
        repoCall.enqueue(
                new Callback<GitRepo>() {
                    @Override
                    public void onResponse(Call<GitRepo> call, Response<GitRepo> response) {
                        if (!response.isSuccessful()){
                            Log.e("Error",String.valueOf(response.code()));
                            return;
                        }
                        List<GitRepo> gitRepos = (List<GitRepo>) response.body();
                        for (GitRepo gitRepo: gitRepos ){
                            String context="";
                            context += "id: " + gitRepo.id+"\n";
                            context += "name:" + gitRepo.name +"\n";
                            context += "lang:" + gitRepo.language+"\n";
                            data.add(context);
                        }
                        stringArrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<GitRepo> call, Throwable t) {
                        Log.i("error","Error onFailure");

                    }
                }
        );
    }
}

