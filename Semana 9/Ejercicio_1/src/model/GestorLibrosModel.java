package com.gestorlibros.model;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.coti.tools.Rutas;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GestorLibrosModel {

    ArrayList<Libro> libros;

    // Path to export and import data
    Path pathToXML = Rutas.pathToFileOnDesktop("libros.xml");
    Path pathToJSON = Rutas.pathToFileOnDesktop("libros.json");
    Path pathToCSV = Rutas.pathToFileOnDesktop("libros.csv");
    Path pathToBinary = Rutas.pathToFileOnDesktop("libros.bin");

    public GestorLibrosModel() {
        libros = new ArrayList<Libro>();
    }



    ///////////////////////////////////////////////////////////////////
    // CRUD OPERATIONS ------------------------------------------------
    public void addLibro(Libro l) {
        libros.add(l);
    }

    public void removeLibro(Libro l) {
        libros.remove(l);
    }

    public void updateLibro(Libro l) {
        libros.set(libros.indexOf(l), l);
    }

    public ArrayList<Libro> getLibros() {
        // Devolvemos copia para no modificar el original desde la vista.
        return new ArrayList<Libro>(this.libros);
    }



    ////////////////////////////////////////////////////////////////////
    // Export and Import data ------------------------------------------
    public int importBooksFromCSV() {
        try {
        if (!Files.exists(pathToCSV)) return 0;

        java.util.List<String> lineas = Files.readAllLines(pathToCSV, StandardCharsets.UTF_8);
        int i = 0;
        libros.clear();

        for (String linea : lineas) {
            Libro l = Libro.fromDelimitedString(linea, ";");
            if (l != null) {
                libros.add(l);
                i++;
            }
        }
            return i;

        } catch (IOException e) {
            System.err.println("[ ERROR : importBooksToCSV() ] " + e.getMessage());
            return -1;
        }
    }

    public int exportBooksToCSV() {
        try {
        ArrayList<String> lineas = new ArrayList<>();
        
        for (Libro l : libros) {
            lineas.add(l.asDelimitedString(";")); 
        }
        
        Files.write(pathToCSV, lineas, StandardCharsets.UTF_8);
        return libros.size();

        } catch (IOException e) {
            System.err.println("[ ERROR : exportarBooksToCSV() ] " + e.getMessage());
            return -1;
        }
    }

    public int importBooksFromJSON() {
        try {
        if (!Files.exists(pathToJSON)) return 0;

        Gson gson = new Gson();
        String json = Files.readString(pathToJSON, StandardCharsets.UTF_8);
        
        Type listaLibrosType = new TypeToken<ArrayList<Libro>>(){}.getType();
        ArrayList<Libro> librosImportados = gson.fromJson(json, listaLibrosType);
        if (librosImportados != null) {
            libros = librosImportados;
            return libros.size();
        }
        return 0;

        } catch (IOException e) {
            System.err.println("[ ERROR : importBooksFromJSON() ] " + e.getMessage());
            return -1;
        }
    }

    public int exportBooksToJSON() {
        try {
        Gson gson = new Gson();
        String json = gson.toJson(libros); 
        
        Files.writeString(pathToJSON, json, StandardCharsets.UTF_8);
        return libros.size();

        } catch (IOException e) {
            System.err.println("[ ERROR : exportBooksToJSON() ] " + e.getMessage());
            return -1;
        }
    }

    public int importBooksFromXML() {
        try {
        if (!Files.exists(pathToXML)) return 0;
        
        XmlMapper xmlMapper = new XmlMapper();
        ArrayList<Libro> librosImportados = xmlMapper.readValue(
            pathToXML.toFile(), 
            new com.fasterxml.jackson.core.type.TypeReference<ArrayList<Libro>>() {}
        );
        this.libros = librosImportados;
        return libros.size();

        } catch (IOException e) {
            System.err.println("[ ERROR : importBooksFromXML() ] " + e.getMessage());
            return -1;
        }
    }

    public int exportBooksToXML() {
        try {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(pathToXML.toFile(), libros);
        return libros.size();

        } catch (IOException e) {
            System.err.println("[ ERROR : exportBooksFromXML() ] " + e.getMessage());
            return -1;
        }
    }

    // Serialization and Deserialization ------------------------------------------
    public void saveStateOfTheApp(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(pathToBinary.toFile()))) {
        objectOutputStream.writeObject(libros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStateOfTheApp(){
        
        if (!Files.exists(pathToBinary)) return;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathToBinary.toFile()))) {
            libros = (ArrayList<Libro>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
