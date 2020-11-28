package common;

public class Constants {
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
