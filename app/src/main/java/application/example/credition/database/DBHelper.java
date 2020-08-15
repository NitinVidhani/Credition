package application.example.credition.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import application.example.credition.model.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "CreditMgmnt.db";
    public static final String ID = "id";
    public static final String USERS = "users";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_IMAGE = "user_image";
    public static final String CREDITS = "credits";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsers = "CREATE TABLE " + USERS + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_NAME + " TEXT NOT NULL," +
                USER_EMAIL + " TEXT NOT NULL, " +
                USER_IMAGE + " TEXT," +
                CREDITS + " INTEGER)";

        db.execSQL(createTableUsers);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableUsers = "DROP TABLE IF EXISTS " + USERS;
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        String username = user.getUserName();
        String email = user.getUserEmail();
        String userimage = user.getUserImage();
        int credits = user.getCredits();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, username);
        values.put(USER_EMAIL, email);
        values.put(USER_IMAGE, userimage);
        values.put(CREDITS, credits);

        long success = db.insert(USERS, null, values);
        if (success == -1) {
            return false;
        }
        return true;
    }

    public int getCredits(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        int credits = 0;

        String query = "SELECT " + CREDITS + " FROM " + USERS + " WHERE " + ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{id + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            credits = cursor.getInt(cursor.getColumnIndex(CREDITS));
        }

        return credits;
    }

    public boolean transferCredits(int fromId, int toId, int creditToBeTransferred) {

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isTransactionSuccessfull = false;

        db.beginTransaction();

        try {
            if (applyTransaction(fromId, toId, creditToBeTransferred)) {
                db.setTransactionSuccessful();
                isTransactionSuccessfull = true;
            }
        } catch (Exception e) {

        } finally {
            db.endTransaction();
        }

        return isTransactionSuccessfull;
    }

    private boolean applyTransaction(int fromId, int toId, int creditToBeTransferred) {

        SQLiteDatabase db = this.getWritableDatabase();

        int prevFromCredits = getCredits(fromId);
        if (prevFromCredits >= creditToBeTransferred) {

            int prevToCredits = getCredits(toId);

            int newFromCredits = prevFromCredits - creditToBeTransferred;
            int newToCredits = prevToCredits + creditToBeTransferred;

            ContentValues fromValues = new ContentValues();
            fromValues.put(CREDITS, newFromCredits);

            int updateFrom = db.update(USERS, fromValues, ID + " = ?", new String[]{fromId + ""});
            if (updateFrom == 1) {

                ContentValues toValues = new ContentValues();
                toValues.put(CREDITS, newToCredits);
                int updateTo = db.update(USERS, toValues, ID + " = ?", new String[]{toId + ""});

                if (updateTo == 1) {
                    return true;
                }

            }
        }
        return false;

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String image = cursor.getString(3);
            int credits = cursor.getInt(4);

            User user = new User(id, name, email, image, credits);
            userList.add(user);
        }

        return userList;
    }


}
