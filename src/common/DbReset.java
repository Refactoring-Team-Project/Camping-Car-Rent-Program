package common;

import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbReset {

    public static void resetDB(Connection conn) {
        String sql = "DROP TABLE IF EXISTS Repair_List";
        PreparedStatement pstmt; //insert, delete

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        sql = "DROP TABLE IF EXISTS RepairShop";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        sql = "DROP TABLE IF EXISTS Car_Check";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        sql = "DROP TABLE IF EXISTS Car_Rent";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        sql = "DROP TABLE IF EXISTS Rent_Customer";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        sql = "DROP TABLE IF EXISTS Camping_Car";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        sql = "DROP TABLE IF EXISTS Camping_Company";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }
    public static void createTable(Connection conn) {
        ArrayList<String> sql = new ArrayList<String>();
        Statement stmt; //select

        sql.add("CREATE TABLE Camping_Company (\r\n" +
                "  compid      INTEGER PRIMARY KEY,\r\n" +
                "  compname    VARCHAR(20),\r\n" +
                "  address   VARCHAR(40),\r\n" +
                "  phone       VARCHAR(20),\r\n" +
                "  manager_name VARCHAR(10),\r\n" +
                "  manager_email VARCHAR(30)\r\n" +
                ");");
        sql.add("CREATE TABLE Camping_Car (\r\n" +
                "  carid      INTEGER,\r\n" +
                "  carname VARCHAR(20),\r\n" +
                "  carno INTEGER,\r\n" +
                "  seat INTEGER,\r\n" +
                "  manufacturer VARCHAR(20),\r\n" +
                "  manu_year INTEGER,\r\n" +
                "  drivingdistance INTEGER,\r\n" +
                "  rentcost INTEGER,\r\n" +
                "  compid INTEGER,\r\n" +
                "  registdate date,\r\n" +
                "  FOREIGN KEY (compid) REFERENCES Camping_Company(compid) ON DELETE CASCADE,\r\n" +
                "  PRIMARY KEY(carid)\r\n" +
                ");\r\n" +
                "");
        sql.add("CREATE TABLE  Rent_Customer (\r\n" +
                "  license_no      INTEGER PRIMARY KEY,  \r\n" +
                "  name        VARCHAR(20),\r\n" +
                "  address     VARCHAR(40),\r\n" +
                "  phone       VARCHAR(20),\r\n" +
                "  email VARCHAR(30)\r\n" +
                ");");
        sql.add("CREATE TABLE Car_Rent (\r\n" +
                "  rentno INTEGER PRIMARY KEY,\r\n" +
                "  carid  INTEGER ,\r\n" +
                "  license_no  INTEGER,\r\n" +
                "  compid INTEGER,\r\n" +
                "  rentdate DATE ,\r\n" +
                "  rentalperiod INTEGER,\r\n" +
                "  charge INTEGER,\r\n" +
                "  paymentdeadline DATE,\r\n" +
                "  billhistory VARCHAR(40),\r\n" +
                "  billhistorycost INTEGER,\r\n" +
                "  FOREIGN KEY (carid) REFERENCES Camping_Car(carid) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (compid) REFERENCES Camping_Company(compid) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (license_no) REFERENCES Rent_Customer(license_no) ON DELETE CASCADE\r\n" +
                ");");
        sql.add("CREATE TABLE Car_Check (\r\n" +
                "  rentno INTEGER PRIMARY KEY,\r\n" +
                "  carid  INTEGER ,\r\n" +
                "  explan_front VARCHAR(40),\r\n" +
                "  explan_left VARCHAR(40),\r\n" +
                "  explan_right VARCHAR(40),\r\n" +
                "  explan_back VARCHAR(40),\r\n" +
                "  repair_required VARCHAR(1),\r\n" +
                "  FOREIGN KEY (rentno) REFERENCES Car_Rent(rentno) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (carid) REFERENCES Camping_Car(carid) ON DELETE CASCADE\r\n" +
                ");");
        sql.add("CREATE TABLE Repairshop (\r\n" +
                "  shopid INTEGER PRIMARY KEY,\r\n" +
                "  shopname  VARCHAR(20),\r\n" +
                "  address VARCHAR(40),\r\n" +
                "  phone VARCHAR(20),\r\n" +
                "  manager_name VARCHAR(10),\r\n" +
                "  manager_email VARCHAR(30)\r\n" +
                ");");
        sql.add("CREATE TABLE Repair_List (\r\n" +
                "  repairno INTEGER PRIMARY KEY,\r\n" +
                "  carid  INTEGER,\r\n" +
                "  shopid INTEGER,\r\n" +
                "  compid INTEGER,\r\n" +
                "  license_no INTEGER,\r\n" +
                "  repairdetails VARCHAR(40),\r\n" +
                "  repairdate DATE,\r\n" +
                "  repaircost INTEGER,\r\n" +
                "  paymentdeadline DATE,\r\n" +
                "  repairhistory VARCHAR(40),\r\n" +
                "  FOREIGN KEY (carid) REFERENCES Camping_Car(carid) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (compid) REFERENCES Camping_Company(compid) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (shopid) REFERENCES Repairshop(shopid) ON DELETE CASCADE,\r\n" +
                "  FOREIGN KEY (license_no) REFERENCES Rent_Customer(license_no) ON DELETE CASCADE\r\n" +
                ");");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public static void initDB(Connection conn) {
        resetDB(conn);
        createTable(conn);

        PreparedStatement pstmt;
        Statement stmt;
        ArrayList<String> sql = new ArrayList<String>();

        sql.add("insert into camping_company value(1,\"abc컴퍼니\",\"서울특별시 강서구 염창동\",\"010-1234-0987\",\"김진아\",\"jina@naver.com\");");
        sql.add("insert into camping_company value(2,\"뉴에이지\",\"인천광역시 연수구 송도동\",\"010-4322-2354\",\"이우진\",\"woojin@naver.com\");");
        sql.add("insert into camping_company value(3,\"paper컴퍼니\",\"경기도 프라하시\",\"010-2134-2343\",\"김이나\",\"inna@naver.com\");");
        sql.add("insert into camping_company value(4,\"콜미컴퍼니\",\"경기도 수원시\",\"010-3426-8754\",\"송주우\",\"juwo@naver.com\");");
        sql.add("insert into camping_company value(5,\"뉴원컴퍼니\",\"제주특별시 제주동\",\"010-1237-4367\",\"안지아\",\"jia@naver.com\");");
        sql.add("insert into camping_company value(6,\"무사고컴퍼니\",\"대전광역시 노잼동\",\"010-2148-6534\",\"오아나\",\"ana@naver.com\");");
        sql.add("insert into camping_company value(7,\"사랑컴퍼니\",\"강원도 삼척시 동춘동\",\"010-2363-3126\",\"주비\",\"rain@yahoo.com\");");
        sql.add("insert into camping_company value(8,\"제주렌트\",\"강원도 춘천시 잠실동\",\"010-2354-2342\",\"마성민\",\"sungmin@gmail.com\");");
        sql.add("insert into camping_company value(9,\"블라디컴퍼니\",\"서울특별시 광진구 세종동\",\"010-2137-3478\",\"서지아\",\"jia123@naver.com\");");
        sql.add("insert into camping_company value(10,\"오호렌트\",\"서울특별시 강남동 건대동\",\"010-2138-4326\",\"임하리\",\"hari@naver.com\");");
        sql.add("insert into camping_company value(11,\"주지컴퍼니\",\"부산광역시 해운대구\",\"010-8738-4347\",\"주우주\",\"blackhole@naver.com\");");
        sql.add("insert into camping_company value(12,\"아에렌트\",\"부산광역시 목포구\",\"010-1238-5643\",\"이진아\",\"jinna@gmail.com\");");
        sql.add("insert into camping_company value(13,\"오이렌트\",\"서울특별시 뉴욕구\",\"010-6574-2346\",\"김성훈\",\"sunghun1998@naver.com\");");
        sql.add("insert into camping_company value(14,\"원츄렌트\",\"서울특별시 캘리포니아주\",\"010-3246-2745\",\"김효경\",\"hyo@naver.com\");");
        sql.add("insert into camping_company value(15,\"추노렌트\",\"인천광역시 말리부동\",\"010-2316-1235\",\"이우식\",\"sik@naver.com\");");

        for(String s : sql) {
            try {
                pstmt = conn.prepareStatement(s);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sql.clear();

        sql.add("insert into camping_car values(1,\"붕붕이\",3241,6,\"현대\",2010,18000,70000,3,\"2012-12-02\");");
        sql.add("insert into camping_car values(2,\"씽씽이\",2312,4,\"현대\",2011,20000,60000,1,\"2012-11-21\");");
        sql.add("insert into camping_car values(3,\"하우우\",7653,2,\"기아\",2012,10000,60000,2,\"2013-03-02\");");
        sql.add("insert into camping_car values(4,\"티엠아이\",1234,8,\"포르쉐\",2010,12000,80000,6,\"2014-9-2\");");
        sql.add("insert into camping_car values(5,\"으르렁\",9384,8,\"기아\",2014,6000,60000,7,\"2015-2-3\");");
        sql.add("insert into camping_car values(6,\"호랑이\",4673,6,\"현대\",2012,12000,120000,5,\"2015-4-5\");");
        sql.add("insert into camping_car values(7,\"코끼리\",3924,4,\"폭스바겐\",2013,20000,90000,9,\"2014-7-4\");");
        sql.add("insert into camping_car values(8,\"상어\",9863,4,\"르노\",2011,3000,70000,11,\"2012-4-3\");");
        sql.add("insert into camping_car values(9,\"기린\",1324,6,\"람보르기니\",2009,7000,60000,1,\"2012-12-1\");");
        sql.add("insert into camping_car values(10,\"람보르기니\",4353,7,\"포르쉐\",2009,8000,80000,2,\"2011-2-3\");");
        sql.add("insert into camping_car values(11,\"영덕이\",1235,9,\"현대\",2014,20000,70000,3,\"2017-2-5\");");
        sql.add("insert into camping_car values(12,\"박스차\",4732,6,\"닛산\",2016,3000,80000,1,\"2018-12-3\");");
        sql.add("insert into camping_car values(13,\"레이\",3627,4,\"혼다\",2010,21000,90000,14,\"2011-12-3\");");
        sql.add("insert into camping_car values(14,\"아반떼\",3843,2,\"BMW\",2010,13000,110000,2,\"2011-4-7\");");
        sql.add("insert into camping_car values(15,\"소나타\",4273,7,\"기아\",2008,14000,90000,3,\"2010-4-5\");");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Rent_Customer value(1,\"김성훈\",\"인천광역시 연수구 송도동\",\"010-9657-8412\",\"sunghun1998@naver.com\");");
        sql.add("insert into Rent_Customer value(2,\"김효경\",\"서울특별시 성동구 뉴욕동\",\"010-1234-1234\",\"hyobbang@naver.com\");");
        sql.add("insert into Rent_Customer value(3,\"이우식\",\"강원도 삼척시\",\"010-3246-4321\",\"sik@naver.com\");");
        sql.add("insert into Rent_Customer value(4,\"이진아\",\"서울특별시 엘에이주\",\"010-1237-4363\",\"jinna@naver.com\");");
        sql.add("insert into Rent_Customer value(5,\"우리나\",\"인천광역시 오하이오주\",\"010-2365-4364\",\"rina@naver.com\");");
        sql.add("insert into Rent_Customer value(6,\"황소진\",\"인천광역시 미시간주\",\"010-2374-3765\",\"sojin@naver.com\");");
        sql.add("insert into Rent_Customer value(7,\"우아나\",\"서울특별시 도쿄구\",\"010-2378-3263\",\"anaa@naver.com\");");
        sql.add("insert into Rent_Customer value(8,\"하나경\",\"제주특별시 감귤주\",\"010-4263-4364\",\"nakyung@yahoo.com\");");
        sql.add("insert into Rent_Customer value(9,\"이민지\",\"대전광역시 수정구\",\"010-4236-3263\",\"minji@naver.com\");");
        sql.add("insert into Rent_Customer value(10,\"이소진\",\"부산광역시 해운대구\",\"010-4327-3425\",\"sojin@gmail.com\");");
        sql.add("insert into Rent_Customer value(11,\"장광진\",\"광주광역시 기아동\",\"010-3248-3474\",\"gang@naver.com\");");
        sql.add("insert into Rent_Customer value(12,\"김자아\",\"경상북도 창원시\",\"010-2348-7543\",\"jaa@gmail.com\");");
        sql.add("insert into Rent_Customer value(13,\"빅비빅\",\"경기도 수원시\",\"010-4328-5465\",\"bibibik@naver.com\");");
        sql.add("insert into Rent_Customer value(14,\"진진아\",\"경기도 과천시\",\"010-3248-4374\",\"jinjin@naver.com\");");
        sql.add("insert into Rent_Customer value(15,\"노진구\",\"강원도 동해시\",\"010-3428-4537\",\"jingu@yahoo.com\");");


        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();

        sql.add("insert into Car_Rent values(1,1,4,3,\"2019-1-23\",3,180000,\"2019-2-1\", \"X\",0);");
        sql.add("insert into Car_Rent values(2,15,3,3,\"2019-8-2\",4,220000,\"2019-8-11\",\"유류비\",20000);");
        sql.add("insert into Car_Rent values(3,14,5,2,\"2019-4-3\",2,120000,\"2019-4-16\",\"X\",0);");
        sql.add("insert into Car_Rent values(4,14,11,2,\"2019-5-2\",3,150000,\"2019-5-17\",\"사고\",200000);");
        sql.add("insert into Car_Rent values(5,8,1,11,\"2019-3-1\",1,70000,\"2019-3-11\",\"유류비\",30000);");
        sql.add("insert into Car_Rent values(6,7,4,9,\"2019-2-22\",2,120000,\"2019-3-2\",\"X\",0);");
        sql.add("insert into Car_Rent values(7,14,10,2,\"2019-7-5\",7,420000,\"2019-7-17\",\"과속\",30000);");
        sql.add("insert into Car_Rent values(8,2,12,1,\"2018-12-22\",4,200000,\"2019-1-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(9,12,13,1,\"2019-12-20\",3,180000,\"2020-1-3\",\"라면\",6000);");
        sql.add("insert into Car_Rent values(10,3,14,2,\"2019-11-22\",3,180000,\"2019-12-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(11,15,15,3,\"2019-6-3\",3,210000,\"2019-6-16\",\"유류비\",40000);");
        sql.add("insert into Car_Rent values(12,7,2,9,\"2020-1-22\",3,180000,\"2020-2-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(13,8,6,11,\"2019-8-21\",5,250000,\"2019-9-1\",\"아이스크림\",10000);");
        sql.add("insert into Car_Rent values(14,11,8,3,\"2019-12-2\",3,180000,\"2019-12-11\",\"X\",0);");
        sql.add("insert into Car_Rent values(15,5,7,2,\"2019-6-24\",2,120000,\"2019-7-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(16,6,1,5,\"2019-7-04\",3,125000,\"2019-7-10\",\"X\",0);");
        sql.add("insert into Car_Rent values(17,3,2,2,\"2019-5-20\",5,280000,\"2019-6-1\",\"과속\",50000);");
        sql.add("insert into Car_Rent values(18,13,8,14,\"2019-1-3\",2,100000,\"2019-1-15\",\"X\",0);");
        sql.add("insert into Car_Rent values(19,11,3,3,\"2020-6-14\",6,250000,\"2020-6-30\",\"X\",0);");
        sql.add("insert into Car_Rent values(20,9,2,1,\"2019-9-15\",8,170000,\"2019-9-21\",\"주차비\",8000);");
        sql.add("insert into Car_Rent values(21,2,10,1,\"2020-3-17\",4,60000,\"2020-3-25\",\"X\",0);");


        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Car_Check values(1,1,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(2,15,\"Clear\",\"Door scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(3,14,\"Bumber scratch\",\"Door scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(4,14,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(5,8,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(6,7,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(7,14,\"Clear\",\"Window scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(8,2,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(9,12,\"Clear\",\"Clear\",\"Door scratch\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(10,3,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(15,5,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(12,7,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(13,8,\"Bumber Broken\",\"Window scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(14,11,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(16,6,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");


        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Repairshop values(1,\"성훈이네\",\"인천광역시 연수구 송도동\",\"02-3665-8412\",\"김성훈\",\"sunghun1998@naver.com\");");
        sql.add("insert into Repairshop values(2,\"하이카\",\"서울특별시 광진구 세종동\",\"02-3242-3241\",\"이하이\",\"hihi@naver.com\");");
        sql.add("insert into Repairshop values(3,\"효경이네\",\"서울특별시 성동구 읍자동\",\"02-3124-2435\",\"김효경\",\"hyo@naver.com\");");
        sql.add("insert into Repairshop values(4,\"안녕네\",\"대전광역시 수정구\",\"033-2343-3124\",\"안안녕\",\"hi@naver.com\");");
        sql.add("insert into Repairshop values(5,\"쏘카\",\"서울특별시 뉴욕주\",\"02-3245-7652\",\"이지훈\",\"jihuuun@naver.com\");");
        sql.add("insert into Repairshop values(6,\"드럽더비트\",\"인천광역시 함부르크동\",\"032-2146-3859\",\"김비트\",\"raper@naver.com\");");
        sql.add("insert into Repairshop values(7,\"삼성차\",\"광주광역시 분당구\",\"023-4223-3584\",\"이삼성\",\"samsung@naver.com\");");
        sql.add("insert into Repairshop values(8,\"통큰이네\",\"서울특별시 밤베르크\",\"02-2343-7659\",\"이루마\",\"luma@naver.com\");");
        sql.add("insert into Repairshop values(9,\"현대해상\",\"서울특별시 하이델베르크\",\"02-3246-2355\",\"금지훈\",\"jihun@naver.com\");");
        sql.add("insert into Repairshop values(10,\"마이디비\",\"강원도 삼척시 광어\",\"033-2378-4537\",\"김디비\",\"dibi@naver.com\");");
        sql.add("insert into Repairshop values(11,\"세종카\",\"강원도 속초시 자갈치\",\"033-4326-7444\",\"이세종\",\"sejong@naver.com\");");
        sql.add("insert into Repairshop values(12,\"브로들\",\"부산광역시 뷔르츠부르크\",\"030-3246-4326\",\"용브로\",\"bro@naver.com\");");
        sql.add("insert into Repairshop values(13,\"깨끗한브로\",\"전라남도 프라하시\",\"031-2345-6532\",\"하이루\",\"hiroo@naver.com\");");
        sql.add("insert into Repairshop values(14,\"이디야네\",\"경상북도 베를린\",\"021-4324-4533\",\"이딘야\",\"dinya@yahoo.com\");");
        sql.add("insert into Repairshop values(15,\"오늘내일\",\"경상남도 브레멘\",\"055-3324-3423\",\"이투마로\",\"tomorrow@naver.com\");");


        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Repair_List values(1,15,2,3,3,\"Window change\",\"2019-2-3\",120000,\"2019-5-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(2,14,6,2,5,\"Bumper change\",\"2019-3-1\",400000,\"2019-6-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(3,14,8,2,10,\"Window change\",\"2019-4-3\",200000,\"2019-7-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(4,12,3,1,13,\"Window change\",\"2019-5-5\",100000,\"2019-8-1\",\"Nothing\");");


        sql.add("insert into Repair_List values(5,2,5,1,3,\"Window change\",\"2019-6-16\",300000,\"2019-5-11\",\"Nothing\");");
        sql.add("insert into Repair_List values(6,12,7,1,1,\"Bumper change\",\"2019-5-15\",100000,\"2019-8-14\",\"Nothing\");");
        sql.add("insert into Repair_List values(7,4,9,6,10,\"Bumper change\",\"2019-5-3\",130000,\"2019-7-13\",\"Nothing\");");

        sql.add("insert into Repair_List values(8,7,10,9,14,\"Window change\",\"2019-4-16\",400000,\"2019-8-21\",\"Nothing\");");
        sql.add("insert into Repair_List values(9,2,11,1,8,\"Window change\",\"2019-7-5\",300000,\"2019-9-11\",\"Nothing\");");
        sql.add("insert into Repair_List values(10,4,3,6,7,\"Window change\",\"2019-9-15\",200000,\"2019-7-21\",\"Nothing\");");

        sql.add("insert into Repair_List values(11,9,8,1,4,\"Window change\",\"2020-1-6\",120000,\"2019-8-4\",\"Nothing\");");
        sql.add("insert into Repair_List values(12,12,10,1,3,\"Bumper change\",\"2019-8-5\",1000000,\"2019-8-6\",\"Nothing\");");
        sql.add("insert into Repair_List values(13,9,1,1,12,\"Bumper change\",\"2019-7-18\",300000,\"2019-8-9\",\"Nothing\");");

        sql.add("insert into Repair_List values(14,13,2,14,4,\"Window change\",\"2019-9-6\",300000,\"2020-1-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(15,11,3,3,11,\"Window change\",\"2019-4-15\",100000,\"2020-1-2\",\"Nothing\");");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sql.clear();

    }


}
