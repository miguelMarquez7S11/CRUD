package com.ex.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity
{
    Metodos m = new Metodos();
    EditText matricula, nombre, apellido;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricula = findViewById(R.id.matricula);
        nombre =  findViewById(R.id.nombre);
        apellido =  findViewById(R.id.apellido);

        referencia = m.iniciarDB(MainActivity.this);
    }

    public void limpiar()
    {
        matricula.setText("");
        nombre.setText("");
        apellido.setText("");
    }

    public void agregar(View v)
    {
        boolean resultado;
        resultado = m.inserccion(matricula.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), referencia, MainActivity.this);
        if (resultado){
            limpiar();
        }
    }

    public void btnMostrar(View v)
    {
        Intent mostrar = new Intent(this, mostrar.class);
        startActivity(mostrar);
    }
}