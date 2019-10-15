package com.grpc.grpcclient.configuration;


import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dss.grpc.AttItem;
import com.dss.grpc.AttItemList;
import com.dss.grpc.AttItemServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
@Service
public class GrpcClientConfig {
	 
	 private AttItemServiceGrpc.AttItemServiceBlockingStub attItemServiceBlockingStub;
	 
	 public void getData(AttItem attItem) {
		 long currentTimeMillisStart = System.currentTimeMillis();
			 AttItemList attItemList = attItemServiceBlockingStub.getItemService(attItem);
			 long currentTimeMillisend = System.currentTimeMillis();
			 
		System.out.println(/* attItemList.getSerializedSize()+ */"size of the list "+attItemList.getAttItemCount()+" time taken by Grpc : "+(currentTimeMillisStart-currentTimeMillisend));
		  }
	 
	   @PostConstruct
	    private void initializeClient() { 
		   ManagedChannel channel =  ManagedChannelBuilder.forAddress("localhost", 7565).usePlaintext(false).build();
	    	 
//	    	INTERNAL: Frame size 9849014 exceeds maximum: 4194304. If this is normal, increase the maxMessageSize in the channel/server builder
	
	attItemServiceBlockingStub=AttItemServiceGrpc.newBlockingStub(channel);
	    }
	   
	   
	   public void getDataStream(AttItem attItem) {
			
		   long currentTimeMillisStart = System.currentTimeMillis();
				 Iterator<AttItemList> attItemList = attItemServiceBlockingStub.getItemStreamService(attItem);
				 long currentTimeMillisend = System.currentTimeMillis();
				 System.out.println("size of list by using grpc  "+attItemList.next().getSerializedSize());
				 System.out.println( " Time taken by GrpcStream: "+(currentTimeMillisStart-currentTimeMillisend));
			  }
		 
		 
	   
}
