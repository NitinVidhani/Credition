package application.example.credition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Database;

import java.util.ArrayList;
import java.util.List;

import application.example.credition.database.DBHelper;
import application.example.credition.model.User;


public class TransferCreditDialog extends AppCompatDialogFragment {

    private EditText creditAmount;
    private Spinner spinner;

    private TransferDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        int id = getArguments().getInt("id");

        builder.setView(view)
                .setTitle("Select User")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userDetails = spinner.getSelectedItem().toString();
                        int amountToBeTransferred = Integer.parseInt(creditAmount.getText().toString());

                        listener.applyInputs(userDetails, amountToBeTransferred);

                    }
                });

        creditAmount = view.findViewById(R.id.edit_text);
        spinner = view.findViewById(R.id.spinner);

        DBHelper db = new DBHelper(getActivity());

        List<User> userList = db.getAllUsers();
        List<String> spinnerText = new ArrayList<>();


        spinnerText.add("Select User");
        for (User user : userList) {
            if (user.getId() != id)
                spinnerText.add(user.getUserName() + "(" + user.getId() + ")");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerText);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return builder.create();
    }

    public interface TransferDialogListener {
        void applyInputs(String userDetails, int amountToBeTransferred);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (TransferDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement TransferDialogListener");
        }

    }
}
