package co.com.myapp.cesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubirLibros extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("libros");

    EditText edIdLibro, edNombreLibro, edAutorLibro, edDescripcion;
    Button btnAtras, btnSubir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_libros);

        edIdLibro = findViewById(R.id.edIdLibro);
        edNombreLibro = findViewById(R.id.edNombreLibro);
        edAutorLibro = findViewById(R.id.edAutorLibro);
        edDescripcion = findViewById(R.id.edDescripcion);
        btnAtras = findViewById(R.id.btnAtras);
        btnSubir = findViewById(R.id.btnSubir);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirLibros();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAlDashBoard();
            }
        });
    }

    public void subirLibros() {
        String idLibro = edIdLibro.getText().toString();
        DatabaseReference nuevoLibro = reference.child(idLibro);

        String nombre = edNombreLibro.getText().toString();
        nuevoLibro.child("Nombre").setValue(nombre);

        String autor = edAutorLibro.getText().toString();
        nuevoLibro.child("Autor").setValue(autor);

        String descripcion = edDescripcion.getText().toString();
        nuevoLibro.child("Descripcion").setValue(descripcion);

        // Si necesitas agregar más campos, hazlo aquí

        Toast.makeText(getApplicationContext(), "Libro subido exitosamente", Toast.LENGTH_LONG).show();
    }

    public void irAlDashBoard() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}
