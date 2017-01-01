package com.journaldev.youplusmegaevent;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lenovo on 9/27/2016.
 */
public class LoginActivity  extends Activity {
    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);


// create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        session = new SessionManager(getApplicationContext());
// Get The Refference Of Buttons
        btnSignIn = (Button)findViewById(R.id.btnLogin);

// Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for NewItemActivity abd Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), PromoterPageActivit.class);
                startActivity(intentSignUP);
            }
        });

        final EditText editTextUserName = (EditText)findViewById(R.id.protometnumberEditText);
       // final EditText editTextPassword = (EditText)findViewById(R.id.editTextPasswordToLogin);



// Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// get The User name and Password
                String userName = editTextUserName.getText().toString();
              //  String password = editTextPassword.getText().toString();
                System.out.println("lllll" + userName );
// fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
                session.createLoginSession(userName);

// check if the Stored password matches with Password entered by user
                if (userName.equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intentmain = new Intent(LoginActivity.this, HomeFragment.class);
                    finish();
                    startActivity(intentmain);
                    //startActivity(new Intent(Home.this, HomeActivity.class));

                    // dialog.dismiss();
                } else {
                    Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }
}
