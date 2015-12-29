package com.sneaky.peaky.dataparser.jpa.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

/**
 *
 * @author Roel
 */
public abstract class AbstractJPAObject implements Serializable{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public String toJSON() {
        return GSON.toJson(this);
    }
}
