package tw.com.cronus.onlineTalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String TAG = "SignUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText sEtAccount = findViewById(R.id.SignUpAccount);
        InputChecker.AccountCheck(sEtAccount);

        final EditText sEtEmail = findViewById(R.id.SignUpEmail);
        InputChecker.EmailCheck(sEtEmail);

        final EditText sEtPassword = findViewById(R.id.SignUpPassword);
        final EditText sEtConfirmPassword = findViewById(R.id.SignUpPasswordConfirm);
        InputChecker.PasswordCheck(sEtPassword, sEtConfirmPassword);

        InputChecker.PasswordConfirm(sEtPassword, sEtConfirmPassword);

        final ProgressDialog dialog = new ProgressDialog(SignUp.this);
        dialog.setTitle("努力加載中 >.<");
        dialog.setMessage("正在新增帳戶");
        dialog.setCancelable(false);

        Button sBtnSignUp = findViewById(R.id.SignUpButton);
        sBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sEtAccount.getText().length() == 0) sEtAccount.setError("必填");
                if(sEtEmail.getText().length() == 0) sEtEmail.setError("必填");
                if(sEtPassword.getText().length() == 0) sEtPassword.setError("必填");
                if(sEtConfirmPassword.getText().length() == 0) sEtConfirmPassword.setError("必填");
                if(sEtAccount.getError() != null) return;
                if(sEtEmail.getError() != null) return;
                if(sEtPassword.getError() != null) return;
                if(sEtConfirmPassword.getError() != null) return;
                dialog.show();
                mAuth.createUserWithEmailAndPassword(sEtEmail.getText().toString(), sEtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user != null) {
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(sEtAccount.getText().toString()).build();
                                user.updateProfile(profileChangeRequest)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                dialog.cancel();
                                                if(task.isSuccessful()) {
                                                    Toast.makeText(SignUp.this, "Create successful", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    Toast.makeText(SignUp.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                        else {
                            dialog.cancel();
                            Toast.makeText(SignUp.this, "CreateFailed: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        Log.w(TAG, "Account create failed: " + task.getException());
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
