/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jpa.generator.domain.JavaName;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Mohammed
 */
public class TestGeneralOperation {
    
    public TestGeneralOperation() {
    }
    
    @Test
    public void testJavaName()
    {
        JavaName n1 = JavaName.of("Mohammed");
        JavaName n2 = JavaName.of("M");
        assertEquals("mohammed", n1.getVariable());
        assertEquals("m", n2.getVariable());
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void nullNameMustBeException()
    {
        JavaName.of(null);
    
    }
     
    public void emptyNameMustBeException()
    {
        JavaName.of("");
    
    }
}
