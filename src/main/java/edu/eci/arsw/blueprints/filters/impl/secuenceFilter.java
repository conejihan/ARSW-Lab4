package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class secuenceFilter implements BlueprintFilters{

	@Override
	public Blueprint filter(Blueprint bp) {
		List<Point> originalP = bp.getPoints();
	
		List<Point> newP = new ArrayList<Point>();
		for(int i = 0; i < originalP.size(); i++) {
			if(i % 2 == 0) {
				newP.add(originalP.get(i));
			}
		}
		Blueprint filteredbp = new Blueprint(bp.getAuthor(), bp.getName(), newP);
		return filteredbp;
	}

}
