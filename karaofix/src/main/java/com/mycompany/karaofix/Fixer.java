/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.karaofix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Nuria Romero (anromercas)
 */

public class Fixer {
	
	public static int  EntraIncial=0; 
	// método recursivo para copiar archivos y carpetas dentro de una ruta
	public static void Copiar(File fOrigen,File fDestino){
	        //si el origen no es una carpeta
	        if(!fOrigen.isDirectory()){
	            //Llamo la funcion que lo copia
	            CopiarFichero(fOrigen,fDestino);
	        }else{
	           EntraIncial++; 
	           if(EntraIncial==1){
	                //Cambio la ruta destino por el nombre que tenia mas el nombre de la carpeta
	                fDestino=new File(fDestino.getAbsolutePath()+"\\"+fOrigen.getName()); 
	                //si la carpeta no existe la creo
	                if(!fDestino.exists()){
	                    fDestino.mkdir();
	                }
	           } 
	           //obtengo el nombre de todos los archivos y carpetas que pertenecen a este fichero(FOrigen)
	           String []rutas=fOrigen.list();
	           //recorro uno a uno el contenido de la carpeta
	             for(int i=0;i<rutas.length;i++){
	              //establezco el nombre del nuevo archivo origen 
	              File FnueOri=new File(fOrigen.getAbsolutePath()+"\\"+rutas[i]);
	              //establezco el nombre del nuevo archivo destino 
	              File FnueDest= new File(fDestino.getAbsolutePath()+"\\"+rutas[i]);
	              //si no existe el archivo destino lo creo
	              if(FnueOri.isDirectory() && !FnueDest.exists()){
	                  FnueDest.mkdir();                        
	              }
	              //uso recursividad y llamo a esta misma funcion hasta llegar al ultimo elemento  
	              Copiar(FnueOri,FnueDest); 
	           }
	        }
	        
	} 
	  
	public static void CopiarFichero(File fOrigen,File fDestino){
		
		String linea = "";
	            try {
	            //Si el archivo a copiar existe
	            if(fOrigen.exists()){
	                String copiar="S";
	                //si el fichero destino ya existe
	                if(fDestino.exists()){
	                   System.out.println("El fichero " + fDestino.getName() + " ya existe, Desea Sobre Escribir:S/N ");
	                   copiar = ( new BufferedReader(new InputStreamReader(System.in))).readLine();
	                }
	                //si puedo copiar
	                if(copiar.toUpperCase().equals("S")){
	                    //Flujo de lectura al fichero origen(que se va a copiar)            
	                    FileInputStream leeOrigen= new FileInputStream(fOrigen);
	                    //Flujo de lectura al fichero destino(donde se va a copiar)
	                    OutputStream salida = new FileOutputStream(fDestino);
	                    //separo un buffer de 1MB de lectura
	                    byte[] buffer = new byte[1024];
	                    int tamaño;
	                    //leo el fichero a copiar cada 1MB
	                    while ((tamaño = leeOrigen.read(buffer)) > 0) {
	                    //Escribe el MB en el fichero destino
	                    salida.write(buffer, 0, tamaño);
	                    }
	                    System.out.println(fOrigen.getName()+" Copiado con Exito!!");
	                    salida.close();
	                    leeOrigen.close();
	                }
	            	}else{               
	                System.out.println("El fichero a copiar no existe..."+fOrigen.getAbsolutePath());
	            	}
	                Path archivoCopiado=Paths.get(fDestino.getAbsolutePath());
	                
	                if (fDestino.getName().contains(".txt")) {
						Charset charset = Charset.forName("ISO-8859-15");
						try (BufferedReader reader = Files.newBufferedReader(fDestino.toPath(), charset);
								PrintWriter writer = new PrintWriter(Files.newBufferedWriter(archivoCopiado, charset));) {
							while ((linea = reader.readLine()) != null) {
								if (linea.contains("#BPM") || linea.contains("#GAP")) {
									linea = linea.substring(0, linea.indexOf(':') + 1) + String.valueOf(
											Math.round(Double.parseDouble(linea.replaceAll(",", ".").substring(5))));
								}
								writer.println(linea);
							}
							System.out.println();
							System.out.println("A el fichero " + fDestino.getName() + " Se le ha modificado el atributo #BPM y #GAP ");
							System.out.println();
						} catch (IOException e) {
							e.printStackTrace();
						}
	            
	            }
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        
	       }
	    
	    
	}
	
	
    public static void main(String[] args) {
    	
    	
    	//Fichero desde el cual se van a copiar los datos
        File FOrigen=new File(".\\src\\main\\resources\\temazos");
        //Fichero donde se van a copiar los datos
        File FDestino=new File(".\\src\\main\\resources\\grandesexistos");
        Copiar(FOrigen,FDestino);
        EntraIncial=0;   
        System.out.println();
        System.out.println("Gracias por usar el sistema de copiado de archivos/Directorios");
    	
    }
}
