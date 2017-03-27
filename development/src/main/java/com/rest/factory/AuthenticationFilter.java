//package com.rest.factory;
//
//import java.io.IOException;
//import javax.ws.rs.ext.Provider;
//import javax.annotation.Priority;
//import javax.ws.rs.NotAuthorizedException;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//
//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class AuthenticationFilter implements ContainerRequestFilter {
//
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		System.out.println("Inetecerptor called");
//
//		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
//			throw new NotAuthorizedException("Authorization header must be provided");
//
//		String token = authorizationHeader.substring("Bearer".length()).trim();
//		try {
//			if (!SecurityFilterFactory.validateJWT(token))
//				throw new Exception("Token is Invalid");
//
//		} catch (Exception e) {
//			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//		}
//	}
//}