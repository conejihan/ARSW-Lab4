/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;


/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
	@Autowired
	BlueprintsServices bps = new BlueprintsServices();
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> GetAllBluePrints(){
		return new ResponseEntity<>(bps.getAllBlueprints(),HttpStatus.ACCEPTED);   
		
	}
    
	@RequestMapping(method = RequestMethod.GET, value = "/{author}")
	public ResponseEntity<?> GetBluePrintsByAuthor(@PathVariable String author){
		try {
			return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>("Blueprints not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{author}/{bpname}")
	public ResponseEntity<?> GetBluePrint(@PathVariable String author, @PathVariable String bpname){
	
			try {
				return new ResponseEntity<>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
			} 

				
			catch (Exception e) {
				return new ResponseEntity<>("Blueprint not found", HttpStatus.NOT_FOUND);
			}
		
		
		
	}
	
	@RequestMapping(value = "/addBlueprint", method = RequestMethod.POST)	
	public ResponseEntity<?> addBlueprint(@RequestBody JSONObject js){
	    try {
	        //registrar dato
	    	//bps.addNewBlueprint(bp);
	    	String name = js.getString("name");
	    	String author = js.getString("author");
	  
	    	Blueprint bp1 = new Blueprint(author, name);
	    	bps.addNewBlueprint(bp1);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (BlueprintPersistenceException ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Not posible to create Blueprint",HttpStatus.FORBIDDEN);            
	    }        

	}
	@RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.PUT)	
	public ResponseEntity<?> putBlueprint(@PathVariable String author, @PathVariable String name, @RequestBody JSONObject js) throws Exception{
	    try {
	        //registrar dato
	    	//bps.addNewBlueprint(bp);
	 
	    	Blueprint bp1 = bps.getBlueprint(author, name);
	    	bp1.setName(js.getString("name"));
	    	bps.addNewBlueprint(bp1);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (BlueprintPersistenceException ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Not posible to create Blueprint",HttpStatus.FORBIDDEN);            
	    }        

	}
	
    
    
}

