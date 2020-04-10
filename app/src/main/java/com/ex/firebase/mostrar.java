package com.ex.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mostrar extends AppCompatActivity
{
    private List<Persona> listaAlumnos = new ArrayList<Persona>();
    MainActivity m = new MainActivity();
    EditText matricula, nombre, apellido;
    ListView lista;
    Persona seleccion;

    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista = findViewById(R.id.mos);
        matricula =  findViewById(R.id.mati);
        nombre =  findViewById(R.id.nombre);
        apellido =  findViewById(R.id.apellido);

        referencia = m.iniciarDB();
        mostrarDatos();
        select();

    }

    public void Eliminar(View v)
    {
        if(validar()) {
            Persona  alumno =  new Persona(apellido.getText().toString(), matricula.getText().toString(),nombre.getText().toString());
            referencia.child("Alumno").child(matricula.getText().toString()).removeValue();
            Toast.makeText(this,"Eliminado Correctamente",Toast.LENGTH_SHORT).show();
            limpiar();
        }else {
            Toast.makeText(this,"Verifica los Campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View v)
    {
        if(validar()) {
            Persona  alumno =  new Persona(apellido.getText().toString(), matricula.getText().toString(),nombre.getText().toString());
            referencia.child("Alumno").child(matricula.getText().toString()).setValue(alumno);
            Toast.makeText(this,"Actualizado Correctamente",Toast.LENGTH_SHORT).show();
            limpiar();
        }else {
            Toast.makeText(this,"Verifica los Campos",Toast.LENGTH_SHORT).show();
        }
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

    public void select()
    {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccion = (Persona) parent.getItemAtPosition(position);
                matricula.setText(seleccion.getMatricula());
                nombre.setText(seleccion.getNombre());
                apellido.setText(seleccion.getApellido());
            }
        });
    }

    public void regresar(View v)
    {
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }

    public void mostrarDatos()
    {
        referencia.child("Alumno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaAlumnos.clear();
                Persona p;
                for(DataSnapshot obj : dataSnapshot.getChildren())
                {
                    p = obj.getValue(Persona.class);
                    listaAlumnos.add(p);
                }
                ArrayAdapter<Persona> arrayAdapterPersona = new  ArrayAdapter<Persona>(mostrar.this,android.R.layout.simple_list_item_1, listaAlumnos);
                lista.setAdapter(arrayAdapterPersona);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
