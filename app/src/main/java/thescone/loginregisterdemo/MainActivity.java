package thescone.loginregisterdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Register.RegisterResponse {

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;

    private Button buttonRegister;
    private Button buttonLogin;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: set reference from activity_main

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        // TODO: for every button, make it setOnClickListener
        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // TODO: since there are 2 buttons, we use switch case in this
        /***
         * Refer Java for more info on Switch Case
         */

        int id = v.getId();

        switch (id) {
            case R.id.buttonRegister:
                registerUser();
                break;
            case R.id.buttonLogin:
                startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void registerUser() {

        // TODO: capture the text in EditText

        String name = editTextName.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();

        // TODO: and send it to Register.java for registration handling
        /***
         * Progress Dialog is the loading circle, to show that the data transferring is in process
         */
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(null);
        progressDialog.show();
        new Register(this).execute(name,username,password,email);
    }

    @Override
    public void processFinished(String output) {

        // TODO: this method is to handle the message sent by register.php, regarding the registration status
        /***
         * Refer Android Snackbar tutorial for more info
         * - Snackbar is a bar that will pop up under the screen, like Toast
         */

        progressDialog.dismiss();

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, output, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
