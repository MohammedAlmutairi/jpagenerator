/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.infastructure.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
 
import jpa.generator.domain.main.DB;
import jpa.generator.domain.main.EntityInfo;
import jpa.generator.domain.main.IDBRepository;
import jpa.generator.domain.template.TemplateManager;

/**
 *
 * @author Mohammed
 */
public class DBRepository implements IDBRepository {

    @Override
    public void write(DB db) {
        
         final String src = db.getOutPutDirectory();
        
         
        Consumer<EntityInfo> consumer = (e) -> {  
             try (BufferedWriter br = new BufferedWriter(new FileWriter(FileUtil.getFile(e.getFilePath(src))))) {
             
                 br.write(e.getContent().toString());
             } catch (Exception ex) {
                ex.printStackTrace();
             }
        };
        
        db.getEntities().stream().forEach(consumer);
        
        

        
    
    }
    public static void main(String[] args) {
    
        
        
        
        DB d = new DB();
        d.ProcessLine("Employee:m:m:Project -> a");
        
        TemplateManager  tm = new TemplateManager();
        tm.fillEntity(d);
        
        new DBRepository().write(d);
    }
    
}
