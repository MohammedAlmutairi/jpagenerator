/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.infastructure.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
 

/**
 *
 * @author Mohammed
 */
public class ReadFileService {
    
    public List<String> readFile(File f)
    {
           
        try {
            return Files.readAllLines(Paths.get(f.toURI()));
        } catch (IOException ex) {
          return new ArrayList<>();
        }
        
    }
    
}
