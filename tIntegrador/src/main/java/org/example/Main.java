package org.example;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import modelo.ArchivoServiciosContratados;
import modelo.LectorArchivos;
import modelo.Partidos;
import modelo.Servicio;
import modelo.Suscripcion;

public class Main {
    public static void main(String[] args) {
        //En la variable args va a viajar la ruta del archivo que queremos que abra el programa
        if(args.length == 0){
            System.out.println("ERROR: No ingresaste ningún archivo como argumento!");
            System.exit(88);
        }
        
        
        List <Partidos> listaDePartidos;
        List <Pronosticos> listaDePronosticos;
        
        try {
        	
        	listaDePartidos = New CsvToBeanBuilder(new FileReader(args[0]))
        			.withType(Resultados.class)
        			.build()
        			.parse();	
        	
        	
        	listaDePronosticos = New CsvToBeanBuilder(new FileReader(args[1]))
        			.withType(Pronosticos.class)
        			.build()
        			.parse();	
        	
        	
        }

        

    

