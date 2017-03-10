package com.example.morga.myteam;

/**
 * Created by morga on 27/01/2017.
 */

public class Equipo
{

    private String nombreEquipo;
    private String logo;

    public Equipo (String nombreEquipo, String logo)
    {
        this.nombreEquipo = nombreEquipo;
        this.logo = logo;
    }

    public String getNombreEquipo() { return nombreEquipo; }
    public String getLogo() { return logo; }

    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo=nombreEquipo; }
    public void setLogo (String logo) { this.logo=logo; }


}

