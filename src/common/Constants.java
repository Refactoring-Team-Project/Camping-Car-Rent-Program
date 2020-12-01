package common;

import javax.swing.*;
public class Constants {

    public static final String CAMPCOMP = "Camping_Company";
    public static final String CAMPCAR = "Camping_Car";
    public static final String CUSTOMER = "Rent_Customer";
    public static final String REPAIRSHOP = "Repairshop";
    public static final String RENTCAR = "Rent_Car";
    public static final String CARCHECK = "Car_Check";
    public static final String REPAIRLIST = "Repair_List";

    
    public static final String RENTLIST = "Camping_Car(대여가능)";
    public static final String SEARCH1 = "검색1";
    public static final String SEARCH2 = "검색2";
    public static final String SEARCH3 = "검색3";
    public static final String SEARCH4 = "검색4";


    public static final String[] ADMIN_BUTTON_NAME = {
                 "Camping_Company", "Camping_Car", "Rent_Customer", "Repairshop", "Rent_Car", "Car_Check", "Repair_List", "검색1", "검색2", "검색3", "검색4"};

    public static final String[] USER_BUTTON_NAME = {"Camping_Car(대여가능)", "검색1", "검색2", "검색3"};

    public static final String[] CAMPINGCAR_FIELDSTRING = { "carid", "carname", "carno", "seat", "manufacturer", "manu_year", "drivingdistance",
            "rentcost", "compid", "registdate" };
    public static final int[] CAMPINGCAR_FIELDLENGTH = { 3, 5, 3, 3, 10, 5, 7, 5, 3, 10 };
    public static final int CAMPINGCAR_FIELDNUM = 10;

    public static final String[] CAMPINGCOMPANY_FIELDSTRING = {"compid", "compname", "address", "phone", "manager_name", "manager_email"};
    public static final int[] CAMPINGCOMPANY_FIELDLENGTH = {3, 7, 10, 10, 7, 10};
    public static final int CAMPINGCOMPANY_FIELDNUM = 6;

    public static final String[] CARCHECK_FIELDSTRING = { "repairno", "carid", "shopid", "compid", "license_no", "repairdetails",
            "repairdate", "repaircost", "paymentdeadline", "repairhistory" };
    public static final int[] CARCHECK_FIELDLENGTH = { 3, 3, 3, 3, 3, 10, 5, 3, 5, 10 };
    public static final int CARCHECK_FIELDNUM = 10;

    public static final String[] CARRENTLIST_FIELDSTRING = { "rentno", "carid", "license-no", "compid", "rent_date", "rentalperiod", "charge", "paymentdeadline", "billhistory", "billhistorycost"};
    public static final int[] CARRENTLIST_FIELDLENGTH = { 3, 3, 3, 3, 10, 5, 5, 10, 10, 5};
    public static final int CARRENTLIST_FIELDNUM = 10;

    public static final String[] RENTCAR_FIELDSTRING = { "rentno", "carid", "explain_front", "explain_left", "explain_right", "explain_back",
            "repair_required" };
    public static final int[] RENTCAR_FIELDLENGTH = { 3, 3, 10, 10, 10, 10, 2 };
    public static final int RENTCAR_FIELDNUM = 7;

    public static final String[] RENTCUSTOMER_FIELDSTIRNG = { "license_no", "name", "address", "phone", "email" };
    public static final int[] RENTCUSTOMER_FIELDLENGTH = { 3, 7, 10, 10, 10 };
    public static final int RENTCUSTOMER_FIELDNUM = 5;

    public static final String[] REPAIRLIST_FIELDSTIRNG = {"repairno", "carid", "shopid", "compid", "license_no", "repairdetails", "repairdate",
            "repaircost", "paymentdeadline", "repairhistory"};
    public static final int[] REPAIRLIST_FIELDLENGTH = {3, 3, 3, 3, 3, 10, 5, 3, 5, 10};
    public static final int REPAIRLIST_FIELDNUM = 10;

    public static final String[] REPAIRSHOP_FIELDSTIRNG = { "shopid", "shopname", "address", "phone", "manager_name", "manager_email" };
    public static final int[] REPAIRSHOP_FIELDLENGTH = { 3, 5, 10, 10, 7, 10 };
    public static final int REPAIRSHOP_FIELDNUM = 6;
}
