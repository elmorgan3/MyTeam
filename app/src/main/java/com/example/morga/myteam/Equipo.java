package com.example.morga.myteam;

/**
 * Created by morga on 27/01/2017.
 */

public class Equipo
{

    private int idEquipo;
    private String nombreEquipo;
    private String direccion;
    private String categoria;
    private String fotoEscudo;
    private int  usuario_IdUsuario;

    public Equipo (int idEquipo, String nombreEquipo,String direccion, String categoria, String fotoEscudo, int usuario_IdUsuario)
    {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.direccion = direccion;
        this.categoria = categoria;
        this.fotoEscudo = fotoEscudo;
        this.usuario_IdUsuario = usuario_IdUsuario;
    }
    public Equipo (){
        super();
    }

    public int getIdEquipo() { return idEquipo; }
    public String getNombreEquipo() { return nombreEquipo; }
    public String getDireccion() { return direccion; }
    public String getCategoria() { return categoria; }
    public String getFotoEscudo() { return fotoEscudo; }
    public int getUsuario_IdUsuario() { return usuario_IdUsuario; }

    public void setIdEquipo(int idEquipo) { this.idEquipo=idEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo=nombreEquipo; }
    public void setDireccion(String direccion) { this.direccion=direccion; }
    public void setCategoria(String categoria) { this.categoria=categoria; }
    public void setFotoEscudo(String fotoEscudo) { this.fotoEscudo=fotoEscudo; }
    public void setUsuario_IdUsuario(int usuario_IdUsuario) { this.usuario_IdUsuario=usuario_IdUsuario; }


}

