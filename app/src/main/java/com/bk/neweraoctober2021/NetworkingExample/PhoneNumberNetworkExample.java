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

public class PhoneNumberNetworkExample extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<Contact> contactArrayList = new ArrayList<>();
    private ListView listView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_network_example);
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
        PhoneNumberAdapter adapter = new PhoneNumberAdapter(this, contactArrayList);
        listView.setAdapter(adapter);
    }

    private void getDataFromInternet(){
        progressBar.setVisibility(View.VISIBLE);

        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/v3/b/5f240dc8250d377b5dc79d7a")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(PhoneNumberNetworkExample.this, "Fail to Fetch", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res = response.body().string();
                try {
                    JSONObject resObject = new JSONObject(res);
                    JSONArray contactArray = resObject.getJSONObject("record").getJSONArray("response");

                    for(int i=0; i < contactArray.length(); i++){
                        JSONObject contactObject = contactArray.getJSONObject(i);
                        String name = contactObject.getString("name");
                        String number = contactObject.getString("number");

                        Contact contact = new Contact(name, number);
                        contactArrayList.add(contact);
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