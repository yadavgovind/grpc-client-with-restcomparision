package com.grpc.grpcclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.grpc.grpcclient.configuration.GrpcClientConfig;
import com.grpc.grpcserver.AttItemListDTO;
import com.grpc.grpcserver.proto.AttItem;
import com.grpc.grpcserver.proto.AttItemList;

@RestController
public class AttItemController {

	@Autowired
	private GrpcClientConfig  client;
	
RestTemplate template= new RestTemplate();	
	@GetMapping(value="/grpc_atttest")
	public  String   getAttItem(){
		 long currentTimeMillisStart = System.currentTimeMillis();
		 client.getData(AttItem.newBuilder().build());
		 long currentTimeMillisend = System.currentTimeMillis();
		 System.out.println(" Tiem taken by Grpc in controller : "+(currentTimeMillisStart-currentTimeMillisend));
		return " time taken by Grpc in Single : "+(currentTimeMillisStart-currentTimeMillisend);
	}
	
	
	@GetMapping(value="/grpc_stream_atttest")
	public  String   getStreamAttItem(){
		 long currentTimeMillisStart = System.currentTimeMillis();
		 client.getDataStream(AttItem.newBuilder().build());
		 long currentTimeMillisend = System.currentTimeMillis();
		 System.out.println(" time taken by Grpc in controller : "+(currentTimeMillisStart-currentTimeMillisend));
		return " time taken by Grpc in Stream : "+(currentTimeMillisStart-currentTimeMillisend);
	}
	
	
	@GetMapping(value="/rest_atttest")
	public  String   getRestAttItem(){
		String url="http://localhost:7080/getData";
		 long currentTimeMillisStart = System.currentTimeMillis();
		 client.getData(AttItem.newBuilder().build());
			AttItemListDTO forObject = template.getForObject(url, AttItemListDTO.class);
			System.out.println(forObject.getDataEnity().size());
			 long currentTimeMillisend = System.currentTimeMillis();
		 System.out.println(" time taken by rest template in controller : "+(currentTimeMillisStart-currentTimeMillisend));
		return " time taken by rest template  : "+(currentTimeMillisStart-currentTimeMillisend);
	}
	
}
