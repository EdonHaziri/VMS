package com.edon.demo.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edon.demo.repository.ServiceCenterRepository;
import com.edon.demo.model.User;
import com.edon.demo.model.ServiceCenter;

@Service
public class ServiceCenterService {
	@Autowired
	private ServiceCenterRepository serviceRepo;
	public ServiceCenter getBranchById(String serviceId)
	{
		ServiceCenter sercen= serviceRepo.findByBrand(serviceId).get();
	
		
		return sercen;	
	}
	public List<ServiceCenter> getAllServiceCenter(){
		List<ServiceCenter> list = new ArrayList<>();
		serviceRepo.findAll().forEach(sercen->list.add(sercen));
		 
		return list;
		
	}
	public ServiceCenter addServiceCenter(ServiceCenter sercen){
		serviceRepo.save(sercen);
		return sercen;
		
	}
	public void updateServiceCenter(ServiceCenter sercen) {
		serviceRepo.save(sercen);
	}
	public void deleteBranch(Integer prdId) {
		serviceRepo.deleteByBrand(prdId);
	}
	
	}
	

	


