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
public class Taller implements Serializable{
    private String nombre;
    private String direccion;
    private LinkedList<Cubiculo> misCubiculos;
    private LinkedList<Tecnico> misTecnicos;
    private LinkedList<Vehiculo> misVehiculos;
    private LinkedList<Cliente> misClientes;
    public Taller() {
    }

    public Taller(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.misCubiculos=new LinkedList<>();
        this.misTecnicos=new LinkedList<>();
        this.misVehiculos=new LinkedList<>();
        this.misClientes=new LinkedList<>();
    }
    public void crearCubiculo(String id){
        Cubiculo nuevoCubiculo=new Cubiculo(id);
        this.getMisCubiculos().add(nuevoCubiculo);
    }
    public void registrarTecnico(Tecnico nuevoTecnico){
        this.getMisTecnicos().add(nuevoTecnico);
    }
    public boolean eliminarTecnico(String cedula){
        boolean exito=false;
        Tecnico tecnicoBuscado=this.buscarTecnico(cedula);
        if (tecnicoBuscado!=null) {
            this.getMisTecnicos().remove(tecnicoBuscado);
            exito=true;
        }
        return exito;
    }
    public Tecnico buscarTecnico(String cedula){
        Tecnico buscado=null;
        int indice=0;
        while(buscado==null && indice<this.getMisTecnicos().size()){
            if (this.getMisTecnicos().get(indice).getCedula().equals(cedula)) {
                buscado=this.getMisTecnicos().get(indice);
            }
            indice++;
        }
        return buscado;
    }
    
    public Cliente buscarCliente(String cedula){
        Cliente buscado=null;
        int indice=0;
        System.out.println("--->"+this.misClientes.size());
        while(buscado==null && indice<this.misClientes.size()){
            if (this.misClientes.get(indice).getCedula().equals(cedula)) {
                buscado=this.misClientes.get(indice);
            }
            indice++;
        }
        return buscado;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
     * @return the misTecnicos
     */
    public LinkedList<Tecnico> getMisTecnicos() {
        return misTecnicos;
    }

    /**
     * @param misTecnicos the misTecnicos to set
     */
    public void setMisTecnicos(LinkedList<Tecnico> misTecnicos) {
        this.misTecnicos = misTecnicos;
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
     * @return the misClientes
     */
    public LinkedList<Cliente> getMisClientes() {
        return misClientes;
    }

    /**
     * @param misClientes the misClientes to set
     */
    public void setMisClientes(LinkedList<Cliente> misClientes) {
        this.misClientes = misClientes;
    }
    
}
