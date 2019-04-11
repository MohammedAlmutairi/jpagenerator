/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jpa.generator.domain.main.DB;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Mohammed
 */
public class EntityProcessTest {
    
    public EntityProcessTest() {
    }
    
    @Test
    public void testProcessEntity()
    {
        String line = "School   :1:m:Stage";
        String line2 = "ABC:m:m:School";
       
        DB db = new DB();
        
        db.ProcessLine(line);   
        db.ProcessLine(line2);
        
        assertEquals(3, db.getEntities().size());
        
        
                
    }
    
}
