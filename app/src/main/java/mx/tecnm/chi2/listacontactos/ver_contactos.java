package mx.tecnm.chi2.listacontactos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ver_contactos extends AppCompatActivity {

Button button_regresar;
AdminSQLlite admin;
TextView textMultiLineContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contactos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button_regresar = findViewById(R.id.button_volver);
        textMultiLineContactos = findViewById(R.id.TextMultiLine_contactos);

        admin = new AdminSQLlite(this,"administracion",null,1);

        mostrarContactos();

        button_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }// cierre de onCreate

    public void mostrarContactos(){
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Contacto",null);

        StringBuilder contactos = new StringBuilder();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String telefono = cursor.getString(2);
            String email = cursor.getString(3);

            contactos.append("ID: ").append(id)
                    .append("\nNombre: ").append(nombre)
                    .append("\nTeléfono: ").append(telefono)
                    .append("\nEmail: ").append(email)
                    .append("\n----------------------\n");
        }

        cursor.close();
        db.close();

        textMultiLineContactos.setText(contactos.toString());
    }
}