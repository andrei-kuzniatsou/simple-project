package org.gradle.domain;

/**
 * Named entity.
 * @author Artsiom_Buyevich
 */
public class NamedEntity extends Entity {

    /**
     * name of entity.
     */
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
