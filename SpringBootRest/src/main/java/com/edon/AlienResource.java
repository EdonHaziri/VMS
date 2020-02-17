package com.edon;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienResource {

	@RequestMapping("/ints")
	public List<Integer> getIntegers(){
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(3);
		return ints;
	}
}
