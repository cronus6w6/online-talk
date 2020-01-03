package tw.com.cronus.onlinetalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText sEtEmail = findViewById(R.id.loginEtEmail);
        sEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(charSequence.length() == 0) return;
                if(!charSequence.toString().matches(getString(R.string.regexEmail))) sEtEmail.setError("Email不符合格式!!!");
            }
        });     //email格式判斷

        final Button sBtnLogin = findViewById(R.id.loginLoginBtn);

    }
}
