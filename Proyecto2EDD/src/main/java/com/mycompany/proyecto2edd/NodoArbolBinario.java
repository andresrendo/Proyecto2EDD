/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2edd;

/**
 *
 * @author mannith
 */
public class NodoArbolBinario {
    Object dato;
    NodoArbolBinario hijoIzq;
    NodoArbolBinario hijoDer;
    
    public NodoArbolBinario(Object elemento){
        dato = elemento;
        hijoIzq = null;
        hijoDer = null;
    }
}
