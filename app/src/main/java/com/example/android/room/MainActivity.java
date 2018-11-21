package com.example.android.room;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextName;
    EditText editTextID;
    Button buttonSave;
    Database database;
    Handler handler;
    Button buttonRestore;
    String name;
    int id;
    Runnable save = new Runnable() {
        @Override
        public void run() {
            UserModel userModel = new UserModel();
            userModel.setName(name);
            database.getDAO().setUser(userModel);
        }
    };
    Runnable restore = new Runnable() {
        @Override
        public void run() {
            UserModel userModel = new UserModel();
            userModel = database.getDAO().getUser(id);
            final UserModel finalUserModel = userModel;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, finalUserModel.getName(), Toast.LENGTH_LONG).show();
                }
            });

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextname);
        editTextID = findViewById(R.id.editTextid);
        buttonSave = findViewById(R.id.buttonSave);
        buttonRestore = findViewById(R.id.buttonRestore);
        database = Room.databaseBuilder(this, Database.class, "db").fallbackToDestructiveMigration().build();
        handler = new Handler();
        buttonRestore.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                name = editTextName.getText().toString();
                editTextName.getText().clear();
                new Thread(save).start();
                break;
            case R.id.buttonRestore:
                if (Integer.parseInt(editTextID.getText().toString()) <= 0)
                    return;
                else
                    id = Integer.parseInt(editTextID.getText().toString());
                editTextName.getText().clear();
                new Thread(restore).start();
                break;
        }
    }
}
