package hatem.yakoubi.testcallerapp;

import static hatem.yakoubi.testcallerapp.R.id.activity_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import hatem.yakoubi.testcallerapp.DAO.ProfileDAO;
import hatem.yakoubi.testcallerapp.DAOImp.ProfileDAOImplementation;

public class MainActivity extends AppCompatActivity {

    Button bt_add,bt_lst;
    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    List<Profile> profileList;
    ProfileDAO profileDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bt_add = findViewById(R.id.bt_add);
        bt_lst = findViewById(R.id.bt_list);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , AddProfileActivity.class);
                startActivity(i);

            }
        });

        bt_lst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,  ListView_profile.class);
                startActivity(i);

            }
        });

       // profileList = profileDAO.getProfiles();
       // profileAdapter = new ProfileAdapter(profileList,this);
       // recyclerView.setAdapter(profileAdapter);
    }
}