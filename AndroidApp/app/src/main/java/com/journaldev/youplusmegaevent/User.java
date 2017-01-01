package com.journaldev.youplusmegaevent;

import java.util.Date;


public class User {


String id;

	String PHONENUMBER;
	String AGE;
	String GENDER;
	String STATE;
	String CITY;
	String AREA;
	String INCOME;
	String VECHICALINFORMATION;
	String SHOPPINGINFORMATION;
	String Luvit;
	String FairLovely;
	String Cherio;
	String DoveShampoo;
	String Atta;
	String Hairconditioner;
	String CREATEDDATE;
	String promoternumber;
	String locationofEvent;

	String areaNameofEvent;

	public String getLocationofEvent() {
		return locationofEvent;
	}

	public void setLocationofEvent(String locationofEvent) {
		this.locationofEvent = locationofEvent;
	}

	public String getAreaNameofEvent() {
		return areaNameofEvent;
	}

	public void setAreaNameofEvent(String areaNameofEvent) {
		this.areaNameofEvent = areaNameofEvent;
	}

	/*public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPHONENUMBER() {
		return PHONENUMBER;
	}

	public void setPHONENUMBER(String pHONENUMBER) {
		PHONENUMBER = pHONENUMBER;
	}

	public String getAGE() {
		return AGE;
	}

	public void setAGE(String aGE) {
		AGE = aGE;
	}

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public String getINCOME() {
		return INCOME;
	}

	public void setINCOME(String iNCOME) {
		INCOME = iNCOME;
	}

	public String getVECHICALINFORMATION() {
		return VECHICALINFORMATION;
	}

	public void setVECHICALINFORMATION(String vECHICALINFORMATION) {
		VECHICALINFORMATION = vECHICALINFORMATION;
	}

	public String getSHOPPINGINFORMATION() {
		return SHOPPINGINFORMATION;
	}

	public void setSHOPPINGINFORMATION(String sHOPPINGINFORMATION) {
		SHOPPINGINFORMATION = sHOPPINGINFORMATION;
	}

	public String getLuvit() {
		return Luvit;
	}

	public void setLuvit(String luvit) {
		Luvit = luvit;
	}

	public String getFairLovely() {
		return FairLovely;
	}

	public void setFairLovely(String fairLovely) {
		FairLovely = fairLovely;
	}

	public String getCherio() {
		return Cherio;
	}

	public void setCherio(String cherio) {
		Cherio = cherio;
	}

	public String getDoveShampoo() {
		return DoveShampoo;
	}

	public void setDoveShampoo(String doveShampoo) {
		DoveShampoo = doveShampoo;
	}

	public String getAtta() {
		return Atta;
	}

	public void setAtta(String atta) {
		Atta = atta;
	}

	public String getHairconditioner() {
		return Hairconditioner;
	}

	public void setHairconditioner(String hairconditioner) {
		Hairconditioner = hairconditioner;
	}

	public String getCREATEDDATE() {
		return CREATEDDATE;
	}

	public void setCREATEDDATE(String cREATEDDATE) {
		CREATEDDATE = cREATEDDATE;
	}

	public String getPromoternumber() {
		return promoternumber;
	}

	public void setPromoternumber(String promoternumber) {
		this.promoternumber = promoternumber;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", PHONENUMBER='" + PHONENUMBER + '\'' +
				", AGE='" + AGE + '\'' +
				", GENDER='" + GENDER + '\'' +
				", STATE='" + STATE + '\'' +
				", CITY='" + CITY + '\'' +
				", AREA='" + AREA + '\'' +
				", INCOME='" + INCOME + '\'' +
				", VECHICALINFORMATION='" + VECHICALINFORMATION + '\'' +
				", SHOPPINGINFORMATION='" + SHOPPINGINFORMATION + '\'' +
				", Luvit='" + Luvit + '\'' +
				", FairLovely='" + FairLovely + '\'' +
				", Cherio='" + Cherio + '\'' +
				", DoveShampoo='" + DoveShampoo + '\'' +
				", Atta='" + Atta + '\'' +
				", Hairconditioner='" + Hairconditioner + '\'' +
				", CREATEDDATE='" + CREATEDDATE + '\'' +
				", promoternumber='" + promoternumber + '\'' +
				'}';
	}
}
