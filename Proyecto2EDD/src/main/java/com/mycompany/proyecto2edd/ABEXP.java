/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2edd;

/**
 *
 * @author mannith
 */
public class ABEXP {
    NodoArbolBinario raiz;
    
    public ABEXP(){
        raiz = null;
    }
    
    public ABEXP(String ola){
//        raiz = creacionArbolito(ola);
    }
    public void vaciarArbol(){
        raiz = null;
    }
    
    public void creacionNodo(Object dato){
        raiz = new NodoArbolBinario(dato);
    }
    
    public NodoArbolBinario crearArbolHijo(NodoArbolBinario izquierda, NodoArbolBinario derecha,
            NodoArbolBinario operador){
        operador.hijoIzq = izquierda;
        operador.hijoDer = derecha;
        return operador;
    }
    public boolean arbolIsVacio(){
        return raiz == null;
    }
    
    private String preOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + subArbol.dato.toString() + "\n" + preOrden(subArbol.hijoIzq, ola) + 
                    preOrden(subArbol.hijoDer, ola);
        }
        return cadena;    
    }
    
    private String inOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + inOrden(subArbol.hijoIzq, ola) + subArbol.dato.toString() + "\n" + 
                inOrden(subArbol.hijoDer, ola);
        }
        return cadena;
    }
    
    private String postOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + inOrden(subArbol.hijoIzq, ola) + inOrden(subArbol.hijoDer, ola) 
                + subArbol.dato.toString() + "\n";
        }
        return cadena;
    }
    private String toString(int a){
        String cadena= "";
        switch(a){
            case 0:
                cadena = preOrden(raiz, cadena);
                break;
            case 1:
                cadena = inOrden(raiz, cadena);
                break;
            case 2:
                cadena = postOrden(raiz, cadena);
                break;
        }
        return cadena;
    }
}
