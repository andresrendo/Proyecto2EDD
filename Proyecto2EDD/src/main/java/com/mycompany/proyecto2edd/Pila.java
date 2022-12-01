/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2edd;

/**
 *
 * @author mannith
 */
public class Pila {
    private NodoPila cima;
    
    public Pila(){
        cima = null;
    }
    
    public void add(NodoArbolBinario elemento){
        NodoPila nuevo;
        nuevo = new NodoPila(elemento);
        nuevo.siguiente = cima;
        cima = nuevo;
    }
    
    public boolean pilaIsVacia(){
        return cima == null;
    }
    
    public NodoArbolBinario cimaPila(){
        return cima.dato;
    }
    
    public void vaciarPila(){
        cima = null;
    }
    
    public NodoArbolBinario eliminar(){
        NodoArbolBinario aux = null;
        if(!pilaIsVacia()){
            aux = cima.dato;
            cima = cima.siguiente;
        }
        return aux;
    }
        public int tamaño(){
        int t = 1;
        NodoPila aux = cima;
        while(aux.siguiente!= null){
            t++;
            aux = aux.siguiente;
        }
        return t;
    }
}
