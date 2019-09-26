package com.grpc.grpcclient.configuration;


import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.grpc.grpcserver.proto.AttItem;
import com.grpc.grpcserver.proto.AttItemList;
import com.grpc.grpcserver.proto.AttItemServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
@Service
public class GrpcClientConfig {
	 
	 private AttItemServiceGrpc.AttItemServiceBlockingStub attItemServiceBlockingStub;
	 
	 public void getData(AttItem attItem) {
		 long currentTimeMillisStart = System.currentTimeMillis();
			 AttItemList attItemList = attItemServiceBlockingStub.getItemService(attItem);
			 long currentTimeMillisend = System.currentTimeMillis();
			 System.out.println(attItemList.getAttItemCount()+" time taken by Grpc : "+(currentTimeMillisStart-currentTimeMillisend));
		  }
	 
	   @PostConstruct
	    private void initializeClient() { 
	ManagedChannel managedChannelImpl = ManagedChannelBuilder.forAddress("localhost", 7565)
	               .build();
	//ManagedChannelBuilder.forAddress("dss-433.dsslp.com", 6565).usePlaintext(true).build();
	    	 
//	    	INTERNAL: Frame size 9849014 exceeds maximum: 4194304. If this is normal, increase the maxMessageSize in the channel/server builder
	
	attItemServiceBlockingStub=AttItemServiceGrpc.newBlockingStub(managedChannelImpl);
	    }
	   
	   
	   public void getDataStream(AttItem attItem) {
			
		   long currentTimeMillisStart = System.currentTimeMillis();
				 Iterator<AttItemList> attItemList = attItemServiceBlockingStub.getItemStreamService(attItem);
//				attItemList.forEachRemaining(item->{
//					System.out.println("count is : "+item.getAttItemCount());;
//				 });
				 long currentTimeMillisend = System.currentTimeMillis();
				 System.out.println( " Tiem taken by GrpcStream: "+(currentTimeMillisStart-currentTimeMillisend));
			  }
		 
		 
	   
}
