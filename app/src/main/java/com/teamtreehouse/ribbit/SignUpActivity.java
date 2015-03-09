package com.teamtreehouse.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SignUpActivity extends Activity {

    @InjectView(R.id.usernameLoginField)
    EditText mUsername;

    @InjectView(R.id.passwordLoginField)
    EditText mPassword;

    @InjectView(R.id.emailField)
    EditText mEmail;

    @InjectView(R.id.signButton)
    Button mSignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.signButton)
    void signIn() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String email = mEmail.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
            builder.setMessage("Please, fill in all required data!")
                    .setTitle("Error")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            setProgressBarIndeterminateVisibility(true);
            ParseUser parseUser = new ParseUser();
            parseUser.setUsername(username);
            parseUser.setPassword(password);
            parseUser.setEmail(email);
            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    setProgressBarIndeterminateVisibility(false);
                    if (e==null){
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle("Error")
                                .setPositiveButton("OK", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
            });
        }
    }
}
