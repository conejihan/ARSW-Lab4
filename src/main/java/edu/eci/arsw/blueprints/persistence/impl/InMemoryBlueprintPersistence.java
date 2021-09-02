/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 *
 * @author hcadavid
 */
@Component("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
        Point[] pts1=new Point[]{new Point(25, 25),new Point(15, 15)};
        Blueprint bp1=new Blueprint("Anastasius", "CasaFlor",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        
        Point[] pts2=new Point[]{new Point(30, 30),new Point(45, 45)};
        Blueprint bp2=new Blueprint("Anastasius", "CasaVacacional",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        
        Point[] pts3=new Point[]{new Point(25, 25),new Point(15, 15)};
        Blueprint bp3=new Blueprint("Pancrasio", "EstadioMayorA",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) {
		HashSet<Blueprint> Rauthor = new HashSet<Blueprint>();
    	Set<Entry<Tuple<String,String>,Blueprint>> blueprint2= blueprints.entrySet();
    	for (Entry<Tuple<String, String>, Blueprint> i: blueprint2) {
    		if (i.getKey().o1.equals(author)) {
    			Rauthor.add(i.getValue());
    		}
    	}
    	
    	return Rauthor;
	}

	@Override
	public Set<Blueprint> getAllBlueprints() {

		
		return blueprints.values().stream().collect(Collectors.toSet());
	}



    
    
}
