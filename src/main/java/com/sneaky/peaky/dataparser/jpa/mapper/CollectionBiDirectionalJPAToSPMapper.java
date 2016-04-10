/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import java.util.Collection;

/**
 *
 * @author Roel
 * @param <A>
 * @param <B>
 */
public interface ListBiDirectionalJPAToSPMapper <A extends Collection<? extends AbstractJPAObject>, B extends Collection<? extends AbstractSPObject>>{
    abstract A mapToJPA(B spCollection);
    abstract B mapToSP(A jpaCollection);
}
