package developer.vichit.android.com.databasefirebase.authentication.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import developer.vichit.android.com.databasefirebase.MainActivity;
import developer.vichit.android.com.databasefirebase.R;

public class SinginActivity extends AppCompatActivity {
    Button btnSignIn;
    private EditText edEmail;
    private EditText edPassword;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        edEmail = (EditText) findViewById(R.id.edEmail_signin);
        edPassword = (EditText) findViewById(R.id.edPassword_signin);
        btnSignIn = (Button) findViewById(R.id.btnSigni);

        auth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(SinginActivity.this, MainActivity.class));
                                    finish();

                                }else{
                                    Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
