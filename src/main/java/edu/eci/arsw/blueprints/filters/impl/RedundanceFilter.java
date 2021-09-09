package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;


public class RedundanceFilter implements BlueprintFilters{

	@Override
	public Blueprint filter(Blueprint bp) throws BlueprintNotFoundException {
		
		
		List<Point> originalP = bp.getPoints();
		List<Point> newP = new ArrayList<Point>();
		Point saveP =originalP.get(0);
		for (int i = 0; i < originalP.size(); i++) {
			
			if(originalP.get(i).getX() == saveP.getX() && originalP.get(i).getY() == saveP.getY()){
				
				saveP = saveP;
			} else {
	
				newP.add(saveP);
				saveP = originalP.get(i);
				
			}
			if(i == originalP.size() - 1) {
				newP.add(saveP);
			}
		
			
		}
	
		Blueprint filteredbp = new Blueprint(bp.getAuthor(), bp.getName(), newP);
		return filteredbp;
	}
	

}
