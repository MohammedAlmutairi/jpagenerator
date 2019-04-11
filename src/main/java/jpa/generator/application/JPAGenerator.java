/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.application;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import jpa.generator.domain.main.DB;
import jpa.generator.domain.template.TemplateManager;
import jpa.generator.infastructure.repository.DBRepository;
import jpa.generator.infastructure.services.ReadFileService;

/**
 *
 * @author Mohammed
 */
public class JPAGenerator {
    
    DB db = new DB();
    DBRepository dBRepository = new DBRepository();
    ReadFileService readFileService = new ReadFileService();
    
    
    TemplateManager  tm = new TemplateManager();
    
    public void generateJPA()
    {
        JFileChooser fileChooser = new JFileChooser(".");
        int choose = fileChooser.showOpenDialog(null);
        if (choose == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            
            List<String> lines = readFileService.readFile(file);
            
            db.ProcessLines(lines);
            
            tm.fillEntity(db);
            
            dBRepository.write(db);
            
        }
        
    }
    
     
    public void generateJPA(File file) {
        List<String> lines = readFileService.readFile(file);
        db.ProcessLines(lines);
        tm.fillEntity(db);
        dBRepository.write(db);

    }
    public static void main(String[] args) {
        
        new JPAGenerator().generateJPA();
    }
            
    
}
