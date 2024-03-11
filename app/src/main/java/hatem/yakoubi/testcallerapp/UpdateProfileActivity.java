package hatem.yakoubi.testcallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import hatem.yakoubi.testcallerapp.DAO.ProfileDAO;
import hatem.yakoubi.testcallerapp.DAOImp.ProfileDAOImplementation;

public class UpdateProfileActivity extends AppCompatActivity {
    Button btn_edit, btn_cancel ;
    EditText ednom,edphone,edadress, edemail;

    private ProfileDAO profileDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btn_edit = findViewById(R.id.btsave_editProfil);
        btn_cancel = findViewById(R.id.btcancel_updateProfil);
        ednom = findViewById(R.id.edname_upadteProfile);
        edphone = findViewById(R.id.edphone_upadteProfile);
        edadress = findViewById(R.id.edadress_upadteProfile);
        edemail = findViewById(R.id.edemail_upadteProfile);
        // reuperation de données
        Intent i = getIntent();

/*        long id = Long.parseLong(i.getStringExtra("PROFILE_ID"));
        ednom.setText(i.getStringExtra("PROFILE_NAME"));
        edphone.setText(i.getStringExtra("PROFILE_PHONE"));
        edadress.setText(i.getStringExtra("PROFILE_ADDRESS"));
        edemail.setText(i.getStringExtra("PROFILE_EMAIL"));*/

        Profile profile =(Profile) i.getSerializableExtra("PROFILE");
        ednom.setText(profile.getName());
        edphone.setText(profile.getPhone());
        edadress.setText(profile.getAddress());
        edemail.setText(profile.getEmail());
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDAO = new ProfileDAOImplementation(UpdateProfileActivity.this);

                profile.setName(ednom.getText().toString());
                profile.setEmail(edemail.getText().toString());
                profile.setAddress(edadress.getText().toString());
                profile.setPhone(edphone.getText().toString());

                long newRow =  profileDAO.updateProfile(profile, profile.getId());
                if(newRow > 0){
                    Toast.makeText(UpdateProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(UpdateProfileActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}