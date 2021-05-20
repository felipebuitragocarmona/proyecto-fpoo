/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

/**
 *
 * @author pipet
 */
public class Carro extends Vehiculo implements Serializable{
    private String tipoTransmision;

    public Carro() {
    }

    public Carro(String placa, String marca, int año, String color,String tipoTransmision) {
        super(placa, marca, año, color);
        this.tipoTransmision = tipoTransmision;
    }

    /**
     * @return the tipoTransmision
     */
    public String getTipoTransmision() {
        return tipoTransmision;
    }

    /**
     * @param tipoTransmision the tipoTransmision to set
     */
    public void setTipoTransmision(String tipoTransmision) {
        this.tipoTransmision = tipoTransmision;
    }
    
}
