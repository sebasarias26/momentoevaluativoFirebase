package co.com.myapp.cesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Button btn_iniciar_sesion;
    Button btn_volver_home;
    EditText input_email;
    EditText input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);  // Asegúrate de llamar a esto primero
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        btn_volver_home = findViewById(R.id.btn_volver_home);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);

        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = input_email.getText().toString();
                String contrasena = input_password.getText().toString();
                iniciarSesion(correo, contrasena);
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irAlDashBoardDesdeInicio() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void iniciarSesion(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            irAlDashBoardDesdeInicio();
                            Toast.makeText(InicioSesion.this, "Has iniciado sesión", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InicioSesion.this, "Error en los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
