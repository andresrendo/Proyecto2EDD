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
    private int prioridad(char c){
        int p = 100;
        switch(c){
            case '^':
                p = 30;
                break;
            case '*':
            case '-':
                p = 10;
                break;
            default:
            p = 0;
        }
        return p;
    }
    private boolean esOperador(char c){
        boolean resultado;
        switch(c){
            case'(':
            case')':
            case'^':
            case'*':
            case'/':
            case'+':
            case'-':
                resultado = true;
                break;
            default:
                resultado = false;
        }
        return resultado;
    }
    private NodoArbolBinario creacionABE(String cadena){
        Pila PilaOperadores;
        Pila PilaExpresiones;
        NodoArbolBinario token;
        NodoArbolBinario op1;
        NodoArbolBinario op2;
        NodoArbolBinario op;

        PilaOperadores = new Pila();
        PilaExpresiones = new Pila();
        char caracterEvaluado;
        for(int i=0; i< cadena.length(); i++){
            caracterEvaluado = cadena.charAt(i);
            token = new NodoArbolBinario(caracterEvaluado);
            if(!esOperador(caracterEvaluado)){
                PilaExpresiones.add(token);
            }else{
                switch(caracterEvaluado){
                    case'(':
                    PilaOperadores.add(token);
                    break;
                    case')':
                    while(!PilaOperadores.pilaIsVacia() &&!PilaOperadores.cimaPila().dato.equals('(')){
                        op2 = PilaExpresiones.eliminar();
                        op1 = PilaExpresiones.eliminar();
                        op = PilaOperadores.eliminar();
                        op = crearArbolHijo(op2, op1, op);
                        PilaExpresiones.add(op);
                    }
                    PilaOperadores.eliminar();
                    break;
                    default:
                        while(!PilaOperadores.pilaIsVacia() && prioridad(caracterEvaluado) <= prioridad(PilaOperadores.cimaPila().dato.toString().charAt(0))){
                            op2 = PilaExpresiones.eliminar();
                            op1 = PilaExpresiones.eliminar();
                            op = PilaExpresiones.eliminar();
                            op = crearArbolHijo(op2, op1, op);
                            PilaExpresiones.add(op);
                        }
                        PilaOperadores.add(token);
                    }
                }
        }
        while(!PilaOperadores.pilaIsVacia()){
                    op2 = PilaExpresiones.eliminar();
                    op1 = PilaExpresiones.eliminar();
                    op = PilaOperadores.eliminar();
                    op = crearArbolHijo(op2, op1, op);
                    PilaExpresiones.add(op);
        }
        op = PilaExpresiones.eliminar();
        return op;
    }
    public double EvaluaExpresion(){
        return evaluacion(raiz);
    }
    private double evaluacion(NodoArbolBinario subArbolBinario){
        double acum = 0;
        if(!esOperador(subArbolBinario.dato.toString().charAt(0))){
            return Double.parseDouble(subArbolBinario.dato.toString());
       
        }else{
            switch(subArbolBinario.dato.toString().charAt(0)){
                case '^':
                    acum = acum + Math.pow(evaluacion(subArbolBinario.hijoIzq), evaluacion(subArbolBinario.hijoDer));
                    break;
                case '*':
                    acum = acum + evaluacion(subArbolBinario.hijoIzq) * evaluacion(subArbolBinario.hijoDer);
                    break;
                case '/':
                    acum = acum + evaluacion(subArbolBinario.hijoIzq) / evaluacion(subArbolBinario.hijoDer);
                    break;
                case '+':
                    acum = acum + evaluacion(subArbolBinario.hijoIzq) + evaluacion(subArbolBinario.hijoDer);
                    break;
                case '-':
                    acum = acum + evaluacion(subArbolBinario.hijoIzq) - evaluacion(subArbolBinario.hijoDer);
                    break;
            }
        }
        return acum;
    }
} // final

