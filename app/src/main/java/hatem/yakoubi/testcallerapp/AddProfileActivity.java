package hatem.yakoubi.testcallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hatem.yakoubi.testcallerapp.DAO.ProfileDAO;
import hatem.yakoubi.testcallerapp.DAOImp.ProfileDAOImplementation;

public class AddProfileActivity extends AppCompatActivity {

    Button btn_save ;
    EditText ednom,edphone,edadress, edemail;
    ProfileDAO profileDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        btn_save = findViewById(R.id.btsave_addProfil);
        ednom = findViewById(R.id.edname);
        edphone = findViewById(R.id.edphone);
        edadress = findViewById(R.id.edadress);
        edemail = findViewById(R.id.edemail);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=ednom.getText().toString();
                String email=edemail.getText().toString();
                String adress=edadress.getText().toString();
                String phone=edphone.getText().toString();
                Profile profile = new Profile(0, nom,phone,email,adress);
                //MainActivity.data.add(profile);
                profileDAO = new ProfileDAOImplementation(AddProfileActivity.this);
                long newRow =   profileDAO.addProfile(profile);
                if(newRow > 0){
                    Toast.makeText(AddProfileActivity.this, "Profile Added", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddProfileActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}