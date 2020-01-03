package tw.com.cronus.onlineTalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
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
                if (charSequence.length() == 0) return;
                if (!charSequence.toString().matches(getString(R.string.regexEmail)))
                    sEtEmail.setError("Email不符合格式!!!");
            }
        });     //email格式判斷

        final Button sBtnLogin = findViewById(R.id.loginLoginBtn);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cuttentUser = mAuth.getCurrentUser();

    }
}
