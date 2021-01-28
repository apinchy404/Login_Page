package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText number,pass;
    Button btnSubmit;
    CheckBox cbRememberMe;



    SharedPreferences sp;

    SharedPreferences.Editor editor;

    private final String SP_NAME=" com.example.loginpage.login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//       login=findViewById(R.id.txt_login);
//        cell=findViewById(R.id.txt_cell);
        number=findViewById(R.id.et_number);
//        password=findViewById(R.id.txt_pass);
        pass=findViewById(R.id.et_pass);
        btnSubmit=findViewById(R.id.btn_submit);
        cbRememberMe=findViewById(R.id.cb_remember_me);

        sp=getSharedPreferences(SP_NAME,MODE_PRIVATE);
        editor=sp.edit();

        if(sp.contains("et_number")){
            String u=sp.getString("et_number","not found");
            number.setText(u.toString());
        }
        if(sp.contains("et_pass"))
        {
            String p=sp.getString("et_pass","not found");
            pass.setText(p.toString());
        }
        if(sp.contains("cb_remember_me"))
        {
            Boolean b = sp.getBoolean("cb_remember_me",false);
            cbRememberMe.setChecked(b);
        }





        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etnumber=number.getText().toString();
                String etpass=pass.getText().toString();



                if (cbRememberMe.isChecked()) {


                    Boolean boolIsChecked=cbRememberMe.isChecked();
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("et_number",number.getText().toString());
                    editor.putString("et_pass",pass.getText().toString());
                    editor.putBoolean("cb_remember_me",boolIsChecked);
                    editor.apply();



                }
                else {
                    sp.edit().clear().apply();
                }

                if(etnumber.isEmpty() || etpass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter", Toast.LENGTH_SHORT).show();
                }

                else if(etnumber.length()!=11)
                {
                    Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                }
                else if (pass.length()<4)
                {

                    Toast.makeText(MainActivity.this, "Password must be 4 digit", Toast.LENGTH_SHORT).show();

                }
                else if(!etnumber.startsWith("01"))
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}