package com.ex.firebase;

public class Persona
{
    private String matricula;
    private String apellido;
    private String nombre;

    public Persona(String apellido,String matricula, String nombre) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.apellido = apellido;
    }

    public Persona()
    {

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString(){
        return nombre;
    }
}

