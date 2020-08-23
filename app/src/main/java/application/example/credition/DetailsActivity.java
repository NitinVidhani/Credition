package application.example.credition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import application.example.credition.database.DBHelper;
import application.example.credition.model.User;

public class DetailsActivity extends AppCompatActivity implements TransferCreditDialog.TransferDialogListener {

    private static final String TAG = "DetailsActivity";

    private TextView userName, userEmail, userId;
    private ImageView profileImage;
    private TextView credits;

    User user;

    int fromId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        user = getIntent().getParcelableExtra("user");

        userId = findViewById(R.id.text_view_id);
        userName = findViewById(R.id.text_view_name);
        userEmail = findViewById(R.id.text_view_email);
        credits = findViewById(R.id.text_view_credits);
        profileImage = findViewById(R.id.profile_image_view);

        Log.e(TAG, "onCreate: " + user.getId());
        userId.setText(user.getId() + "");
        userName.setText(user.getUserName());
        userEmail.setText(user.getUserEmail());
        credits.setText(user.getCredits() + "");

        Picasso.get().load(user.getUserImage()).into(profileImage);

        fromId = user.getId();

    }

    public void transferCredits(View view) {

        TransferCreditDialog dialog = new TransferCreditDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("id", fromId);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "Transfer Dialog");

    }

    @Override
    public void applyInputs(String userDetails, int amountToBeTransferred) {

        String details = userDetails.split("[(]")[1];
        String toId = details.split("[)]")[0];

        Log.e(TAG, "applyInputs: " + toId);

        DBHelper db = new DBHelper(this);
        int id = Integer.parseInt(toId);
        boolean b = db.transferCredits(fromId, id, amountToBeTransferred);

        if(b) {
            Toast.makeText(this, "Transaction Successful!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        } else {
            Toast.makeText(this, "Transaction Failed!!", Toast.LENGTH_SHORT).show();
        }


    }
}