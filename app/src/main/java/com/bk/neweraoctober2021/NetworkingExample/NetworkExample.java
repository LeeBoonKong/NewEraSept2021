package com.bk.neweraoctober2021.NetworkingExample;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkExample extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<String> userList = new ArrayList<>();
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_example);
        findViews();
        setListeners();
        getDataFromInternet();
    }

    private void findViews(){
        listView = findViewById(R.id.listview);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setListeners(){

    }

    private void initListview(){
        UsernameAdapter adapter = new UsernameAdapter(this, userList);
        listView.setAdapter(adapter);
    }

    private void getDataFromInternet(){
        progressBar.setVisibility(View.VISIBLE);

        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/v3/b/60d14dca8ea8ec25bd12b9b4")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(NetworkExample.this, "Fail to Fetch", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res = response.body().string();
                try {
                    JSONObject resObject = new JSONObject(res);
                    JSONArray usersArray = resObject.getJSONObject("record").getJSONArray("users");

                    for(int i=0; i < usersArray.length(); i++){
                        userList.add(usersArray.getString(i));
                    }

                    runOnUiThread(() -> {
                        initListview();
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}