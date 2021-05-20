/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author pipet
 */
public class Tecnico extends Persona implements Serializable{
    private int añosExperiencia;
    private String especializacion;
    private LinkedList<Cubiculo> misCubiculos;
    private Date fechaCreacion;
    public Tecnico() {
    }

    public Tecnico(String cedula, String nombre, String apellido, String telefono,int añosExperiencia, String especializacion) {
        super(cedula, nombre, apellido, telefono);
        this.añosExperiencia = añosExperiencia;
        this.especializacion = especializacion;
        this.misCubiculos = this.misCubiculos=new LinkedList<>();
        Calendar miCalendario;
        miCalendario=Calendar.getInstance();
        
        this.fechaCreacion=miCalendario.getTime();
        System.out.println("FECHA CREACION-->"+this.fechaCreacion);
    }

   

    /**
     * @return the añosExperiencia
     */
    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    /**
     * @param añosExperiencia the añosExperiencia to set
     */
    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    /**
     * @return the especializacion
     */
    public String getEspecializacion() {
        return especializacion;
    }

    /**
     * @param especializacion the especializacion to set
     */
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    /**
     * @return the misCubiculos
     */
    public LinkedList<Cubiculo> getMisCubiculos() {
        return misCubiculos;
    }

    /**
     * @param misCubiculos the misCubiculos to set
     */
    public void setMisCubiculos(LinkedList<Cubiculo> misCubiculos) {
        this.misCubiculos = misCubiculos;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
