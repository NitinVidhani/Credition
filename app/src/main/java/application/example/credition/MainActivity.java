package application.example.credition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
//        user.setUserImage("https://images.pexels.com/photos/2117283/pexels-photo-2117283.jpeg");
//
//        db.insertUser(user);
//
//        user.setUserName("Robert Hudson");
//        user.setUserEmail("robhudson99@gmail.com");
//        user.setCredits(1000);
//        user.setUserImage("https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
//
//        db.insertUser(user);

        userList = db.getAllUsers();

        adapter = new UserRecyclerViewAdapter(this, userList);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.setAdapter(adapter);

    }
}