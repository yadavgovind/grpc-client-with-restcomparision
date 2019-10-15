package com.grpc.grpcclient.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grpc.grpcclient.configuration.ObjectSizeMapper;

import de.bonprix.sourcing.dmart.dto.AttItem;

@RestController
public class DmartController {

@Resource
DmartService dmartService;

@GetMapping("/dmart_service")
public String  getAttItem() {
	  long currentTimeMillisStart = System.currentTimeMillis();
	  Set<AttItem> attItem = dmartService.getAttItem();
		 long currentTimeMillisend = System.currentTimeMillis();
		 System.out.println( " Time Taken by Dmart Service for "+attItem.size()+" records : "+(currentTimeMillisend-currentTimeMillisStart));
		 AttItem next = attItem.iterator().next();
		 ObjectSizeMapper.main("de.bonprix.sourcing.dmart.dto.AttItem");
		 return " Time Taken by Dmart Service for "+attItem.size()+" records : "+(currentTimeMillisend-currentTimeMillisStart);
}
	
}
