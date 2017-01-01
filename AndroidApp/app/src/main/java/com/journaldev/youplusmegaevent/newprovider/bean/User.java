package com.journaldev.youplusmegaevent.newprovider.bean;

import android.database.Cursor;

/**
 * Created by Naveen on 6/24/2015.
 */
public class User extends CuresorResource<User> {

    public static final String TABLE_NAME = "user";
   /* public static final String ID = "id";
    public static final String ITEMNAME = "itemname";
    public static final String ITEMCODE = "itemcode";
    public static final String WEIGHT = "weight";
    public static final String MAPRICE = "mapprice";
    public static final String PRICE = "price";*/

    public static final  String _ID = "_id";
    /*  String _STATUS = "_status";*/
    public static final  String PHONENUMBER = "phonenumber";
    public static final  String AGE = "age";
    public static final  String  GENDER= "gender";
    public static final  String STATE = "state";
    public static final  String CITY = "city";
    public static final   String ARE = "area";
    public static final   String INCOME = "income";

    public static final   String VECHICALINFO = "vehicalInformation";
    public static final  String SHOPPINGINFO = "ShoppingInformation";
    public static final String CREATEDDATE = "createddate";


    public static final  String LOVIT = "lovit";
    public static final   String HAIR = "hair";
    public static final   String DOVE = "dove";

    public static final   String ATTAT = "attat";
    public static final  String CHEIRO = "cheior";
    public static final String FAIRNLOVLY = "fairnlovly";
    public static final String PROMOTERNUMBER = "promoternumber";
    public static final String LOCATIONOFEVENT = "locationofEvent";
    public static final String AREAOFEVENT = "areaNameofEvent";

  /*  public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String ITEM_DATE = "itemdate";*/


    public Cursor cursor;

    private String id;


    private String phonenumber;
    private String age;
    private String gender;

    private String state;
    private String city;

    private String area;
    private String income;
    private String vehicalInformation;
    private String ShoppingInformation;
    private String createddate;


    private String lovit;

    private String fairnlovly;
    private String dove;
    private String hair;
    private String attat;
    private String cheior;
    String promoternumber;

    private String locationofEvent;
    private String areaNameofEvent;


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

    public String getPromoternumber() {
        return promoternumber;
    }

    public void setPromoternumber(String promoternumber) {
        this.promoternumber = promoternumber;
    }

    public String getLovit() {
        return lovit;
    }

    public void setLovit(String lovit) {
        this.lovit = lovit;
    }

    public String getFairnlovly() {
        return fairnlovly;
    }

    public void setFairnlovly(String fairnlovly) {
        this.fairnlovly = fairnlovly;
    }

    public String getDove() {
        return dove;
    }

    public void setDove(String dove) {
        this.dove = dove;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getAttat() {
        return attat;
    }

    public void setAttat(String attat) {
        this.attat = attat;
    }

    public String getCheior() {
        return cheior;
    }

    public void setCheior(String cheior) {
        this.cheior = cheior;
    }


    /*  private String transaction_type;


    private String itemdate;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() { super(User.class); }
    public User(Cursor cursor) { super(User.class);
        cursor.moveToFirst();
        this.bindBean(cursor);
        cursor.close();
    }

    @Override
    public void bindBean(Cursor cursor) {


        this.cursor = cursor;
        this.id = cursor.getString(cursor.getColumnIndex(_ID));
        this.phonenumber = cursor.getString(cursor.getColumnIndex(PHONENUMBER));
        this.age = cursor.getString(cursor.getColumnIndex(AGE));
        this.gender = cursor.getString(cursor.getColumnIndex(GENDER));



        this.state=cursor.getString(cursor.getColumnIndex(STATE));
        this.city=cursor.getString(cursor.getColumnIndex(CITY));
        this.area=cursor.getString(cursor.getColumnIndex(ARE));
        this.income=cursor.getString(cursor.getColumnIndex(INCOME));


        this.vehicalInformation=cursor.getString(cursor.getColumnIndex(VECHICALINFO));
        this.ShoppingInformation=cursor.getString(cursor.getColumnIndex(SHOPPINGINFO));



        this.lovit=cursor.getString(cursor.getColumnIndex(LOVIT));
        this.fairnlovly=cursor.getString(cursor.getColumnIndex(FAIRNLOVLY));
        this.cheior=cursor.getString(cursor.getColumnIndex(CHEIRO));
        this.dove=cursor.getString(cursor.getColumnIndex(DOVE));
        this.attat=cursor.getString(cursor.getColumnIndex(ATTAT));
        this.hair=cursor.getString(cursor.getColumnIndex(HAIR));






        this.locationofEvent=cursor.getString(cursor.getColumnIndex(LOCATIONOFEVENT));
        this.areaNameofEvent=cursor.getString(cursor.getColumnIndex(AREAOFEVENT));
        this.promoternumber=cursor.getString(cursor.getColumnIndex(PROMOTERNUMBER));
        this.createddate=cursor.getString(cursor.getColumnIndex(CREATEDDATE));


      /*  this.transaction_type = cursor.getString(cursor.getColumnIndex(TRANSACTION_TYPE));
        this.itemdate = cursor.getString(cursor.getColumnIndex(ITEM_DATE));*/
   //   this.itemdate=cursor.get(cursor.getColumnIndex(NewItemActivity.getDateTime()));
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getVehicalInformation() {
        return vehicalInformation;
    }

    public void setVehicalInformation(String vehicalInformation) {
        this.vehicalInformation = vehicalInformation;
    }

    public String getShoppingInformation() {
        return ShoppingInformation;
    }

    public void setShoppingInformation(String shoppingInformation) {
        ShoppingInformation = shoppingInformation;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    @Override
    public String toString() {
        return "User{" +
                "cursor=" + cursor +
                ", id='" + id + '\'' +
                ", promoternumber='" + promoternumber + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", income='" + income + '\'' +
                ", vehicalInformation='" + vehicalInformation + '\'' +
                ", ShoppingInformation='" + ShoppingInformation + '\'' +
                ", createddate='" + createddate + '\'' +
                ", lovit='" + lovit + '\'' +
                ", fairnlovly='" + fairnlovly + '\'' +
                ", dove='" + dove + '\'' +
                ", hair='" + hair + '\'' +
                ", attat='" + attat + '\'' +
                ", cheior='" + cheior + '\'' +
                '}';
    }

}
