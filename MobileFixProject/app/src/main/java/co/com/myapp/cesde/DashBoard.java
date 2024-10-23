package co.com.myapp.cesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashBoard extends AppCompatActivity {
    Button btnSubirLibros;
    Button btnVerLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);

        btnSubirLibros = findViewById(R.id.btnSubirLibros);
        btnVerLibros = findViewById(R.id.btnVerLibros);

        btnSubirLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irASubirLibros();
            }
        });

        btnVerLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irVerDatosUsuario();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irDashBoardDesdeInicio() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void irASubirLibros() {
        Intent intent = new Intent(this, SubirLibros.class);
        startActivity(intent);
    }

    public void irVerDatosUsuario() {
        Intent intent = new Intent(this, VerLibros.class);
        startActivity(intent);
    }


}
