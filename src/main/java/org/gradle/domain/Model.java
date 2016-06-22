package org.gradle.domain;

import java.util.Date;

/**
 * Model entity.
 * @author Artsiom_Buyevich
 */
public class Model extends NamedEntity {

    /**
     * start date produce of model.
     */
    private Date startDate;

    /**
     * end date produce of model.
     */
    private Date endDate;

    /**
     * make entity.
     */
    private Make make;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the make
     */
    public Make getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(Make make) {
        this.make = make;
    }

}
