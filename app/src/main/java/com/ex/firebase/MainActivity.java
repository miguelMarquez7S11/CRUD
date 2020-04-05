package com.ex.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView mensaje;
    EditText matricula, nombre, apellido;
    FirebaseDatabase database;
    DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje = findViewById(R.id.mensaje);
        matricula = findViewById(R.id.matricula);
        nombre =  findViewById(R.id.nombre);
        apellido =  findViewById(R.id.apellido);
        iniciarDB();
    }

    public void iniciarDB()
    {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        referencia = database.getReference();
    }

    public boolean validar()
    {
        boolean resultado = false;
        if (!matricula.getText().toString().isEmpty()  || !nombre.getText().toString().isEmpty() || !apellido.getText().toString().isEmpty())
        {
            resultado = true;
        }
        return resultado;
    }

    public void limpiar()
    {
        matricula.setText("");
        nombre.setText("");
        apellido.setText("");
    }

    public void agregar(View v)
    {
        if(validar())
        {
            String mat = matricula.getText().toString();
            Persona  per =  new Persona(nombre.getText().toString(), apellido.getText().toString());


            referencia.child("persona").child(mat).setValue(per);

            mensaje.setText("INSERTADO CORRECTAMENTE");
            limpiar();
        }else {
            mensaje.setText("ERROR AL INSERTAR");
        }

    }
}
