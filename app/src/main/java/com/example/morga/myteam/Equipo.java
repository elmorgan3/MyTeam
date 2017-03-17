package com.example.morga.myteam;

/**
 * Created by morga on 27/01/2017.
 */

public class Equipo
{

    private String nombreEquipo;
    private String categoriaEquipo;
    private String logoEquipo;

    public Equipo (String nombreEquipo, String categoriaEquipo, String logoEquipo)
    {
        this.nombreEquipo = nombreEquipo;
        this.categoriaEquipo = categoriaEquipo;
        this.logoEquipo = logoEquipo;
    }

    public String getNombreEquipo() { return nombreEquipo; }
    public String getCategoriaEquipo() { return categoriaEquipo; }
    public String getLogoEquipo() { return logoEquipo; }

    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo=nombreEquipo; }
    public void setCategoriaEquipo(String categoriaEquipo) { this.categoriaEquipo=categoriaEquipo; }
    public void setLogoEquipo (String logoEquipo) { this.logoEquipo=logoEquipo; }


}

