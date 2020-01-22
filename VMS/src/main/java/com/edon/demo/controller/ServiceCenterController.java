package com.edon.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edon.demo.model.ServiceCenter;
import com.edon.demo.services.ServiceCenterService;

@RequestMapping("/branches")
@RestController
public class ServiceCenterController {
	@Autowired
    private ServiceCenterService sercenService;
	 @GetMapping("/{BranchId}")
	    public ResponseEntity <ServiceCenter>getSerCenById(@PathVariable("BranchId") String BranchId) {
		 ServiceCenter sercen = sercenService.getBranchById(BranchId);
	        
	        return new ResponseEntity<ServiceCenter>(sercen,HttpStatus.OK);
	    }
	 @GetMapping("/allBranches")
	    public ResponseEntity <List<ServiceCenter>>getAllBranches() {
	    	List<ServiceCenter> sercen = sercenService.getAllServiceCenter();	        
	        return new ResponseEntity<>(sercen,HttpStatus.OK);
	    }
	 @PostMapping("/insert")
     public ResponseEntity<Void> ServiceCenter(@RequestBody ServiceCenter sercen, UriComponentsBuilder builder){
     ServiceCenter flag = sercenService.addServiceCenter(sercen);
      if(flag==null)
         return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      HttpHeaders header = new HttpHeaders();
      header.setLocation(builder.path("/branches")
              .buildAndExpand(sercen.getBrand()).toUri());
      return new ResponseEntity<>(header, HttpStatus.CREATED);
  }
	 @PutMapping("/update")
	    public ResponseEntity<ServiceCenter>    updateBranches(@RequestBody ServiceCenter sercen){
		 sercenService.updateServiceCenter(sercen);
	     return new ResponseEntity<>(sercen, HttpStatus.OK);
	     
	 }
}