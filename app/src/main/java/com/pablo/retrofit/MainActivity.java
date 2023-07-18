package com.pablo.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.pablo.retrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cardview.setRadius(100f);
        binding.button.setOnClickListener(v -> getPosts());
    }
    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jetpack-course-default-rtdb.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<List<Post>> call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> listaPost = response.body();

                if(listaPost!=null){
                    int aleatorio = new Random().nextInt(listaPost.size());
                    Random rnd = new Random();
                    int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    binding.cardview.setCardBackgroundColor(currentColor);
                    binding.textView.setText(listaPost.get(aleatorio).getName());
                    binding.textView2.setText(listaPost.get(aleatorio).getDirector());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
}