//package com.rest.factory;
//
//import org.jose4j.jwk.RsaJsonWebKey;
//import org.jose4j.jwk.RsaJwkGenerator;
//import org.jose4j.lang.JoseException;
//
//public class RsaKeyProducer {
//	private RsaKeyProducer() {}
//	private static RsaJsonWebKey webKey;
//	
//	public static RsaJsonWebKey produce(){
//		if(webKey == null){
//			try{
//				webKey = RsaJwkGenerator.generateJwk(2048);
//			}
//			catch(JoseException ex){
//				System.out.println(ex);
//			}
//		}
//		return webKey;
//	}
//}