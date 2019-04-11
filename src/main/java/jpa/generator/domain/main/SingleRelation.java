/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain.main;

import jpa.generator.domain.main.EntityRelation.SideRelation;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mohammed
 */
public class SingleRelation {

    enum RelationType {

        OneToOne, OneToMany, ManyToOne, ManyToMany
    };

    @Setter
    @Getter
    private EntityInfo leftEntity;
    @Setter
    @Getter
    private EntityInfo rigtEntityEntity;
    @Setter
    @Getter
    private String relationType;

    public SingleRelation(EntityInfo leftEntity, EntityInfo rigtEntityEntity, SideRelation leftSide, SideRelation rightSide) {
        this.leftEntity = leftEntity;
        this.rigtEntityEntity = rigtEntityEntity;

        leftEntity.addToImportSet(rigtEntityEntity);
        rigtEntityEntity.addToImportSet(leftEntity);

        if (leftSide == SideRelation.One && rightSide == SideRelation.One) {

            relationType = "OneToOne";
            leftEntity.addToImportSet(getImortForRelation(relationType));
            rigtEntityEntity.addToImportSet(getImortForRelation(relationType));

        } else if (leftSide == SideRelation.One && rightSide == SideRelation.Many) {

            relationType = "OneToMany";
            leftEntity.addToImportSet(getImortForRelation(relationType));
            rigtEntityEntity.addToImportSet(getImortForRelation("ManyToOne"));
            leftEntity.addToImportSet("import java.util.List;");
            

        } else if (leftSide == SideRelation.Many && rightSide == SideRelation.One) {
            relationType = "ManyToOne";
            leftEntity.addToImportSet(getImortForRelation(relationType));
            rigtEntityEntity.addToImportSet(getImortForRelation("OneToMany"));
            rigtEntityEntity.addToImportSet("import java.util.List;");
        } else if (leftSide == SideRelation.Many && rightSide == SideRelation.Many) {
            relationType = "ManyToMany";
            
            leftEntity.addToImportSet(getImortForRelation(relationType));
            rigtEntityEntity.addToImportSet(getImortForRelation(relationType));
            leftEntity.addToImportSet("import java.util.List;");
            leftEntity.addToImportSet("import javax.persistence.JoinColumn;");
            leftEntity.addToImportSet("import javax.persistence.JoinTable;");
            
            
            

            rigtEntityEntity.addToImportSet("import java.util.List;");

        }
    }

    public String getImortForRelation(String relation) {
        StringBuilder sb = new StringBuilder("import ");
        sb.append("javax.persistence.").append(relation).append(";");
        return sb.toString();

    }

}
