package com.example.apiwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;


import com.example.apiwork.Backend.DataHold;
import com.example.apiwork.Backend.RecyclerViewAdapterUsers;
import com.example.apiwork.Backend.Users;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import static com.example.apiwork.Backend.DataHold.usersArrayList;

public class UserDataActivity extends AppCompatActivity  implements RecyclerViewAdapterUsers.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        ArrayList<Users>usersArrayList= DataHold.usersArrayList;
        System.out.println("Test"+ DataHold.usersArrayList.size());

        if(usersArrayList==null){

            new AlertDialog.Builder(UserDataActivity.this).setTitle("Confirm").setMessage("Data Not Received Please Try Again").setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserDataActivity.this.finish();
                }
            }).show();

        }
        else if(usersArrayList.size()<1){

            new AlertDialog.Builder(UserDataActivity.this).setTitle("Confirm").setMessage("Data Not Received Please Try Again").setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserDataActivity.this.finish();
                }
            }).show();

        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        RecyclerViewAdapterUsers recyclerViewAdapter= new RecyclerViewAdapterUsers(this,usersArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        recyclerViewAdapter.setOnItemClickListener(UserDataActivity.this);

    }

    @Override
    public void onItemClick(int position) {
        Users users = usersArrayList.get(position);
        String name = users.getName();

        Toast.makeText(UserDataActivity.this,name,Toast.LENGTH_LONG).show();
    }
}
