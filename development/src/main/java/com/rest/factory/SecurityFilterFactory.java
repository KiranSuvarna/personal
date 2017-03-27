package com.rest.factory;

import java.util.List;

import javax.ws.rs.ext.Provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.rest.util.HibernateUtil;

@Provider
public class SecurityFilterFactory {
	public String authenticate(String phoneNumber, String password) {
		SessionFactory sessionFactory = null;
		Session session = null;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Gson gson = new Gson();
		try {
			sessionFactory = hibernateUtil.buildSessionFactory();
			hibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "SELECT password FROM development.users where phone_number = " + phoneNumber;
			List hqlRes = session.createSQLQuery(hql).list();
			String res = gson.toJson(hqlRes.get(0));
			String actualRes = res.replace("\"", "");
			if (actualRes != null || actualRes == password) {
				return actualRes;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
	}

	// public static String buildJWT(String subject) {
	// RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
	// JwtClaims claims = new JwtClaims();
	// claims.setSubject(subject);
	// claims.setIssuer("www.thefuturemarketplace.com");
	// claims.setExpirationTimeMinutesInTheFuture(10);
	// claims.setGeneratedJwtId();
	// claims.setIssuedAtToNow();
	// claims.setNotBeforeMinutesInThePast(2);
	//
	// JsonWebSignature jws = new JsonWebSignature();
	// jws.setPayload(claims.toJson());
	// jws.setKey(rsaJsonWebKey.getPrivateKey());
	// jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
	//
	// String jwt = null;
	// try {
	// jwt = jws.getCompactSerialization();
	// return jwt;
	// } catch (JoseException ex) {
	// System.out.println(ex);
	// return ex.getMessage();
	// }
	//
	// }
	//
	// public static boolean validateJWT(String token) {
	// boolean flag = false;
	// RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
	// JwtConsumer jwtConsumer = new
	// JwtConsumerBuilder().setRequireExpirationTime().setAllowedClockSkewInSeconds(30)
	// .setRequireSubject().setExpectedIssuer("www.thefuturemarketplace.com")
	// .setVerificationKey(rsaJsonWebKey.getKey()).build();
	// try {
	// JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
	// System.out.println("Token Validated" + jwtClaims);
	// flag = true;
	// } catch (Exception ex) {
	// flag = false;
	// System.out.println("Token Invalid");
	// }
	// return flag;
	// }

	public static void main(String[] args) {
		SecurityFilterFactory s = new SecurityFilterFactory();
		// String se = s.authenticate("8105505579", "password123");
		// System.out.println(se);
		// System.out.println(s.validateJWT(s.buildJWT("8105505579")));
		// System.out.println(s.validateJWT(s.buildJWT(("sd"))));

	}
}
