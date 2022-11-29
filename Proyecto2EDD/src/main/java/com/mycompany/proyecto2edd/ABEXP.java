/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2edd;

import javax.swing.JOptionPane;

/**
 *
 * @author mannith
 */
public class ABEXP {
    public NodoArbolBinario raiz;
    
    public ABEXP(){
        raiz = null;
    }
    //Constructor del ArbolBinario de Expresiones. Si la expresion recibida del archivo de texto empieza con un operador, convierte la expresion
    // de prefija a posfija, y luego a infija para crear el arbol. Si termina en un operador, convierte la expresion de postfija a infija y crea el arbol. Y 
    // si no, simplemente crea el arbol.
    public ABEXP(String ola){
        ola = ola.replace("\n", "");
        
        if(esInPos(String.valueOf(ola.charAt(ola.length()-1)))){ //Postfija
            raiz = creacionABEPS(ola);
            
        }else if (esInPos(String.valueOf(ola.charAt(0)))){//Prefija. Voltea la expresion para convertirla en posfija.
            String y = "";
            for (int i = ola.length()-1; i >= 0; i--) {
                y += String.valueOf(ola.charAt(i));
            }
            raiz = creacionABEPF(y);
        }
        
        else{//Infija
            raiz=creacionABE(ola);
        }
    }
    public void vaciarArbol(){
        raiz = null;
    }
    
    public void creacionNodo(Object dato){
        raiz = new NodoArbolBinario(dato);
    }
    
    
//    -----------------------------------------------------------------
//   | Recibe un nodo y le asigna sus hijos, creando un sub-arbol.     |
//    -----------------------------------------------------------------
    public NodoArbolBinario crearArbolHijo(NodoArbolBinario izquierda, NodoArbolBinario derecha,
            NodoArbolBinario operador){
        operador.hijoIzq = izquierda;
        operador.hijoDer = derecha;
//        ***JOptionPane.showMessageDialog(null, operador.hijoDer);***
//        ***JOptionPane.showMessageDialog(null, operador.hijoIzq);***
//        ***JOptionPane.showMessageDialog(null, operador.dato);***
        return operador;
    }
    
    public boolean arbolIsVacio(){
        return raiz == null;
    }

    
//    -------------------------------------------------------------------------------------------------------------
//   | Recibe un nodo y recorre el árbol en preorden, guardando la informacion de cada recorrido en la cadena.     |
//    -------------------------------------------------------------------------------------------------------------
    public String preOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + " "+ subArbol.dato.toString() + " "+ "\n" + preOrden(subArbol.hijoDer, ola) +" "+ preOrden(subArbol.hijoIzq, ola);
//            ***JOptionPane.showMessageDialog(null, subArbol);***
//            ***JOptionPane.showMessageDialog(null, subArbol.dato);***
//            ***JOptionPane.showConfirmDialog(null, "preorden" + cadena);***
        }
        return cadena;    
    }

//    -------------------------------------------------------------------------------------------------------------
//   | Recibe un nodo y recorre el árbol en inorden, guardando la informacion de cada recorrido en la cadena.     |
//    -------------------------------------------------------------------------------------------------------------
    
    public String inOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + " "+inOrden(subArbol.hijoDer, ola) + " "+ subArbol.dato.toString() + " "+"\n" + inOrden(subArbol.hijoIzq, ola);
        }
        return cadena;
    }

//    -------------------------------------------------------------------------------------------------------------
//   | Recibe un nodo y recorre el árbol en postorden, guardando la informacion de cada recorrido en la cadena.    |
//    -------------------------------------------------------------------------------------------------------------
    
    public String postOrden(NodoArbolBinario subArbol, String ola){
        String cadena;
        cadena = "";
        if(subArbol != null){
            cadena = ola + " "+ postOrden(subArbol.hijoDer, ola) + " "+ postOrden(subArbol.hijoIzq, ola) + " "+ subArbol.dato.toString() + "\n";
        }
        return cadena;
    }
    
//    --------------------------------------------------------------------------------------------------------------------------------------
//   | Recibe un numero para llamar a la funcion del orden solicitado. Guarda las funciones de inorden, preorden y postorden en una sola.    |
//    ---------------------------------------------------------------------------------------------------------------------------------------
    public String toString(int a){
        String cadena= "";
        switch(a){
            case 0:
                
                cadena = preOrden(raiz, cadena);
//                JOptionPane.showConfirmDialog(null, "RECIBIDO");
//                JOptionPane.showConfirmDialog(null, cadena);

                break;
            case 1:
                cadena = inOrden(raiz, cadena);
                break;
            case 2:
                cadena = postOrden(raiz, cadena);
                break;
        }
        cadena = cadena.replace("\n", "");
        return cadena;
    }
    
//    -------------------------------------------------
//   | Recibe un operador y devuelve su prioridad.     |
//    -------------------------------------------------
    private int prioridad(String c){
        int p = 100;
        switch(c){
            case "^":
                p = 30;
                break;
            case "*":
                p=20;
                break;
            case "/":
                p=20;
                break;
            case "+":
                p = 10;
            case "-":
                p = 10;
                break;
            default:
                p = 0;
        }
        return p;
    }
    
