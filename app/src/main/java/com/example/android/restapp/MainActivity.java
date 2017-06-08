package com.example.android.restapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.restapp.restPack.ContactClass;
import com.example.android.restapp.restPack.RandomAPI;
import com.example.android.restapp.restPack.Result;
import com.example.android.restapp.restPack.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameTV;
    TextView addressTV;
    TextView emailTV;
    Button listBTN;
    Button fetchBTN;
    Button recycleBTN;

    int count = 0;
    ArrayList<ContactClass> contactList;
    ArrayList<Result> resultsList;

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String RETROFIT_URL = "https://randomuser.me/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTV = (TextView)findViewById(R.id.nameTV);
        addressTV = (TextView)findViewById(R.id.addressTV);
        emailTV = (TextView)findViewById(R.id.emailTV);
        fetchBTN = (Button)findViewById(R.id.fetchBTN);
        fetchBTN.setOnClickListener(this);
        listBTN = (Button)findViewById(R.id.listBTN);
        listBTN.setOnClickListener(this);
        recycleBTN = (Button)findViewById(R.id.recycleBTN);
        recycleBTN.setOnClickListener(this);

        contactList = new ArrayList<>();
    }
    private void doRetrofitNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
                if(response.isSuccessful()){
                    RandomAPI randomAPI =  response.body();
                    for(Result result:randomAPI.getResults()){
                        Log.d(TAG, "onResponse: Name is " + result.getName());

                        String fullName = nameFusion(result.getName().getFirst(), result.getName().getLast());



                        nameTV.setText(fullName);
                        String location = result.getLocation().getStreet()+ ", " + result.getLocation().getCity()
                                + ", " + result.getLocation().getState() + ", " + result.getLocation().getPostcode();
                        ContactClass user = new ContactClass(result.getEmail(),location,fullName);
                        contactList.add(user);
                        addressTV.setText(location);
                        emailTV.setText(result.getEmail());
                    }
                   count++;
                    Log.d(TAG, "onResponse: arrayCount = " + contactList.size());
                }
            }
            public  String nameFusion(String first, String last){
                StringBuilder builder = new StringBuilder();
                builder.append(getString(R.string.string_name_res));
                builder.append(" ");
                builder.append(first);
                builder.append(" ");
                builder.append(last);
                return builder.toString();

            }
            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {

            }
        });
    }
    private void launchList(){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        //intent.putParcelableArrayListExtra("rList", contactList);
        Bundle b = new Bundle();
        b.putParcelableArrayList("rList", contactList);
        intent.putExtras(b);
        startActivity(intent);
    }
    private void launchRecycle(){
        Intent intent = new Intent(MainActivity.this, RecylcerActivity.class);
        //intent.putParcelableArrayListExtra("rList", contactList);
        Bundle b = new Bundle();
        b.putParcelableArrayList("rList", contactList);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fetchBTN:
                doRetrofitNetworkCall();
                break;
            case R.id.listBTN:
                Toast.makeText(this, "listhit", Toast.LENGTH_SHORT).show();
                launchList();
            case R.id.recycleBTN:
                launchRecycle();
            default:
                return;
        }
    }
}