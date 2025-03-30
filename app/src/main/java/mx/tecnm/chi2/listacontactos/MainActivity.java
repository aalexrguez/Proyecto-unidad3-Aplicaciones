package mx.tecnm.chi2.listacontactos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    SQLiteDatabase db;


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

        button_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin = new AdminSQLlite(MainActivity.this,"administracion",null,1);
                db = admin.getWritableDatabase();

                String id = editText_id.getText().toString().trim();
                String nombre = editText_nombre.getText().toString().trim();
                String telefono = editText_telefono.getText().toString().trim();
                String email = editText_email.getText().toString().trim();

                if(id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues registro = new ContentValues();
                registro.put("idcontacto",id);
                registro.put("nombre",nombre);
                registro.put("telefono",telefono);
                registro.put("email",email);

                long resultado = db.insert("Contacto",null,registro);
                db.close();
                if (resultado != -1) {
                    Toast.makeText(MainActivity.this, "Registro Agregado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al agregar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void cargarDatos(){
        admin = new AdminSQLlite(MainActivity.this, "administracion",null,1);
        db = admin.getReadableDatabase();

    }*/
}