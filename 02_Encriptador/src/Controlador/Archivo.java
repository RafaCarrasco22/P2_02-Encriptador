/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class Archivo {
    
    public String leerArchivo(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        File file = new File(path);
        FileReader reader = new FileReader(file);
        String txt = "";
        int caracter = 0;
        char c;
        
        while(caracter!=-1){
            caracter = reader.read();
            c = (char) caracter;
            if(caracter!=-1){
                txt = txt.concat(String.valueOf(c));
            }
        }
        return txt;
    }
    
    public File escribirArchivo(String path, String texto) throws FileNotFoundException, IOException{
        File file = new File(path);
        try ( FileWriter writer = new FileWriter(file); ) {
            writer.append(texto);
        }

        return file;    
    }
   

    public void encriptarArchivo(String path,String key) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        char[] clave = key.toCharArray();
        File file = new File(path);
        String contenido = this.leerArchivo(file.getAbsolutePath());
        char[] cadena = contenido.toCharArray();
        String texto = "";
        int valor;
        int suma;
        char caracter;
                
        int contadorKey = 0;
        for(int i=0; i<cadena.length; i++){
            valor = (int) cadena[i];
            
            suma = valor + clave[contadorKey];
            if(suma<255){
                caracter = (char) suma;
            }else{
                caracter = (char) (suma-255);
            }
            
            texto = texto.concat(String.valueOf(caracter));
            
            contadorKey++;
            if(! (contadorKey<key.length())){
                contadorKey = 0;
            }
        }
                    
        String nP = this.crearDirectorio(path, "Encriptado");
        this.escribirArchivo(nP, texto);
        
        
    }
        
    public String crearDirectorio(String path, String dirName){
       String keys = "\\\\";
       String[] tokens = path.split(keys);
       
       String pathname = "";
       for(int i=0; i<tokens.length-1;i++){
           pathname = pathname.concat(tokens[i]);
           pathname = pathname.concat("\\");
       }
               
       String directory = pathname + dirName;
       new File(directory).mkdirs();
       return directory+"\\"+tokens[tokens.length-1];
    }
    
    public void desencriptarArchivo(String path,String key) throws FileNotFoundException, IOException, ClassNotFoundException{        
        char[] clave = key.toCharArray();
        File file = new File(path);
        String contenido = this.leerArchivo(file.getAbsolutePath());
        char[] cadena = contenido.toCharArray();    
        String texto = "";
        int valor;
        int suma;
        char caracter;
                
        int contadorKey = 0;
        for(int i=0; i<cadena.length; i++){
            valor = (int) cadena[i]; 
            suma = valor - clave[contadorKey];
            
            if(suma<0){
                caracter = (char) (suma+255);
            }else{
                caracter = (char) suma;
            }
            
            texto = texto.concat(String.valueOf(caracter));
            contadorKey++;
            
            if(! (contadorKey<key.length())){
                contadorKey = 0;
            }
        }
                    
        String nP = this.crearDirectorio(path, "Desencriptado");
        this.escribirArchivo(nP, texto);
    }
}