package com.sneaky.peaky.dataparser.jpa.pojo.staticdata;

import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAParticipant;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "rune")
public class JPARune extends AbstractJPAObject {
    @Id
    private Integer id;
    private String name;
    private String description;
    @Transient
    @ManyToMany(mappedBy = "runes")
    private List<JPAParticipant> participants;
    
    public JPARune(){
    }

}