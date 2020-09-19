package com.app.zerotolerance;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    public static View parentLayout;
    CardView card_zt_user, card_frontliner, card_mypha, card_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        parentLayout = findViewById(R.id.content);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_home);


        toolbar.setOverflowIcon(ContextCompat.getDrawable(HomeActivity.this,R.drawable.more_vert));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.itm_profile){
                    Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(i);
                }
                if (id == R.id.itm_logout){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                    alertDialogBuilder.setTitle("Yakin ingin keluar aplikasi?");
                    alertDialogBuilder
                            .setMessage("Klik Yes untuk keluar")
                            .setIcon(R.drawable.ic_info_outline)
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    new Logout(HomeActivity.this);
                                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                    //MainActivity.inst.finish();
                                    finish();
                                }
                            })
                            .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

//                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    finish();
//                    startActivity(loginIntent);
                }
                return false;
            }
        });


        card_zt_user = findViewById(R.id.card_zt_user);
        card_frontliner = findViewById(R.id.card_frontliner);
        card_mypha = findViewById(R.id.card_mypha);
        card_profile = findViewById(R.id.card_profile);
        card_zt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        card_frontliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        card_mypha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
