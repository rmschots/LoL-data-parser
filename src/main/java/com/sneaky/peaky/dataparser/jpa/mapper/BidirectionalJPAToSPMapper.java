package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;

/**
 *
 * @author Roel
 * @param <A>
 * @param <B>
 */
public interface BidirectionalJPAToSPMapper<A extends AbstractJPAObject,B extends AbstractSPObject>{
    abstract A mapToJPA(B spObject);
    abstract B mapToDomain(A jpaObject);
}
