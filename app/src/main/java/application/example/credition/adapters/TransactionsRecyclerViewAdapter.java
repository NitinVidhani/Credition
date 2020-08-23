package application.example.credition.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import application.example.credition.R;
import application.example.credition.database.DBHelper;
import application.example.credition.model.Transaction;

public class TransactionsRecyclerViewAdapter extends RecyclerView.Adapter<TransactionsRecyclerViewAdapter.TransactionViewHolder> {

    Context context;
    List<Transaction> transactionList;
    LayoutInflater inflater;

    public TransactionsRecyclerViewAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;

        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_transaction_item, parent, false);

        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        Transaction transaction = transactionList.get(position);
        DBHelper dbHelper = new DBHelper(context);

        int fromId = transaction.getFromId();
        int toId = transaction.getToId();

        String fromName = dbHelper.getNameById(fromId);
        String toName = dbHelper.getNameById(toId);

        holder.textViewFrom.setText(fromName + " (" + fromId + ")");
        holder.textViewTo.setText(toName + " (" + toId + ")");

        holder.textViewCredits.setText(transaction.getAmount() + "");

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFrom, textViewTo, textViewCredits;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFrom = itemView.findViewById(R.id.from_details);
            textViewTo = itemView.findViewById(R.id.to_details);
            textViewCredits = itemView.findViewById(R.id.credits);

        }
    }

}
