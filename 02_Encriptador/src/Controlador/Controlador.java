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
public class Controlador {
    public String path;
    public String path2;

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }
    public String llave;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }
    
    public void encriptador() throws IOException, FileNotFoundException, ClassNotFoundException{
        Encriptador encriptador = new Encriptador();
        encriptador.EncriptarArchivo(getPath(), getLlave());
    }
    
    
    public void desencriptar() throws IOException, FileNotFoundException, ClassNotFoundException{
        Encriptador encriptador = new Encriptador();        
        encriptador.DesencriptarArchivo(getPath2(), getLlave());
    }
}
