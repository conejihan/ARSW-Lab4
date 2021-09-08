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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
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
			return new ResponseEntity<>("Blueprints not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{author}/{bpname}")
	public ResponseEntity<?> GetBluePrint(@PathVariable String author, @PathVariable String bpname){
		try {
			return new ResponseEntity<>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>("Blueprint not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
    
    
}

