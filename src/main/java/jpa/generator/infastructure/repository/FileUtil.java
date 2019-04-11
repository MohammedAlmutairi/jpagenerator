/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.infastructure.repository;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class FileUtil {
    
    
    public static File getFile(String path)
    {
        File file = new File(path);
        
        File dir = file.getParentFile();
        
        
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        
        if (!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        
        
        return file;
    }
}
