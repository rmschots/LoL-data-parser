package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
import java.util.Collection;

/**
 *
 * @author Roel Mangelschots
 * @param <A>
 * @param <B>
 */
public interface ListRestMapper<A extends Collection<? extends AbstractSPObject>, B extends Collection> {
    abstract A mapToSP(B restObject);
}
