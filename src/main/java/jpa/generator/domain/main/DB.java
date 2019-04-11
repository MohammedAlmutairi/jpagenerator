/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain.main;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import jpa.generator.domain.JavaName;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
/**
 *
 * @author Mohammed
 */
public class DB {

     @Setter @Getter private Set<EntityInfo> entities; 
     @Setter @Getter private  List<EntityRelation> relations;
      

     @Setter @Getter private String mainPackage = "";
     @Setter @Getter private String outPutDirectory = "C:\\Users\\Mohammed\\Documents\\NetBeansProjects\\jpamodeler\\src\\main\\java";
     public DB()
     {
         entities = new LinkedHashSet<>();
         relations = new ArrayList<>();
     }
     
     public void addCmd(String cmd)
     {
         String[] c = cmd.split("=");
         
         if (c.length != 2)
         {
            JOptionPane.showMessageDialog(null, "Error in "+ cmd,"Error",JOptionPane.ERROR_MESSAGE);
             throw new IllegalStateException();
         }
         switch(c[0].toLowerCase())
         {
             case "src":
                 setOutPutDirectory(c[1]);
                 break;
             case "package":
                 setMainPackage(c[1]);
                 break;
                     
         }
     }
     
      public void ProcessLines(List<String> lines)
      {
          lines.stream()
                  .filter(line -> !line.trim().equals(""))
                  .forEach(line -> ProcessLine(line));
          
          
      }
     
     
      
      public void ProcessLine(String line)
     {
         
         if (line.startsWith("//"))
         {
             return;
         }
         
         if (line.startsWith("#"))
         {
             addCmd(line.substring(1));
             return;
         }
         
         String[] packageSpliting = line.split("->");
         String packageName = mainPackage;
         String[] r ; 
         if (packageSpliting.length == 2)
         {
             packageName += "." + packageSpliting[1].trim();
             r = packageSpliting[0].trim().split(":"); 
         }
         else
         {
            r = line.trim().split(":");
         }
         
         if (r.length != 4)
         {
                 JOptionPane.showMessageDialog(null, "Error in "+ line,"Error",JOptionPane.ERROR_MESSAGE);
                 throw new IllegalStateException();
         
         }
         
         
         EntityRelation relation = new EntityRelation(
                 addNewEntity(r[0], packageName), 
                 (r[1].equals("1") ? EntityRelation.SideRelation.One  : EntityRelation.SideRelation.Many ), 
                 (r[2].equals("1") ? EntityRelation.SideRelation.One  : EntityRelation.SideRelation.Many ), 
                 addNewEntity(r[3], packageName)
         );
         
         
         relations.add(relation);
         
     }
     
   
     
     public List<EntityInfo> addNewEntity(String list,String packageName)
     {
         List<EntityInfo> ret = new java.util.ArrayList<>();
         
         Consumer<String> cosumer = s -> {
             EntityInfo n = findOrCreateEntity(s, packageName);
             ret.add(n);
         };
         Stream.of(list.trim().split("\\s+")).forEach(cosumer);
       
         
         return ret;
     }
     
     public EntityInfo findOrCreateEntity(String name,String packageName)
     {
      
         EntityInfo n = entities.stream().
                 filter(e -> e.getName().equals(name)).
                 findAny().
                 map(e -> e).
                 orElse(new EntityInfo(JavaName.of(name), packageName));
         
         
         entities.add(n);
         return n;
     }
     
     public List<SingleRelation> createSingleRealtion()
     {
         List<SingleRelation> ret = new ArrayList<>();
         for (EntityRelation r:relations)
         {
             ret.addAll(r.findSingleRelations());
         }
        return ret;
     }
     
    
}
