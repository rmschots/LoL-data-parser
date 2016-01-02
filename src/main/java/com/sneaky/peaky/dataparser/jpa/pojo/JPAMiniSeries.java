package com.sneaky.peaky.dataparser.jpa.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 *
 * @author Roel
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "mini_series")
public class JPAMiniSeries extends AbstractJPAObject {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "miniSeries")
    private JPARanking ranking;
    private int wins;
    private int losses;
    private String progress;
    private int target;
    private long timeLeftToPlayMillis;

    public JPAMiniSeries() {
    }
    
}
