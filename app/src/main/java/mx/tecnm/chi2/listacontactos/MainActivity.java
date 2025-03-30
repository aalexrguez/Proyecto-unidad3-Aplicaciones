package mx.tecnm.chi2.listacontactos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText_nombre;
    EditText editText_id;
    EditText editText_telefono;
    EditText editText_email;
    EditText editText_buscar_por_nombre;

    Button button_agregar;
    Button button_editar;
    Button button_buscar;
    Button button_eliminar;

    AdminSQLlite admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText_id = findViewById(R.id.editText_id);
        editText_nombre = findViewById(R.id.editText_nombre);
        editText_telefono = findViewById(R.id.editText_telefono);
        editText_email = findViewById(R.id.editText_email);
        editText_buscar_por_nombre = findViewById(R.id.editText_buscar_por_nombre);

        button_agregar = findViewById(R.id.button_agregar);
        button_buscar = findViewById(R.id.button_buscar);
        button_editar = findViewById(R.id.button_editar);
        button_eliminar = findViewById(R.id.button_eliminar);

    }
}