package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;

public interface BlueprintFilters {
	
	public Blueprint filter(Blueprint bp) throws BlueprintNotFoundException, Exception;
}