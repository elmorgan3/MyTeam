package com.example.morga.myteam;

import android.graphics.drawable.Drawable;

import java.sql.Time;

/**
 * Created by morga on 30/01/2017.
 */

public class HistorialEstadisticas
{
    private Drawable imagen;
    private String evento;
    private Time minuto;


    // Metodo contructor de la clase
    public HistorialEstadisticas (Drawable imagen, String evento, Time minuto)
    {
        //A los atrivutos le asignamos los parametros correspondiente
        this.imagen=imagen;
        this.evento= evento;
        this.minuto=minuto;
    }

    //Metodos getters y setters
    public Drawable getImagen () { return imagen; }
    public String getEvento () { return evento; }
    public Time getMinuto () { return minuto; }

    public void setImagen(Drawable imagen) {
        this.imagen=imagen;
    }
    public void setEvento(String evento) {
        this.evento=evento;
    }
    public void setMinuto(Time minuto) {
        this.minuto=minuto;
    }

}
