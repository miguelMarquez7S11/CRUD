package com.ex.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText matricula, nombre, apellido;
    FirebaseDatabase database;
    DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricula = findViewById(R.id.matricula);
        nombre =  findViewById(R.id.nombre);
        apellido =  findViewById(R.id.apellido);

        iniciarDB();
    }

    public DatabaseReference iniciarDB()
    {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        referencia = database.getReference();
        return referencia;
    }

    public boolean validar()
    {
        boolean resultado = true;
        if (matricula.getText().toString().isEmpty()  || nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty())
        {
            resultado = false;
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
        if(validar()) {
            Persona  alumno =  new Persona(apellido.getText().toString(), matricula.getText().toString(),nombre.getText().toString());
            referencia.child("Alumno").child(matricula.getText().toString()).setValue(alumno);
            Toast.makeText(this,"Insertado Correctamente",Toast.LENGTH_SHORT).show();
            limpiar();
        }else {
            Toast.makeText(this,"Verifica los Campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrar(View v)
    {
        Intent mostrar = new Intent(this, mostrar.class);
        startActivity(mostrar);
    }
}
