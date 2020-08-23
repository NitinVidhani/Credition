package application.example.credition;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import application.example.credition.adapters.UserRecyclerViewAdapter;
import application.example.credition.database.DBHelper;
import application.example.credition.model.User;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView userRecyclerView;
    private UserRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userRecyclerView = findViewById(R.id.user_recycler_view);

        List<User> userList = new ArrayList<>();

        DBHelper db = new DBHelper(this);


//        User user = new User();
//        user.setUserName("Erica Morgan");
//        user.setUserEmail("ericamorgan1212@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.pexels.com/photos/2613260/pexels-photo-2613260.jpeg");
//
//        db.insertUser(user);
//
//        user.setUserName("Stuart Reed");
//        user.setUserEmail("sreed.24@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.pexels.com/photos/2379005/pexels-photo-2379005.jpeg");
//
//        db.insertUser(user);
//
//        user.setUserName("Farhan Kelley");
//        user.setUserEmail("farhan.kelley@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.pexels.com/photos/2117283/pexels-photo-2117283.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260");
//
//        db.insertUser(user);
//
//        user.setUserName("Robert Hudson");
//        user.setUserEmail("robhudson99@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
//
//        db.insertUser(user);
//
//        user.setUserName("Joseph Reeves");
//        user.setUserEmail("jayree45@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");
//
//        db.insertUser(user);
//
//        user.setUserName("Kattie Johnson");
//        user.setUserEmail("kattie.johnson@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
//
//        db.insertUser(user);
//
//        user.setUserName("William Right");
//        user.setUserEmail("willright00@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.unsplash.com/photo-1545167622-3a6ac756afa4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60");
//
//        db.insertUser(user);
//
//        user.setUserName("Jack Samoa");
//        user.setUserEmail("jsmoa21@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.unsplash.com/photo-1541647376583-8934aaf3448a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
//
//        db.insertUser(user);

        userList = db.getAllUsers();

        adapter = new UserRecyclerViewAdapter(this, userList);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.setAdapter(adapter);

    }

    public void allTransactions(View view) {

        Intent intent = new Intent(this, TransactionsActivity.class);
        startActivity(intent);

    }

}