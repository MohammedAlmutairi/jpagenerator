/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.generator.domain.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.RelationType;
import javax.swing.JOptionPane;
import jpa.generator.domain.main.DB;
import jpa.generator.domain.main.EntityInfo;
import jpa.generator.domain.main.SingleRelation;

/**
 *
 * @author Mohammed
 */
public class TemplateManager {

    Configuration cfg;
    Template template;

    public TemplateManager() {
        try {
            cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/TEMPLATES");
        } catch (Exception ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillEntity(DB db) {

        
       
       
        List<SingleRelation> relations = db.createSingleRealtion(); 
        
        Function<String, Consumer<EntityInfo>> templateOf = s -> e -> e.addContent(stringOf(s, e));

        db.getEntities().forEach(templateOf.apply("entity.ftl"));

      

        relations.forEach(sr -> fillEntityRelation(sr));
        
        
        
        db.getEntities().forEach(templateOf.apply("entity_last.ftl"));

    }

    public void fillEntityRelation(SingleRelation sr) {
        if (sr.getRelationType().equals("ManyToOne")) {
             String manyToOne = stringOfRelation("manytoone.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             String manyToOne_map = stringOfRelation("manytoone_map.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             sr.getLeftEntity().addContent(manyToOne);
             sr.getRigtEntityEntity().addContent(manyToOne_map);
        }
        
        else if (sr.getRelationType().equals("OneToOne")) {
             String oneToOne = stringOfRelation("onetoone.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             String oneToOne_map = stringOfRelation("onetoone_map.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             sr.getLeftEntity().addContent(oneToOne);
             sr.getRigtEntityEntity().addContent(oneToOne_map);
        }
        
        else if (sr.getRelationType().equals("OneToMany")) {
            String manyToOne = stringOfRelation("manytoone.ftl", sr.getRigtEntityEntity(), sr.getLeftEntity());
            String manyToOne_map = stringOfRelation("manytoone_map.ftl", sr.getRigtEntityEntity(), sr.getLeftEntity());
            sr.getRigtEntityEntity().addContent(manyToOne);
            sr.getLeftEntity().addContent(manyToOne_map);
        }
       else if (sr.getRelationType().equals("ManyToMany")) {

           String manyToOne = stringOfRelation("manytomany.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             String manyToOne_map = stringOfRelation("manytomany_map.ftl", sr.getLeftEntity(), sr.getRigtEntityEntity());
             sr.getLeftEntity().addContent(manyToOne);
             sr.getRigtEntityEntity().addContent(manyToOne_map);

           
       } 

    }

    private String stringOf(String templateName, EntityInfo entityInfo) {
        try {
            template = cfg.getTemplate(templateName);
            Map<String, Object> data = new HashMap<>();
            data.put("entity", entityInfo);
            data.put("importList", entityInfo.getImportSet());
            
            Writer out = new StringWriter();

            template.process(data, out);

            String ret = out.toString();
        
            return ret;

        } catch (IOException | TemplateException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private String stringOfRelation(String templateName, EntityInfo l, EntityInfo r) {
        try {
            template = cfg.getTemplate(templateName);
            Map<String, Object> data = new HashMap<>();
            data.put("rightEntity", r);
            data.put("leftEntity", l);
            Writer out = new StringWriter();

            template.process(data, out);

            return out.toString();

        } catch (IOException | TemplateException ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
