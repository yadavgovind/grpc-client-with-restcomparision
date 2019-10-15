package com.grpc.grpcclient.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.bonprix.sourcing.dmart.dto.AttItem;
import de.bonprix.sourcing.dmart.filter.dto.AttItemFilter;
import de.bonprix.user.dto1.AuthenticationAttempt;
import de.bonprix.user.dto1.AuthenticationResult;


@Component
public class DmartService {
	static RestTemplate restTemplate = new RestTemplate();

	public Set<AttItem> getAttItem(){
		Set<AttItem> attItems= new HashSet<AttItem>();
		String itemServiceFindAll = "http://dev.int.k8s.bonprix.de/dmart-ws-dev/items/";
		HttpHeaders headers = new HttpHeaders();
//    	headers.add("Authorization", "Basic " + authKey);
		headers.add("authKey", /* "Basic " + */ generateAuthKey() );
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		AttItemFilter attItemFilter = new AttItemFilter();
		attItemFilter.setClientId(1l);
		attItemFilter.setPage(0);
		attItemFilter.setPageSize(100);
		ParameterizedTypeReference<Set<AttItem>> parameterizedTypeReference = new ParameterizedTypeReference<Set<AttItem>>() {
		};
		try {
			RequestEntity<AttItemFilter> requestEntity = new RequestEntity<>(attItemFilter, headers, HttpMethod.POST,
					new URI(itemServiceFindAll));
			 attItems = restTemplate.exchange(requestEntity, parameterizedTypeReference).getBody();
			for (AttItem attItem : attItems) {
				System.out.println(attItem.getId());
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attItems;
	}
	
	
	public String generateAuthKey() {
		String generateUrl = "https://dsuite.bonprix.net/user-ws/authenticate/generate";

		String username = "dquality-ui";// "dsuite-parent";
		String password = "n614Ql7v";// "4pw86uwxyEeQZQAE";
		long applicationId = 59L;
		AuthenticationAttempt authenticationAttempt = new AuthenticationAttempt(username,password,1l);
		authenticationAttempt.setUsername(username);
		authenticationAttempt.setPassword(password);
		authenticationAttempt.setClientId(1l);
		HttpEntity<AuthenticationAttempt> request = new HttpEntity<>(authenticationAttempt);
		ResponseEntity<AuthenticationResult> exchange = restTemplate.exchange(generateUrl, HttpMethod.POST, request,
				AuthenticationResult.class);
		System.out.println(exchange.getBody());// ZHN1aXRlLXBhcmVudDoxOjEzMDY6LTE4ODM5MzAyNDE6MzhiN2Q1MWYzYTZlZjE0MGI0OGZkZjBjY2ZkODEyMDI=
		AuthenticationResult authenticationResult = exchange.getBody();
		String authKey = authenticationResult.getAuthKey();

		// ZGJ1c2luZXNzcGFydG5lci13czoxOjEzMTE6NDQ1OTgwMDg0OjBhYjdlYzY3OWRhNjJhOTMzMWE5YzhjOGU0ZmI3ODli
		System.out.println("authKey ->" + authKey);
		return authKey;
	}
	public static void main(String[] args) {
		

//		String plainCredentials = "admin:password";
//		String base64Credentials = new String(
//				org.apache.tomcat.util.codec.binary.Base64.encodeBase64(plainCredentials.getBytes()));
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Basic " + base64Credentials);
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		HttpEntity<String> request = new HttpEntity<String>(headers);
//		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/employees/", HttpMethod.GET,
//				request, String.class);
//		System.out.println(response.getBody());

		String generateUrl = "https://dsuite.bonprix.net/user-ws/authenticate/generate";
//		String validateUrl = "https://dsuite.bonprix.net/user-ws/authenticate/validate";

		String username = "dquality-ui";// "dsuite-parent";
		String password = "n614Ql7v";// "4pw86uwxyEeQZQAE";
		long applicationId = 59L;
		AuthenticationAttempt authenticationAttempt = new AuthenticationAttempt(username,password,1l);
		authenticationAttempt.setUsername(username);
		authenticationAttempt.setPassword(password);
		authenticationAttempt.setClientId(1l);
		HttpEntity<AuthenticationAttempt> request = new HttpEntity<>(authenticationAttempt);
		ResponseEntity<AuthenticationResult> exchange = restTemplate.exchange(generateUrl, HttpMethod.POST, request,
				AuthenticationResult.class);
		System.out.println(exchange.getBody());// ZHN1aXRlLXBhcmVudDoxOjEzMDY6LTE4ODM5MzAyNDE6MzhiN2Q1MWYzYTZlZjE0MGI0OGZkZjBjY2ZkODEyMDI=
		AuthenticationResult authenticationResult = exchange.getBody();
		String authKey = authenticationResult.getAuthKey();

		// ZGJ1c2luZXNzcGFydG5lci13czoxOjEzMTE6NDQ1OTgwMDg0OjBhYjdlYzY3OWRhNjJhOTMzMWE5YzhjOGU0ZmI3ODli
		System.out.println("authKey ->" + authKey);
//		ValidationAttempt validationAttempt = new ValidationAttempt(authKey, applicationId);
//		ResponseEntity<ValidationResult> exchange2 = restTemplate.exchange(validateUrl, HttpMethod.POST,
//				new HttpEntity<>(validationAttempt), ValidationResult.class);
//		ValidationResult body = exchange2.getBody();
//		System.out.println(body);

		String itemServiceFindAll = "http://dev.int.k8s.bonprix.de/dmart-ws-dev/items/";
		HttpHeaders headers = new HttpHeaders();
//    	headers.add("Authorization", "Basic " + authKey);
		headers.add("authKey", /* "Basic " + */ authKey);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		AttItemFilter attItemFilter = new AttItemFilter();
		attItemFilter.setClientId(1l);
		attItemFilter.setPage(0);
		attItemFilter.setPageSize(10);
		ParameterizedTypeReference<Set<AttItem>> parameterizedTypeReference = new ParameterizedTypeReference<Set<AttItem>>() {
		};

//    	ResponseEntity<Set> exchange3 = restTemplate.exchange(itemServiceFindAll, HttpMethod.POST, new HttpEntity<>(attItemFilter, headers),Set.class);
//    	System.out.println(exchange3);

		try {
//			RequestEntity<HttpEntity<AttItemFilter>> request1 = RequestEntity.post(new URI(itemServiceFindAll))
//					.accept(MediaType.APPLICATION_JSON).body(new HttpEntity<>(attItemFilter, headers));

			RequestEntity<AttItemFilter> requestEntity = new RequestEntity<>(attItemFilter, headers, HttpMethod.POST,
					new URI(itemServiceFindAll));
			Set<AttItem> set = restTemplate.exchange(requestEntity, parameterizedTypeReference).getBody();
			for (AttItem attItem : set) {
				System.out.println(attItem.getId());
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
