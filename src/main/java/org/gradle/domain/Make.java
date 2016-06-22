package org.gradle.domain;

import java.util.List;

/**
 * Make entity
 * 
 * @author Artsiom_Buyevich
 *
 */
public class Make extends NamedEntity {

	private List<Model> models;

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

}
