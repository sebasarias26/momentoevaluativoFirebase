package co.com.myapp.cesde;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class VerLibros extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("libros");

    EditText edIdLibro;
    Button btnBuscar, btnAtras;
    TextView tvNombreLibro, tvAutorLibro, tvDescripcionLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);

        edIdLibro = findViewById(R.id.edIdLibro);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnAtras = findViewById(R.id.btnAtras);
        tvNombreLibro = findViewById(R.id.tvNombreLibro);
        tvAutorLibro = findViewById(R.id.tvAutorLibro);
        tvDescripcionLibro = findViewById(R.id.tvDescripcionLibro);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDataLibros();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    public void recuperarDataLibros() {
        String libroId = edIdLibro.getText().toString();
        DatabaseReference referenciaLibro = reference.child(libroId);

        referenciaLibro.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    tvNombreLibro.setText("Nombre: " + nombre);

                    String autor = snapshot.child("Autor").getValue(String.class);
                    tvAutorLibro.setText("Autor: " + autor);

                    String descripcion = snapshot.child("Descripcion").getValue(String.class);
                    tvDescripcionLibro.setText("Descripcion: " + descripcion);
                } else {
                    Toast.makeText(getApplicationContext(), "Libro no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("VerLibros", "Error al obtener datos: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error al obtener datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}