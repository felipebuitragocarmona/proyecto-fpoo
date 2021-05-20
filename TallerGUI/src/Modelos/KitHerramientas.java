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
public class KitHerramientas implements Serializable{
    private String id;
    private int numeroLlaves;

    public KitHerramientas() {
    }

    public KitHerramientas(String id, int numeroLlaves) {
        this.id = id;
        this.numeroLlaves = numeroLlaves;
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
     * @return the numeroLlaves
     */
    public int getNumeroLlaves() {
        return numeroLlaves;
    }

    /**
     * @param numeroLlaves the numeroLlaves to set
     */
    public void setNumeroLlaves(int numeroLlaves) {
        this.numeroLlaves = numeroLlaves;
    }
     
}
