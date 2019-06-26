package nj.com.loginsystem;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Main2Activity extends AppCompatActivity {

    private Button buttonGetVerificationCode;
    private EditText editTextPhone,editTextCode;
    private String verificationId;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextCode = findViewById(R.id.editTextCode);
        buttonGetVerificationCode = findViewById(R.id.buttonGetVerificationCode);

        mAuth = FirebaseAuth.getInstance();

        buttonGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editTextPhone.getText().toString().trim();
                if(number.isEmpty() || number.length()<10){
                    editTextPhone.setError("Number is required");
                    editTextPhone.requestFocus();
                    return;
                }
                sendVerificationCode(number);
                findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = editTextCode.getText().toString().trim();
                        if(code.isEmpty() || code.length()<6){
                            editTextCode.setError("Enter Code");
                            editTextCode.requestFocus();
                            return;
                        }
                        verifyCode(code);
                    }
                });
            }
        });
    }

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String naam1= editTextPhone.getText().toString();
                            Intent intent = new Intent(Main2Activity.this,RegisterActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("phno",naam1);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Main2Activity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                45,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Main2Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}