/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class Encriptador {
    public void EncriptarArchivo(String path, String key) throws IOException, FileNotFoundException, ClassNotFoundException {
        Archivo archivo = new Archivo();
        archivo.encriptarArchivo(path, key);
      
        
    }
    
    public void DesencriptarArchivo(String path, String key) throws IOException, FileNotFoundException, ClassNotFoundException{
        Archivo archivo = new Archivo();
        archivo.desencriptarArchivo(path, key);
    }
}
