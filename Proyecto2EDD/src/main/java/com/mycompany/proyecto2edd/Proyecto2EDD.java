/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyecto2edd;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.mycompany.proyecto2edd.folder.ArbolGrafico;
/**
 *
 * @author mannith
 */
public class Proyecto2EDD {

    public static void main(String[] args) {
//        new ArbolBinarioExpresionesVentana().setVisible(true);
          new Interfaz().setVisible(true);
          SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame frame = new ArbolGrafico();
                frame.setSize(488,488);
                frame.setVisible(true);
            }
          
          });
    }
}
