/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain.main;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mohammed
 */

public class EntityRelation {
    
     public enum SideRelation
    {
        One,Many
    };
    
     @Setter @Getter private  List<EntityInfo> leftEntities;
     @Setter @Getter private  List<EntityInfo> rightEntities; 
     @Setter @Getter private  SideRelation leftRelation;
     @Setter @Getter private SideRelation rightRelation;

    public EntityRelation(List<EntityInfo> leftEntities,SideRelation leftRelation , SideRelation rightRelation,List<EntityInfo> rightEntities) {
        this.leftEntities = leftEntities;
        this.rightEntities = rightEntities;
        this.leftRelation = leftRelation;
        this.rightRelation = rightRelation;
    }
    
    public List<SingleRelation> findSingleRelations()
    {
       return  leftEntities.stream()
                    .flatMap(e1 -> rightEntities.stream()
                            .map(e2 -> new SingleRelation(e1,e2,leftRelation,rightRelation))
                    ).collect(Collectors.toList());
                
                     
     
    }
    
     
     
    
}
