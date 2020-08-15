package application.example.credition.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import application.example.credition.DetailsActivity;
import application.example.credition.R;
import application.example.credition.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    private static final String TAG = "UserRecyclerViewAdapter";

    Context context;
    List<User> users;
    LayoutInflater inflater;

    public UserRecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_user_item, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = users.get(position);

        holder.username.setText(user.getUserName());
        holder.userEmail.setText(user.getUserEmail());
        Log.e(TAG, "onBindViewHolder: " + user.getUserName());
        Log.e(TAG, "onBindViewHolder: " + user.getId());
        Log.e(TAG, "onBindViewHolder: " + user.getCredits());
        holder.credits.setText(user.getCredits() + "");

        Picasso.get().load(user.getUserImage()).into(holder.profileImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView username, userEmail, credits;
        private CircleImageView profileImg;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            credits = itemView.findViewById(R.id.credits_available);
            profileImg = itemView.findViewById(R.id.profile_image);

        }
    }

}
