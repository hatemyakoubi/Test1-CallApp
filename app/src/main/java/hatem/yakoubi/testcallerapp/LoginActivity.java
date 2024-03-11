package hatem.yakoubi.testcallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //declaration de composantes
    Button btnExit , btnLogin;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //recuperation des composantes
        btnExit = findViewById(R.id.btnExit_auth);
        btnLogin = findViewById(R.id.btnLogin_auth);
        email = findViewById(R.id.edEmail_auth);
        password = findViewById(R.id.edPassword_auth);

        //events
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //System.out.println("test");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperer les chaines saisies
                String e = email.getText().toString();
                String psw = password.getText().toString();
                if(e.equals("azer") && psw.equals("111")){
                    //pasage entre acivite
                    // intent: intention de lncer qlq chose
                    //params: activite courante.this, activite_cible.class
                    //pour lancer l'intent=> startActivity

                    Intent i = new Intent(LoginActivity.this, MainActivity.class );
                    startActivity(i);
                    finish();
                }else{
                    //message erreur
                    Toast.makeText(LoginActivity.this, "Erreur de saisie", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}