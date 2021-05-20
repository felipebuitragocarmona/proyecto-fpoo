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
public class Cubiculo implements Serializable{
    private String id;
    private LinkedList<Vehiculo> misVehiculos;
    private KitHerramientas miKitHerramientas;
    public Cubiculo() {
    }

    public Cubiculo(String id) {
        this.id = id;
        this.misVehiculos=new LinkedList<>();
    }
    
    public void registrarKit(KitHerramientas nuevoKit){
        this.setMiKitHerramientas(nuevoKit);
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return the miKitHerramientas
     */
    public KitHerramientas getMiKitHerramientas() {
        return miKitHerramientas;
    }

    /**
     * @param miKitHerramientas the miKitHerramientas to set
     */
    public void setMiKitHerramientas(KitHerramientas miKitHerramientas) {
        this.miKitHerramientas = miKitHerramientas;
    }
     
}
