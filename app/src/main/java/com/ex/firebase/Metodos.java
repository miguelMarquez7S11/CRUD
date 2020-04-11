package com.ex.firebase;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Metodos
{

    public DatabaseReference iniciarDB(Context contexto)
    {
        FirebaseApp.initializeApp(contexto);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference();
        return referencia;
    }

    public boolean validar(String matriula, String nombre, String apellido )
    {
        boolean resultado = true;
        if (matriula.isEmpty() || nombre.isEmpty() || apellido.isEmpty())
        {
            resultado = false;
        }
        return resultado;
    }

    public boolean inserccion(String matriula, String nombre, String apellido, DatabaseReference referencia, Context contexto)
    {
        if(validar(matriula, nombre, apellido))
        {
            Persona alumno = new Persona(apellido, matriula, nombre);
            referencia.child("Alumno").child(matriula).setValue(alumno);
            Toast.makeText(contexto,"Operacion Correcta",Toast.LENGTH_SHORT).show();
            return true;

        }else {
            Toast.makeText(contexto,"Verifica los Campos",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean eliminar(String matriula, DatabaseReference referencia, Context contexto)
    {
        if(!matriula.isEmpty())
        {
            referencia.child("Alumno").child(matriula).removeValue();
            Toast.makeText(contexto,"Operacion Correcta",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(contexto,"Verifica los Campos",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