//    -------------------------------------------------------------------------------------------------------------
//   | Devuelve si el String es un operador sin contar los parentesis. Básicamente, se utiliza para saber si el    |
//   | primer o último caracter de la cadena recibida por texto es un operador y determinar en qé orden está. Como |
//   | los paréntesis pueden o no estar en cualquier stio de la notación normal, no sirven para identificarla.     |
//    -------------------------------------------------------------------------------------------------------------
    public boolean esInPos(String c){
        boolean resultado;
        switch(c){
            case"^":
            case"*":
            case"/":
            case"+":
            case"-":
                resultado = true;
                break;
            default:
                resultado = false;
        }
        return resultado;
    }
    
//    --------------------------------------------
//   | Devuelve si el String es un operador.     |
//    --------------------------------------------
    private boolean esOperador(String c){
        boolean resultado;
        switch(c){
            case"(":
            case")":
            case"^":
            case"*":
            case"/":
            case"+":
            case"-":
                resultado = true;
                break;
            default:
                resultado = false;
        }
        return resultado;
    }
    
//    ----------------------------------------------------------------------------------------------------
//   | Crea el Arbol Binario a partir de una expresion en inorden u notacion normal, y devuelve la raiz.  |
//    ----------------------------------------------------------------------------------------------------    
    public NodoArbolBinario creacionABE(String cadena){
        Pila PilaOperadores;
        Pila PilaExpresiones;
        NodoArbolBinario token;
        NodoArbolBinario op1;
        NodoArbolBinario op2;
        NodoArbolBinario op;

        PilaOperadores = new Pila();
        PilaExpresiones = new Pila();
        String caracterEvaluado;
//    -------------------------------------------------------------------------------------------------------------------------------
//   | Primero, se analiza en cada iteracion si el caracter seleccionado es un número, y de ser así, se comprueba si el siguiente.   |
//   | caracter es otro numero, y así hasta encontrarse con un operador. Se utiliza para saber si el número tiene más de dos dígitos,|
//   | para que se mantengan juntos y no se separen.                                                                                 |
//    -------------------------------------------------------------------------------------------------------------------------------
//
        for(int i=0; i< cadena.length(); i++){
            caracterEvaluado = String.valueOf(cadena.charAt(i));
//          ***JOptionPane.showMessageDialog(null, String.valueOf(cadena.charAt(i)));***
            if(!esOperador(String.valueOf(cadena.charAt(i)))){
                for (int j = i+1; j < cadena.length(); j++) {
//                  ***JOptionPane.showMessageDialog(null, String.valueOf(cadena.charAt(j)));***
                    if (!esOperador(String.valueOf(cadena.charAt(j)))){
                        caracterEvaluado += String.valueOf(cadena.charAt(j));
                        i++;
                    }else{
                        break;
                    }
                }
            }
            token = new NodoArbolBinario(caracterEvaluado);
//    ----------------------------------------------------------------------------------------------------------------------------------------
//   | Si la cadena no es un operador, se guarda en la pila de numeros. Sino, se evalua si es un "(" o un ")". Si es el primero, se apila.    |
//   | en la de operadores. Si es el segundo, se van sacando dos numeros y un operador para crear un subarbol con el operador como raiz,      |
//   | hasta que se encuentre con un "(". Si no se cumple ninguno de los dos casos, se hace lo mismo que en ")" hasta que la prioridad del    |
//   | caracter evaluado del principio sea mayor a la del operador en la cima de la pila, y luego se agrega el caracter evaluado a dicha pila.|
//   | Una vez evaluada toda la cadena, se vacia la pila de operadores con el mismo proceso de ")", y la raiz de cada subarbol se agrega a la |
//   | pila de expresiones, hasta que quede un único elemento que será la raiz de todo el arbol.                                              |
//    ----------------------------------------------------------------------------------------------------------------------------------------
//
            if(!esOperador(caracterEvaluado)){
//                ***JOptionPane.showMessageDialog(null, token.dato);
                PilaExpresiones.add(token);
            }else{
                switch(caracterEvaluado){
                    case"(":
                        PilaOperadores.add(token);
                        break;
                    case")":
                        while(!PilaOperadores.pilaIsVacia() &&!PilaOperadores.cimaPila().dato.equals("(")){
                            op2 = PilaExpresiones.eliminar();
                            op1 = PilaExpresiones.eliminar();
                            op = PilaOperadores.eliminar();
                            op = crearArbolHijo(op2, op1, op);
                            PilaExpresiones.add(op);
                        }
                        PilaOperadores.eliminar();
                        break;
                    default:
                        while(!PilaOperadores.pilaIsVacia() && prioridad(caracterEvaluado) <= prioridad(String.valueOf(PilaOperadores.cimaPila().dato.toString()))){
//                            .charAt(0)
                            op2 = PilaExpresiones.eliminar();
                            op1 = PilaExpresiones.eliminar();
                            op = PilaOperadores.eliminar();
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

    public NodoArbolBinario creacionABEPS (String cadena){
        Pila Pilax;
//        Pila PilaExpresiones;
        NodoArbolBinario token;
        NodoArbolBinario op1;
        NodoArbolBinario op2;
        NodoArbolBinario op;

        Pilax = new Pila();
//        PilaExpresiones = new Pila();
        cadena = cadena.replace(" ", "?");
        System.out.println(cadena);
        String caracterEvaluado;

//    -------------------------------------------------------------------------------------------------------------------------------
//   | Primero, se analiza en cada iteracion si el caracter seleccionado es un número, y de ser así, se comprueba si el siguiente.   |
//   | caracter es otro numero, y así hasta encontrarse con un operador. Se utiliza para saber si el número tiene más de dos dígitos,|
//   | para que se mantengan juntos y no se separen.                                                                                 |
//    -------------------------------------------------------------------------------------------------------------------------------
//
        for(int i=0; i< cadena.length(); i++){
            if(!String.valueOf(cadena.charAt(i)).equals("?")){
                caracterEvaluado = String.valueOf(cadena.charAt(i));
                if(!esOperador(String.valueOf(cadena.charAt(i))) ){
                    for (int j = i+1; j < cadena.length(); j++) {
                        if (!esOperador(String.valueOf(cadena.charAt(j)))&& !String.valueOf(cadena.charAt(j)).equals("?")){
                            caracterEvaluado += String.valueOf(cadena.charAt(j));
                            i++;
                        }else{
                            break;
                        }                            
                    }
                }

    //            caracterEvaluado = cadena.charAt(i);
                token = new NodoArbolBinario(caracterEvaluado);
//                System.out.println(caracterEvaluado);
//    ----------------------------------------------------------------------------------------------------------------------------------
//   | Si el elemento no es un operador, se guarda en la pila. Si es un operador, se le asignan los dos elementos más arriba de la pila |
//   | como hijos, y se agrega dicho elemento a la pila, ahora como la raiz de un subarbol. Al finalizar de recorrer toda la cadena, si |                                          |
//   | el tamaño de la pila es mayor a uno quiere decir que la expresion no estaba completa, y no queda unida en un solo arbol.         |                                                                            |
//    ----------------------------------------------------------------------------------------------------------------------------------
//
                if(!esOperador(caracterEvaluado)){
                    Pilax.add(token);
                }else{
                    token.hijoIzq= Pilax.eliminar();
                    token.hijoDer = Pilax.eliminar();
                    Pilax.add(token);
            }
                
            }
            
    }
        if (Pilax.tamaño() > 1) {
            JOptionPane.showMessageDialog(null, "ERROR AL RECIBIR LA NOTACION. REVISE SI ESTÁ COMPLETA E INTENTE DE NUEVO");
            return null;
        }else{
            NodoArbolBinario ola;
            ola = Pilax.cimaPila();
            return ola;
        }
    }

//      MISMO PROCEDIMIENTO QUE EL ANTERIOR, PERO CON EL ORDEN DE ASIGNACION DE LOS HIJOS CAMBIADO PARA QUE EL ARBOL NO SE INVIERTA.
//     SE USA CUANDO LA CADENA RECIBIDA DEL TXT ESTÁ EN PREORDEN.
        public NodoArbolBinario creacionABEPF (String cadena){
        Pila Pilax;
//        Pila PilaExpresiones;
        NodoArbolBinario token;
        NodoArbolBinario op1;
        NodoArbolBinario op2;
        NodoArbolBinario op;

        Pilax = new Pila();
//        PilaExpresiones = new Pila();
        cadena = cadena.replace(" ", "?");
        System.out.println(cadena);
        String caracterEvaluado;
        for(int i=0; i< cadena.length(); i++){
            if(!String.valueOf(cadena.charAt(i)).equals("?")){
                caracterEvaluado = String.valueOf(cadena.charAt(i));
                if(!esOperador(String.valueOf(cadena.charAt(i))) ){
                    for (int j = i+1; j < cadena.length(); j++) {
                        if (!esOperador(String.valueOf(cadena.charAt(j)))&& !String.valueOf(cadena.charAt(j)).equals("?")){
                            caracterEvaluado += String.valueOf(cadena.charAt(j));
                            i++;
                        }else{
                            break;
                        }                            
                    }
                }

    //            caracterEvaluado = cadena.charAt(i);
                token = new NodoArbolBinario(caracterEvaluado);
                System.out.println(caracterEvaluado);
                if(!esOperador(caracterEvaluado)){
                    Pilax.add(token);
                }else{
                    token.hijoDer= Pilax.eliminar();
                    token.hijoIzq = Pilax.eliminar();
                    Pilax.add(token);
            }
                
            }
            
    }
        if (Pilax.tamaño() > 1) {
            JOptionPane.showMessageDialog(null, "ERROR AL RECIBIR LA NOTACION. REVISE SI ESTÁ COMPLETA E INTENTE DE NUEVO");
            return null;
        }else{
            NodoArbolBinario ola;
            ola = Pilax.cimaPila();
            return ola;
        }
    }
        


        

} // final
