/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.application;

import java.io.File;

/**
 *
 * @author Mohammed Almutriai
 */
public class GameGenerateTable { 
   public static void main(String[] args) {
       int x = 0;
        File file = new File("D:\\JPAGenerator\\src\\main\\resources\\example.txt");
         new JPAGenerator().generateJPA(file);
    }
    
}
