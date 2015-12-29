/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;

/**
 *
 * @author Roel
 * @param <A>
 * @param <B>
 */
public interface RestMapper<A extends AbstractSPObject, B> {
    abstract A mapToSP(B restObject);
}
