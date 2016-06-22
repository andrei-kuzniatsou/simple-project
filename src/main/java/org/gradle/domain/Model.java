package org.gradle.domain;

import java.util.Date;

/**
 * Model entity
 * 
 * @author Artsiom_Buyevich
 *
 */
public class Model extends NamedEntity {

	private Date startDate;

	private Date endDate;

	private Make make;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

}
