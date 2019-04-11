/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mohammed
 */
public class JavaName {
    
    @Setter @Getter private String name;

    private JavaName(String name) {
        this.name = name;
    }
    
    public static JavaName of(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalStateException("pls check name");
        }
        return new JavaName(name.trim());
        

    }
    
    public String getVariable()
    {
        if (name.length() == 1)
        {
            return name.toLowerCase();
        }
        StringBuilder sb = new StringBuilder(name.substring(0, 1).toLowerCase());
        sb.append(name.substring(1));
        
        return sb.toString();
        
    }
            
    
    
    
}
