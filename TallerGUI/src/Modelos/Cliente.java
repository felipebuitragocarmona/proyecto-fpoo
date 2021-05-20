/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author pipet
 */
public class Cliente extends Persona implements Serializable{
    private int añosFidelidad;
    private LinkedList<Vehiculo> misVehiculos;
    public Cliente() {
    }

    public Cliente(String cedula, String nombre,String apellido, String telefono,int añosFidelidad) {
        super(cedula, nombre,apellido ,telefono);
        this.añosFidelidad = añosFidelidad;
        this.misVehiculos=new LinkedList<>();
    }
    public String listarPlacas(){
        String respuesta="";
        for(Vehiculo actual : this.misVehiculos){
            respuesta=respuesta+actual.getPlaca()+",";
        }
        return  respuesta;
    }

    /**
     * @return the añosFidelidad
     */
    public int getAñosFidelidad() {
        return añosFidelidad;
    }

    /**
     * @param añosFidelidad the añosFidelidad to set
     */
    public void setAñosFidelidad(int añosFidelidad) {
        this.añosFidelidad = añosFidelidad;
    }

    /**
     * @return the misVehiculos
     */
    public LinkedList<Vehiculo> getMisVehiculos() {
        return misVehiculos;
    }

    /**
     * @param misVehiculos the misVehiculos to set
     */
    public void setMisVehiculos(LinkedList<Vehiculo> misVehiculos) {
        this.misVehiculos = misVehiculos;
    }
    
}
