package org.gradle.domain;

import java.util.List;

/**
 * Make entity.
 * @author Artsiom_Buyevich
 */
public class Make extends NamedEntity {

    /**
     * list of models.
     */
    private List<Model> models;

    /**
     * @return the models
     */
    public List<Model> getModels() {
        return models;
    }

    /**
     * @param models the models to set
     */
    public void setModels(List<Model> models) {
        this.models = models;
    }



}
