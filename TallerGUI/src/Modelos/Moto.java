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
public class Moto extends Vehiculo implements Serializable{
    private int numeroPatas;

    public Moto() {
    }

    public Moto( String placa, String marca, int año, String color,int numeroPatas) {
        super(placa, marca, año, color);
        this.numeroPatas = numeroPatas;
    }

    /**
     * @return the numeroPatas
     */
    public int getNumeroPatas() {
        return numeroPatas;
    }

    /**
     * @param numeroPatas the numeroPatas to set
     */
    public void setNumeroPatas(int numeroPatas) {
        this.numeroPatas = numeroPatas;
    }
    
}
