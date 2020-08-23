package application.example.credition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import application.example.credition.adapters.TransactionsRecyclerViewAdapter;
import application.example.credition.database.DBHelper;
import application.example.credition.model.Transaction;

public class TransactionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView transactionsRecyclerView;

    private TransactionsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Transactions");

        transactionsRecyclerView = findViewById(R.id.transactions_recycler_view);

        DBHelper db = new DBHelper(this);
        List<Transaction> list = db.getAllTransactions();

        transactionsRecyclerView.setHasFixedSize(true);
        transactionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionsRecyclerViewAdapter(this, list);
        transactionsRecyclerView.setAdapter(adapter);

    }
}