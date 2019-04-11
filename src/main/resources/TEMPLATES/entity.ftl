
<#if entity.packageName??>package</#if> ${entity.packageName};

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
<#list importList as i>
${i}
</#list>

/**
 *
 * @author Mohammed
 */
@Entity
@Table(name = "${entity.tableName}")
public class ${entity.name} implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter private Long id;
  
    @Column(length = 200,nullable = false)
    @Setter @Getter private String name; 
    
 

