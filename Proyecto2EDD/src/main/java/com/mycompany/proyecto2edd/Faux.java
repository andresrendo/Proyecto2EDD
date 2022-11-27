package com.mycompany.proyecto2edd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
/**
 *
 * @author andre
 */
public class Faux {
    
    public String leer_txt(String p){
        String datos = "";
        String linea;
        String datos_txt = "";
        String path = p;
        File file = new File(path);
        try{
            if (!file.exists()){
                file.createNewFile();
            }else{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while((linea = br.readLine())!= null){
                    if(!linea.isEmpty()){
                        datos_txt += linea + "\n";
                    }
                }
                if (!"".equals(datos_txt)){
                    datos = datos_txt;
                    

                }
                br.close();
                JOptionPane.showMessageDialog(null, "Lectura Exitosa");
            }
        }catch( Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        datos = datos.replace("\n", "");
        datos = datos.replace(" ", "");
        return datos;
    }
}
