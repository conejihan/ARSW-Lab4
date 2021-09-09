package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.controllers.ResourceNotFoundException;
import edu.eci.arsw.blueprints.filters.BlueprintFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;

@Service
public class secuenceFilter implements BlueprintFilters{

	@Override
	public Blueprint filter(Blueprint bp) throws Exception{
		try {
			List<Point> originalP = bp.getPoints();
			
			List<Point> newP = new ArrayList<Point>();
			for(int i = 0; i < originalP.size(); i++) {
				if(i % 2 == 0) {
					newP.add(originalP.get(i));
				}
			}
			Blueprint filteredbp = new Blueprint(bp.getAuthor(), bp.getName(), newP);
			return filteredbp;
		}catch(Exception e) {
			throw new Exception("Not Found");
		}
		
		
	}

}
