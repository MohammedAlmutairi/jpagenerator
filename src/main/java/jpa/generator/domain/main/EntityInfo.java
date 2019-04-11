/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain.main;

import java.util.LinkedHashSet;
import java.util.Objects;
import jpa.generator.domain.JavaName;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
/**
 *
 * @author Mohammed
 */
public class EntityInfo {
    
     private JavaName name; 
     @Setter @Getter private  String packageName; 
     @Setter @Getter private  StringBuilder content;
     @Setter @Getter private Set<String> importSet; 
     @Setter @Getter private String tableName; 
  

    public EntityInfo(JavaName name, String packageName) {
        this.name = name;
        this.packageName = packageName;
        this.content = new StringBuilder();
        this.importSet = new LinkedHashSet<>();
        
        fillTableName();
    }
    
    public String getVariableName() { return name.getVariable(); }
    public String getName()  { return name.getName(); }
    
    
    public String getFilePath(String src)
    {
        StringBuilder sb = new StringBuilder(src);
        
        if (!packageName.trim().equals(""))
        {
            
            sb.append("\\").append(packageName.replace(".", "\\\\"));
        }
        sb.append("\\").append(getName()).append(".java");
        
        return sb.toString();
    }
            
            
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntityInfo other = (EntityInfo) obj;
        if (!Objects.equals(this.name.getName(), other.name.getName())) {
            return false;
        }
        return true;
    }
    
    
    public void addContent(String c)
    {
        content.append("\n");
        content.append(c);
    }
     
    public void addToImportSet(String str)
    {
        importSet.add(str);
    }
    
    public void addToImportSet(EntityInfo entityInfo)
    {
        StringBuilder sb = new StringBuilder("import ");
        sb.append(getNameWithPackage()).append(";");
        importSet.add(sb.toString());
    }
    
    public String getNameWithPackage()
    {
        StringBuilder sb = new StringBuilder(packageName);
        sb.append(".").append(getName());
        return  sb.toString();
    }

    private void fillTableName() {
    
        String n = getName();
        StringBuilder sb = new StringBuilder("");
        
        for (int index = 0;index < n.length();index++)
        {
            String c = n.charAt(index)+"";
            
            if (c.matches("[A-Z]") && index > 0)
            {
                sb.append("_");
            }
            sb.append(c);
           
        }
        
        setTableName(sb.toString());
        
        
    
    }
  
  
    
    
   
    
}
