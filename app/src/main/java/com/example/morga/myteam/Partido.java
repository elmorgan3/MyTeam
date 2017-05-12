package com.example.morga.myteam;

import java.util.Date;

/**
 * Created by Morgan on 11/05/2017.
 */

public class Partido {
    private  int idPartido;
    private String fechaPartido;
    private int jornada;
    private String rival;
    private boolean local;
    private int equipo_IdEquipo;

    public Partido (int idPartido, String fechaPartido, int jornada, String rival, boolean local, int equipo_IdEquipo)
    {
        this.idPartido = idPartido;
        this.fechaPartido = fechaPartido;
        this.jornada = jornada;
        this.rival = rival;
        this.local = local;
        this.equipo_IdEquipo = equipo_IdEquipo;
    }
    public Partido (){
        super();
    }

    public int getIdPartido() { return idPartido; }
    public String getFechaPartido() { return fechaPartido; }
    public int getJornada() { return jornada; }
    public String getRival() { return rival; }
    public boolean getLocal() { return local; }
    public int getEquipo_IdEquipo() { return equipo_IdEquipo; }

    public void setIdPartido(int idPartido) { this.idPartido=idPartido; }
    public void setFechaPartido(String fechaPartido) { this.fechaPartido=fechaPartido; }
    public void setJornada(int jornada) { this.jornada=jornada; }
    public void setRival(String rival) { this.rival=rival; }
    public void setLocal(boolean local) { this.local=local; }
    public void setEquipo_IdEquipo(int equipo_IdEquipo) { this.equipo_IdEquipo=equipo_IdEquipo; }

}


