package pl.zhr.hak.wykrywaczchorob.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.zhr.hak.wykrywaczchorob.R;
import pl.zhr.hak.wykrywaczchorob.User;
import pl.zhr.hak.wykrywaczchorob.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextLogin;
    EditText editTextPassword;
    EditText editTextPassword2;
    Button buttonRegister;
    UserViewModel userViewModel;
    User user;
    TextView textViewLoginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextLogin = findViewById(R.id.editTextRegisterName);
        editTextPassword = findViewById(R.id.editTextRegisterPassword);
        editTextPassword2 = findViewById(R.id.editTextRegisterPassword2);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLoginNow = findViewById(R.id.textViewLoginNow);
        textViewLoginNow.setPaintFlags(textViewLoginNow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        buttonRegister.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString();
            String password = editTextPassword.getText().toString();
            String password2 = editTextPassword2.getText().toString();
            user = userViewModel.getItemByName(login);
            // sprawdź czy uzupełniono wszystkie pola
            if (login.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, getString(R.string.alldata), Toast.LENGTH_SHORT).show();
            }
            else {
                // sprawdź czy podany login istnieje w bazie
                if (userViewModel.checkItemByName(login) == 1) {
                    Toast.makeText(this, getString(R.string.double_login), Toast.LENGTH_SHORT).show();
                }
                else {
                    // sprawdź poprawność hasła
                    if (!password.equals(password2)) {
                        Toast.makeText(this, getString(R.string.wrong_password_repeat), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent mIntent = new Intent();
                        mIntent.putExtra("login", login);
                        mIntent.putExtra("password", password);
                        setResult(RESULT_OK, mIntent);
                        finish();
                    }
                }
            }
        });

        textViewLoginNow.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}