package co.com.myapp.cesde;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userDataView extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("usuarios");


    Button btnConsultarUsuario;
    Button btnVolverDashBoard1;

    EditText inputIngresarId;

    TextView mostrarId;
    TextView mostrarNombre;
    TextView mostrarApellido;
    TextView mostrarTelefono;
    TextView mostrarCorreo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_data_view);


        btnConsultarUsuario = findViewById(R.id.btn_buscar_usuario);
        btnVolverDashBoard1 = findViewById(R.id.btn_volver_dash1);
        inputIngresarId = findViewById(R.id.input_id_buscar);
        mostrarId = findViewById(R.id.mostrar_id_usuario);
        mostrarNombre = findViewById(R.id.mostrar_nombre_usuario);
        mostrarApellido = findViewById(R.id.mostrar_apellido);
        mostrarTelefono = findViewById(R.id.mostrar_telefono);
        mostrarCorreo = findViewById(R.id.mostrar_correo);



        btnConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDataUsuario();
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void recuperarDataUsuario(){

        String usuarioId = inputIngresarId.getText().toString();

        DatabaseReference refenciaUsuario = reference.child(usuarioId);

        refenciaUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){


                    String usuarioId = inputIngresarId.getText().toString();
                    mostrarId.setText(usuarioId);

                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    mostrarNombre.setText("Nombre " + nombre);

                    String apellido = snapshot.child("Apellildo").getValue(String.class);
                    mostrarApellido.setText(apellido);

                    String telefono = snapshot.child("Telefono").getValue(String.class);
                    mostrarTelefono.setText(telefono);

                    String correo = snapshot.child("Correo").getValue(String.class);
                    mostrarCorreo.setText(correo);



                } else {
                    Toast.makeText(getApplicationContext(), "Datos no encontrados", Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}