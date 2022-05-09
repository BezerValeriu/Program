package com.example.greenflagproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {

    private EditText mEditTextEmail, password1, password2;
    private Button mButton;
    DatabaseHelper databaseHelper;
    Pattern password_pattern=    Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        mEditTextEmail = findViewById(R.id.editTextTextEmailAddress);
        password1 = findViewById(R.id.editTextTextPassword);
        password2 = findViewById(R.id.editTextTextPassword2);
        mButton = findViewById(R.id.button2);

        databaseHelper = new DatabaseHelper(this);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String email =  mEditTextEmail.getText().toString();
                   String Password = password1.getText().toString();
                  // String Password2 = password2.getText().toString();
                //checks for validation by calling the methods before storing the data into database
                if (validateEmail() && validatePass1()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("email", email);
                    contentValues.put("password", Password);

                    //all the value successfully added after been validated
                    databaseHelper.insertgreenFlagdetails(contentValues);
                    Intent in =  new Intent(MainActivity2.this, MainActivity3.class);
                    startActivity(in);

                    //    validateEmail();
                    //   validatePass1();
                    //   validatePass2();
                }
            }
        });


    /*
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailAddress =  mEditTextEmail.getText().toString().trim();
            String pass1 = password1.getText().toString().trim();
            String pass2 = password2.getText().toString().trim();

         //   mButton.setEnabled(!emailAddress.isEmpty() && !pass1.isEmpty());
            mButton.setEnabled(!emailAddress.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

     */
    }

    private boolean validatePass1() {
        String pass1 = password1.getText().toString().trim();
        String pass2 = password2.getText().toString().trim();
        /*
        else if(!email.matches("a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
            mEditTextEmail.requestFocus();
            mEditTextEmail.setError("ENTER VALID EMAIL");
            return false;
        }*/

        if (pass1.isEmpty()) {
            password1.setError("Field cannot be empty");
            return false;
        }
//        if (pass1.matches("\"^\" +\n" +
//                "                \"(?=.*[0-9])\" +         //at least 1 digit\n" +
//                "                \"(?=.*[a-z])\" +         //at least 1 lower case letter\n" +
//                "                \"(?=.*[A-Z])\" +         //at least 1 upper case letter\n" +
//                "                \"(?=.*[a-zA-Z])\" +      //any letter\n" +
//                "                \"(?=.*[@#$%^&+=])\" +    //at least 1 special character\n" +
//                "                \".{4,}\" +               //at least 4 characters\n" +
//                "                \"$\";")) {
        if(!pass1.matches(String.valueOf(password_pattern))){
            password1.requestFocus();
            password1.setError("ENTER VALID PASSWORD");
            return false;
        }
        if (!pass1.equals(pass2)) {
            password1.setError("Passwords does not match");
            return false;
        } else {
            Toast.makeText(MainActivity2.this, "Passwords matches", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private boolean validateEmail() {
        String email = mEditTextEmail.getText().toString().trim();
        if (email.length() == 0) {
            mEditTextEmail.requestFocus();
            mEditTextEmail.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditTextEmail.setError("Email invalid");
            return false;
        } else {
            mEditTextEmail.setError(null);
            return true;
        }
        /*
        else if(!email.matches("a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
            mEditTextEmail.requestFocus();
            mEditTextEmail.setError("ENTER VALID EMAIL");
            return false;
        }*/

        /*
        else if(password.matches("\"^\" +\n" +
                "                \"(?=.*[0-9])\" +         //at least 1 digit\n" +
                "                \"(?=.*[a-z])\" +         //at least 1 lower case letter\n" +
                "                \"(?=.*[A-Z])\" +         //at least 1 upper case letter\n" +
                "                \"(?=.*[a-zA-Z])\" +      //any letter\n" +
                "                \"(?=.*[@#$%^&+=])\" +    //at least 1 special character\n" +
                "                \".{4,}\" +               //at least 4 characters\n" +
                "                \"$\";")){
            password1.requestFocus();
            password1.setError("ENTER VALID PASSWORD");
            return false;
        }
        else if(password != password2){
            Toast.makeText(MainActivity2.this, "Password does not match", Toast.LENGTH_SHORT).show();
         //   mButton.setEnabled(false);
         //   mButton.setBackgroundColor(getResources().getColor(R.color.teal_200));
        }
         */
        // return false;
    }
}
