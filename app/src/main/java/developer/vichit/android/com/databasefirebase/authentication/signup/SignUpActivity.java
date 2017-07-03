package developer.vichit.android.com.databasefirebase.authentication.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import developer.vichit.android.com.databasefirebase.R;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignUp;
    private EditText edEmail;
    private EditText edPassword;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);

        auth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = auth.getCurrentUser();
                                    Toast.makeText(SignUpActivity.this, "successfully", Toast.LENGTH_SHORT).show();

                                    Log.e("ppppp", user.getEmail());
                                } else {
                                    task.getException().printStackTrace();
                                    Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });

    }
}
