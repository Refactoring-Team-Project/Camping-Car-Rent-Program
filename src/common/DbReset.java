package common;

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
            e.printStackTrace();
        }


    }
    public static void initDB(Connection conn) {
        resetDB(conn);
        createTable(conn);

        PreparedStatement pstmt;
        Statement stmt;
        ArrayList<String> sql = new ArrayList<String>();

        sql.add("insert into camping_company values(1,\"abc컴퍼니\",\"서울특별시 강서구 염창동\",\"010-1234-0987\",\"김진아\",\"jina@naver.com\");");
        sql.add("insert into camping_company values(2,\"뉴에이지\",\"인천광역시 연수구 송도동\",\"010-4322-2354\",\"이우진\",\"woojin@naver.com\");");
        sql.add("insert into camping_company values(3,\"paper컴퍼니\",\"경기도 프라하시\",\"010-2134-2343\",\"김이나\",\"inna@naver.com\");");
        sql.add("insert into camping_company values(4,\"콜미컴퍼니\",\"경기도 수원시\",\"010-3426-8754\",\"송주우\",\"juwo@naver.com\");");
        sql.add("insert into camping_company values(5,\"뉴원컴퍼니\",\"제주특별시 제주동\",\"010-1237-4367\",\"안지아\",\"jia@naver.com\");");
        sql.add("insert into camping_company values(6,\"무사고컴퍼니\",\"대전광역시 노잼동\",\"010-2148-6534\",\"오아나\",\"ana@naver.com\");");
        sql.add("insert into camping_company values(7,\"사랑컴퍼니\",\"강원도 삼척시 동춘동\",\"010-2363-3126\",\"주비\",\"rain@yahoo.com\");");
        sql.add("insert into camping_company values(8,\"제주렌트\",\"강원도 춘천시 잠실동\",\"010-2354-2342\",\"마성민\",\"sungmin@gmail.com\");");
        sql.add("insert into camping_company values(9,\"블라디컴퍼니\",\"서울특별시 광진구 세종동\",\"010-2137-3478\",\"서지아\",\"jia123@naver.com\");");
        sql.add("insert into camping_company values(10,\"오호렌트\",\"서울특별시 강남동 건대동\",\"010-2138-4326\",\"임하리\",\"hari@naver.com\");");
        sql.add("insert into camping_company values(11,\"주지컴퍼니\",\"부산광역시 해운대구\",\"010-8738-4347\",\"주우주\",\"blackhole@naver.com\");");
        sql.add("insert into camping_company values(12,\"아에렌트\",\"부산광역시 목포구\",\"010-1238-5643\",\"이진아\",\"jinna@gmail.com\");");
        sql.add("insert into camping_company values(13,\"오이렌트\",\"서울특별시 뉴욕구\",\"010-6574-2346\",\"김성훈\",\"sunghun1998@naver.com\");");
        sql.add("insert into camping_company values(14,\"원츄렌트\",\"서울특별시 캘리포니아주\",\"010-3246-2745\",\"김효경\",\"hyo@naver.com\");");
        sql.add("insert into camping_company values(15,\"추노렌트\",\"인천광역시 말리부동\",\"010-2316-1235\",\"이우식\",\"sik@naver.com\");");

        sql.add("INSERT INTO camping_company VALUES (16, '김캠핑카', '서울시 노원구', '02-5463-5234', '김김김', 'kim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (17, '님캠핑카', '서울시 용산구', '02-8788-5287', '님님님', 'nim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (18, '딤캠핑카', '서울시 송파구', '02-5231-8798', '딤딤딤', 'dim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (19, '림캠핑카', '서울시 송파구', '02-2856-2374', '림림림', 'lim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (20, '밈캠핑카', '서울시 강남구', '02-8762-4567', '밈밈밈', 'mim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (21, '빔캠핑카', '서울시 강동구', '02-4568-8769', '빔빔빔', 'bim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (22, '심캠핑카', '서울시 송파구', '02-0988-8763', '심심심', 'sim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (23, '임캠핑카', '서울시 광진구', '02-4507-6780', '임임임', 'im@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (24, '짐캠핑카', '서울시 관악구', '02-4567-4652', '짐짐짐', 'jim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (25, '침캠핑카', '서울시 광진구', '02-5420-0547', '침침침', 'cim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (26, '킴캠핑카', '서울시 구로구', '02-4550-2507', '킴킴킴', 'kkim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (27, '팀캠핑카', '서울시 광진구', '02-5463-5465', '팀팀팀', 'tim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (28, '핌캠핑카', '서울시 강동구', '02-3078-5621', '핌핌핌', 'fim@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (29, '힘캠핑카', '서울시 강남구', '02-4560-4520', '힘힘힘', 'him@camp.com')");
        sql.add("INSERT INTO camping_company VALUES (30, '금캠핑카', '서울시 은평구', '02-4560-7800', '금금금', 'km@camp.com')");

        sql.add("insert into camping_company values(31, '포레스트 캠핑카', '서울 노원구', '02-933-0000', '김민수', 'minsu@naver.com');");
        sql.add("insert into camping_company values(32, '그린 캠핑카', '서울 중랑구', '02-936-0000', '박정현', 'parkjh@gmail.com');");
        sql.add("insert into camping_company values(33, '플레이 캠핑카', '경기 의정부시', '031-222-3333', '이승현', 'seunghyunlee@hanmail.net');");
        sql.add("insert into camping_company values(34, '투게더 캠핑카', '인천 남동구', '032-333-4444', '최나라', 'choiworld@naver.com');");
        sql.add("insert into camping_company values(35, '행복 캠핑카', '서울 강남구', '02-999-8888', '안성현', 'Annnsh@naver.com');");
        sql.add("insert into camping_company values(36, '케이원 캠핑카', '경기 남양주시', '031-932-7777', '이수빈', 'subini@gmail.com');");
        sql.add("insert into camping_company values(37, '피크닉 캠핑카', '서울 강서구 ', '02-936-2323', '조재관', 'jkcho@naver.com');");
        sql.add("insert into camping_company values(38, '파스텔 캠핑카', '인천 미추홀구', '032-222-1111', '이승준', 'junjuly@gmail.com');");
        sql.add("insert into camping_company values(39, ' 스타캠핑카', '경기 수원시 ', '031-111-2222', '박보검', 'bobo123@gmail.com');");
        sql.add("insert into camping_company values(40, '투어 캠핑카', '대구 달서구', '053-000-5555', '이동건', 'twodong@hanmail.net');");
        sql.add("insert into camping_company values(41, '아폴로 캠핑카', '충청남도 천안시', '041-222-3333', '공지철', 'gongong@gmail.com');");
        sql.add("insert into camping_company values(42, '미즈 캠핑카', '부산 동구 ', '051-555-1111', '한예슬', 'hanbeautiful@naver.com');");
        sql.add("insert into camping_company values(43, '시티 캠핑카', '강원도 원주시', '033-666-8888', '이진욱', 'leejinwook63@naver.com');");
        sql.add("insert into camping_company values(44, '프리덤 캠핑카', '강원도 강릉시', '033-222-1111', '김동욱', 'dongdong2@gmail.com');");
        sql.add("insert into camping_company values(45, '사랑 캠핑카', '전라남도 여수시', '061-111-8888', '김태희', 'nothuman@naver.com');");

        for(String s : sql) {
            try {
                pstmt = conn.prepareStatement(s);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sql.clear();

        sql.add("insert into camping_car values(1,\"붕붕이\",3241,6,\"현대\",2010,18000,70000,1,\"2012-12-02\");");
        sql.add("insert into camping_car values(2,\"씽씽이\",2312,4,\"현대\",2011,20000,60000,2,\"2012-11-21\");");
        sql.add("insert into camping_car values(3,\"하우우\",7653,2,\"기아\",2012,10000,60000,3,\"2013-03-02\");");
        sql.add("insert into camping_car values(4,\"티엠아이\",1234,8,\"포르쉐\",2010,12000,80000,4,\"2014-9-2\");");
        sql.add("insert into camping_car values(5,\"으르렁\",9384,8,\"기아\",2014,6000,60000,5,\"2015-2-3\");");
        sql.add("insert into camping_car values(6,\"호랑이\",4673,6,\"현대\",2012,12000,120000,6,\"2015-4-5\");");
        sql.add("insert into camping_car values(7,\"코끼리\",3924,4,\"폭스바겐\",2013,20000,90000,7,\"2014-7-4\");");
        sql.add("insert into camping_car values(8,\"상어\",9863,4,\"르노\",2011,3000,70000,8,\"2012-4-3\");");
        sql.add("insert into camping_car values(9,\"기린\",1324,6,\"람보르기니\",2009,7000,60000,9,\"2012-12-1\");");
        sql.add("insert into camping_car values(10,\"람보르기니\",4353,7,\"포르쉐\",2009,8000,80000,10,\"2011-2-3\");");
        sql.add("insert into camping_car values(11,\"영덕이\",1235,9,\"현대\",2014,20000,70000,11,\"2017-2-5\");");
        sql.add("insert into camping_car values(12,\"박스차\",4732,6,\"닛산\",2016,3000,80000,12,\"2018-12-3\");");
        sql.add("insert into camping_car values(13,\"레이\",3627,4,\"혼다\",2010,21000,90000,13,\"2011-12-3\");");
        sql.add("insert into camping_car values(14,\"아반떼\",3843,2,\"BMW\",2010,13000,110000,14,\"2011-4-7\");");
        sql.add("insert into camping_car values(15,\"소나타\",4273,7,\"기아\",2008,14000,90000,15,\"2010-4-5\");");

        sql.add("INSERT INTO camping_car VALUES (16, '궁궁이', 12345, 7, '궁제조회사', 2020, 10, 450000, 16,'2020-06-11')");
        sql.add("INSERT INTO camping_car VALUES (17, '눙눙이', 89645, 4, '눙제조회사', 2017, 80, 280000, 17,'2017-05-11')");
        sql.add("INSERT INTO camping_car VALUES (18, '둥둥이', 56423, 5, '궁제조회사', 2018, 28, 350000, 18,'2018-05-18')");
        sql.add("INSERT INTO camping_car VALUES (19, '룽룽이', 98635, 4, '눙제조회사', 2015, 180, 250000, 19,'2016-06-11')");
        sql.add("INSERT INTO camping_car VALUES (20, '뭉뭉이', 64832, 3, '궁제조회사', 2018, 72, 380000, 20,'2019-01-11')");
        sql.add("INSERT INTO camping_car VALUES (21, '붕붕이', 68763, 5, '궁제조회사', 2019, 165, 260000, 21, '2020-01-01')");
        sql.add("INSERT INTO camping_car VALUES (22, '숭숭이', 67854, 8, '눙제조회사', 2020, 28, 380000, 22, '2020-05-15')");
        sql.add("INSERT INTO camping_car VALUES (23, '웅웅이', 21374, 4, '눙제조회사', 2018, 84, 460000, 23, '2018-06-11')");
        sql.add("INSERT INTO camping_car VALUES (24, '중중이', 54678, 5, '눙제조회사', 2017, 186, 310000, 24, '2018-02-23')");
        sql.add("INSERT INTO camping_car VALUES (25, '충충이', 34525, 6, '궁제조회사', 2019, 97, 410000, 25, '2019-08-16')");
        sql.add("INSERT INTO camping_car VALUES (26, '쿵쿵이', 23864, 5, '눙제조회사', 2017, 45, 270000, 26, '2107-12-11')");
        sql.add("INSERT INTO camping_car VALUES (27, '퉁퉁이', 54686, 4, '궁제조회사', 2019, 28, 640000, 27, '2020-02-12')");
        sql.add("INSERT INTO camping_car VALUES (28, '풍풍이', 86731, 8, '궁제조회사', 2020, 5, 480000, 28, '2020-03-03')");
        sql.add("INSERT INTO camping_car VALUES (29, '훙훙이', 23154, 6, '궁제조회사', 2017, 45, 590000, 29, '2017-05-01')");
        sql.add("INSERT INTO camping_car VALUES (30, '공공이', 68765, 5, '눙제조회사', 2018, 34, 560000, 30, '2018-10-05')");

        sql.add("insert into camping_car VALUES(31, 'RAY18', '2942',2, 'KIA', 2018, 28000, 170000, 31, '2018-07-01');");
        sql.add("insert into camping_car VALUES(32, '포터2', '4824', 5, '현대', 2020, 8000, 270000, 32, '2020-03-04');");
        sql.add("insert into camping_car VALUES(33, 'T5', '5902', 7, '폭스바겐', 2019, 16000, 190000, 33, '2019-06-01');");
        sql.add("insert into camping_car VALUES(34, 'A7', '1934', 20, '아우디', 2017, 35000, 250000, 34, '2017-12-08');");
        sql.add("insert into camping_car VALUES(35, '마스터19', '9908', 5, '르노', 2019, 13000, 160000, 35, '2019-02-22');");
        sql.add("insert into camping_car VALUES(36, '코란도', '8837', 10, '쌍용', 2016, 44000, 300000, 36, '2016-06-03');");
        sql.add("insert into camping_car VALUES(37, 'RAY16', '1812', 4, 'KIA', 2016, 52000, 230000, 37, '2016-08-10');");
        sql.add("insert into camping_car VALUES(38, 'A9', '2214', 25, '아우디', 2019, 12000, 180000, 38, '2019-09-24');");
        sql.add("insert into camping_car VALUES(39, '마스터19', '8293', 7, '르노', 2019, 19000, 200000, 39, '2019-10-05');");
        sql.add("insert into camping_car VALUES(40, 'T6', '2014', 9, '폭스바겐', 2020, 9000, 150000, 40, '2020-01-05');");
        sql.add("insert into camping_car VALUES(41, '렉스턴', '4492', 5, '쌍용', 2018, 29000, 190000, 41, '2018-02-06');");
        sql.add("insert into camping_car VALUES(42, '포터2', '5666', 10, '현대', 2017, 36000, 210000, 42, '2017-03-26');");
        sql.add("insert into camping_car VALUES(43, 'A7', '3611', 11, '아우디', 2018, 29000, 170000, 43, '2018-02-09');");
        sql.add("insert into camping_car VALUES(44, 'RAY19', '2298', 25, 'KIA', 2019, 17000, 160000, 44, '2019-07-21');");
        sql.add("insert into camping_car VALUES(45, '마스터18', '1222', 7, '르노', 2018, 49000, 200000, 45, '2018-05-13');");
        sql.add("insert into camping_car VALUES(46, 'T4', '3211', 9, '폭스바겐', 2014, 56000, 240000, 1, '2014-04-29');");
        sql.add("insert into camping_car VALUES(47, 'A7', '5883', 20, '아우디', 2017, 33000, 150000, 2, '2017-11-06');");
        sql.add("insert into camping_car VALUES(48, 'RAY15', '4881', 2, 'KIA', 2015, 52000, 270000, 3, '2015-12-12');");
        sql.add("insert into camping_car VALUES(49, 'A6', '8399', 5, '아우디', 2016, 49000, 250000, 4, '2016-06-19');");
        sql.add("insert into camping_car VALUES(50, 'RAY15', '4408', 21, 'KIA', 2015, 58000, 230000, 5, '2015-03-17');");
        sql.add("insert into camping_car VALUES(51, '코란도', '2186', 11, '쌍용', 2020, 10000, 190000, 6, '2020-11-20');");
        sql.add("insert into camping_car VALUES(52, 'A9', '6782', 2, '아우디', 2019, 11000, 160000, 7, '2019-07-18');");
        sql.add("insert into camping_car VALUES(53, '포터2', '2902', 7, '현대', 2016, 45000, 210000, 8, '2016-08-17');");
        sql.add("insert into camping_car VALUES(54, 'RAY13', '9572', 10, 'KIA', 2013, 51000, 300000, 9, '2013-07-21');");
        sql.add("insert into camping_car VALUES(55, '마스터19', '8888', 4, '르노', 2020, 54000, 290000, 10, '2020-04-11');");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Rent_Customer values(1,\"김성훈\",\"인천광역시 연수구 송도동\",\"010-9657-8412\",\"sunghun1998@naver.com\");");
        sql.add("insert into Rent_Customer values(2,\"김효경\",\"서울특별시 성동구 뉴욕동\",\"010-1234-1234\",\"hyobbang@naver.com\");");
        sql.add("insert into Rent_Customer values(3,\"이우식\",\"강원도 삼척시\",\"010-3246-4321\",\"sik@naver.com\");");
        sql.add("insert into Rent_Customer values(4,\"이진아\",\"서울특별시 엘에이주\",\"010-1237-4363\",\"jinna@naver.com\");");
        sql.add("insert into Rent_Customer values(5,\"우리나\",\"인천광역시 오하이오주\",\"010-2365-4364\",\"rina@naver.com\");");
        sql.add("insert into Rent_Customer values(6,\"황소진\",\"인천광역시 미시간주\",\"010-2374-3765\",\"sojin@naver.com\");");
        sql.add("insert into Rent_Customer values(7,\"우아나\",\"서울특별시 도쿄구\",\"010-2378-3263\",\"anaa@naver.com\");");
        sql.add("insert into Rent_Customer values(8,\"하나경\",\"제주특별시 감귤주\",\"010-4263-4364\",\"nakyung@yahoo.com\");");
        sql.add("insert into Rent_Customer values(9,\"이민지\",\"대전광역시 수정구\",\"010-4236-3263\",\"minji@naver.com\");");
        sql.add("insert into Rent_Customer values(10,\"이소진\",\"부산광역시 해운대구\",\"010-4327-3425\",\"sojin@gmail.com\");");
        sql.add("insert into Rent_Customer values(11,\"장광진\",\"광주광역시 기아동\",\"010-3248-3474\",\"gang@naver.com\");");
        sql.add("insert into Rent_Customer values(12,\"김자아\",\"경상북도 창원시\",\"010-2348-7543\",\"jaa@gmail.com\");");
        sql.add("insert into Rent_Customer values(13,\"빅비빅\",\"경기도 수원시\",\"010-4328-5465\",\"bibibik@naver.com\");");
        sql.add("insert into Rent_Customer values(14,\"진진아\",\"경기도 과천시\",\"010-3248-4374\",\"jinjin@naver.com\");");
        sql.add("insert into Rent_Customer values(15,\"노진구\",\"강원도 동해시\",\"010-3428-4537\",\"jingu@yahoo.com\");");

        sql.add("INSERT INTO Rent_Customer VALUES (16, '신희연', '서울시 송파구', '010-1234-5678', 'yoni@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (17, '신민경', '서울시 광진구', '010-9090-2929', 'ming@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (18, '이익준', '서울시 은평구', '010-5676-4567', 'ik@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (19, '안정원', '서울시 강서구', '010-4567-7685', 'jeong1@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (20, '김준완', '서울시 양천구', '010-3534-5468', 'junwan@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (21, '양석형', '서울시 구로구', '010-9852-3154', 'seok@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (22, '채송화', '서울시 금천구', '010-8685-5254', 'ssong@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (23, '정로사', '서울시 관악구', '010-1244-1234', 'rosa@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (24, '주종수', '서울시 동작구', '010-6388-5678', 'bell@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (25, '도재학', '서울시 강남구', '010-3653-3245', 'dojae@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (26, '용석민', '서울시 강동구', '010-5345-3278', 'stone@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (27, '장겨울', '서울시 중랑구', '010-2002-6465', 'winter@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (28, '안치홍', '서울시 도봉구', '010-2344-5464', 'anchi@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (29, '봉광현', '서울시 노원구', '010-0057-7684', 'light@sjcamp.com')");
        sql.add("INSERT INTO Rent_Customer VALUES (30, '추민하', '서울시 용산구', '010-4578-5642', 'fall@sjcamp.com')");

        sql.add("insert into Rent_Customer VALUES(31, '조유나', '서울 노원구', '010-5000-8777', 'choyn@naver.com');");
        sql.add("insert into Rent_Customer VALUES(32, '신민경', '강원도 춘천시', '010-6666-7777', 'shinming@gmail.com');");
        sql.add("insert into Rent_Customer VALUES(33, '신희연', '서울시 송파구', '010-2333-1111', 'hyshin@naver.com');");
        sql.add("insert into Rent_Customer VALUES(34, '김민수', '인천 남동구', '010-3434-0000', 'minsukim@hanmail.net');");
        sql.add("insert into Rent_Customer VALUES(35, '홍성철', '충청남도 아산시', '010-1212-1111', 'honghong@naver.com');");
        sql.add("insert into Rent_Customer VALUES(36, '박은정', '서울 강서구', '010-4545-2222', 'eundong@gmail.com');");
        sql.add("insert into Rent_Customer VALUES(37, '허정민', '서울 강남구', '010-2323-9999', 'huhhuh@gmail.com');");
        sql.add("insert into Rent_Customer VALUES(38, '정지호', '서울 광진구', '010-7878-0000', 'jihohoho@hanmail.net');");
        sql.add("insert into Rent_Customer VALUES(39, '김준호', '경기도 수원시', '010-6767-3333', 'junhohoho@naver.com');");
        sql.add("insert into Rent_Customer VALUES(40, '정예원', '서울 도봉구', '010-3888-3999', 'yeahone@naver.com');");
        sql.add("insert into Rent_Customer VALUES(41, '박소영', '경기도 의정부시', '010-5656-3838', 'romii@gmail.com');");
        sql.add("insert into Rent_Customer VALUES(42, '김지호', '서울 노원구', '010-7898-0000', 'jjihooo@naver.com');");
        sql.add("insert into Rent_Customer VALUES(43, '최순영', '서울 강서구', '010-2580-0000', 'sunsun0@hanmail.net');");
        sql.add("insert into Rent_Customer VALUES(44, '이승빈', '경기도 남양주시', '010-9090-8080', 'bean123@naver.com');");
        sql.add("insert into Rent_Customer VALUES(45, '임서정', '서울 강동구', '010-3444-3444', 'sjlim@naver.com');");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.clear();

        sql.add("insert into Car_Rent values(1,1,1,1,\"2019-1-23\",3,180000,\"2019-2-1\", \"X\",0);");
        sql.add("insert into Car_Rent values(2,2,2,2,\"2019-8-2\",4,220000,\"2019-8-11\",\"유류비\",20000);");
        sql.add("insert into Car_Rent values(3,3,3,3,\"2019-4-3\",2,120000,\"2019-4-16\",\"X\",0);");
        sql.add("insert into Car_Rent values(4,4,4,4,\"2019-5-2\",3,150000,\"2019-5-17\",\"사고\",200000);");
        sql.add("insert into Car_Rent values(5,5,5,5,\"2019-3-1\",1,70000,\"2019-3-11\",\"유류비\",30000);");
        sql.add("insert into Car_Rent values(6,6,6,6,\"2019-2-22\",2,120000,\"2019-3-2\",\"X\",0);");
        sql.add("insert into Car_Rent values(7,7,7,7,\"2019-7-5\",7,420000,\"2019-7-17\",\"과속\",30000);");
        sql.add("insert into Car_Rent values(8,8,8,8,\"2018-12-22\",4,200000,\"2019-1-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(9,9,9,9,\"2019-12-20\",3,180000,\"2020-1-3\",\"라면\",6000);");
        sql.add("insert into Car_Rent values(10,10,10,10,\"2019-11-22\",3,180000,\"2019-12-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(11,11,11,11,\"2019-6-3\",3,210000,\"2019-6-16\",\"유류비\",40000);");
        sql.add("insert into Car_Rent values(12,12,12,12,\"2020-1-22\",3,180000,\"2020-2-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(13,13,13,13,\"2019-8-21\",5,250000,\"2019-9-1\",\"아이스크림\",10000);");
        sql.add("insert into Car_Rent values(14,14,14,14,\"2019-12-2\",3,180000,\"2019-12-11\",\"X\",0);");
        sql.add("insert into Car_Rent values(15,15,15,15,\"2019-6-24\",2,120000,\"2019-7-1\",\"X\",0);");
        sql.add("insert into Car_Rent values(16,16,16,16,\"2019-7-04\",3,125000,\"2019-7-10\",\"X\",0);");
        sql.add("insert into Car_Rent values(17,17,17,17,\"2019-5-20\",5,280000,\"2019-6-1\",\"과속\",50000);");
        sql.add("insert into Car_Rent values(18,18,18,18,\"2019-1-3\",2,100000,\"2019-1-15\",\"X\",0);");
        sql.add("insert into Car_Rent values(19,19,19,19,\"2020-6-14\",6,250000,\"2020-6-30\",\"X\",0);");
        sql.add("insert into Car_Rent values(20,20,20,20,\"2019-9-15\",8,170000,\"2019-9-21\",\"주차비\",8000);");
        sql.add("insert into Car_Rent values(21,21,21,21,\"2020-3-17\",4,60000,\"2020-3-25\",\"X\",0);");

        sql.add("INSERT INTO Car_Rent VALUES (22, 22, 22, 22, '2019-10-05', 10, 780000, '2019-10-20', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (23, 23, 23, 23, '2018-10-05', 8, 580000, '2018-10-18', '차 긁힘', 150000)");
        sql.add("INSERT INTO Car_Rent VALUES (24, 24, 24, 24, '2020-05-05', 21, 1200000, '2020-05-20', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (25, 25, 25, 25, '2019-05-19', 7, 780000, '2019-06-05', '유리 깨짐', 540000)");
        sql.add("INSERT INTO Car_Rent VALUES (26, 26, 26, 26, '2018-09-18', 10, 640000, '2018-09-30', '범퍼 긁힘', 670000)");
        sql.add("INSERT INTO Car_Rent VALUES (27, 27, 27, 27, '2020-03-03', 2, 350000, '2020-03-18', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (28, 28, 28, 28, '2019-08-13', 4, 680000, '2019-08-28', '타이어 펑크', 250000)");
        sql.add("INSERT INTO Car_Rent VALUES (29, 29, 29, 29, '2020-03-04', 18, 973000, '2020-03-30', '스크래치', 50000)");
        sql.add("INSERT INTO Car_Rent VALUES (30, 30, 30, 30, '2018-12-05', 31, 1340000, '2018-12-20', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (31, 31, 31, 31, '2020-04-20', 8, 420000, '2020-05-01', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (32, 32, 32, 32, '2018-09-18', 10, 870000, '2018-09-24', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (33, 33, 33, 33, '2019-10-27', 8, 540000, '2019-11-03', '청구내역없음', 0)");
        sql.add("INSERT INTO Car_Rent VALUES (34, 34, 34, 34, '2020-03-03', 6, 640000, '2020-03-18', '타이어 펑크', 240000)");
        sql.add("INSERT INTO Car_Rent VALUES (35, 35, 35, 35, '2018-07-08', 10, 770000, '2018-07-21', '청구내역없음', 0)");

        sql.add("insert into Car_Rent VALUES(36, 36, 36, 36, '2020-01-08', 1, 170000, '2020-01-10', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(37, 37, 37, 37, '2020-01-18', 3, 690000, '2020-01-24', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(38, 38, 38, 38, '2020-01-20', 4, 680000, '2020-01-28', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(39, 39, 39, 39, '2020-02-01', 10, 1500000, '2020-02-21', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(40, 40, 40, 40, '2020-02-22', 2, 540000, '2020-02-26', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(41, 41, 41, 41, '2020-03-14', 2, 380000, '2020-03-18', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(42, 42, 42, 42, '2020-03-17', 3, 570000, '2020-03-23', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(43, 43, 43, 43, '2020-03-21', 5, 900000, '2020-03-31', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(44, 44, 44, 44, '2020-03-26', 2, 580000, '2020-03-30', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(45, 45, 45, 45, '2020-04-05', 3, 450000, '2020-04-11', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(46, 46, 1, 1, '2020-05-13', 1, 300000, '2020-05-15', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(47, 47, 2, 2, '2020-05-15', 3, 630000, '2020-05-21', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(48, 48, 3, 3, '2020-05-18', 2, 320000, '2020-05-22', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(49, 49, 4, 4, '2020-05-24', 5, 800000, '2020-06-04', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(50, 50, 5, 5, '2020-06-03', 2, 540000, '2020-06-07', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(51, 51, 6, 6, '2020-06-06', 1, 540000, '2020-06-08', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(52, 52, 7, 7, '2020-06-09', 3, 540000, '2020-06-15', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(53, 53, 8, 8, '2020-06-11', 3, 540000, '2020-06-17', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(54, 54, 9, 9, '2020-06-14', 2, 540000, '2020-06-18', 'X', 0);");
        sql.add("insert into Car_Rent VALUES(55, 55, 10, 10, '2020-06-20', 1, 540000, '2020-06-22', 'X', 0);");


        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.clear();

        sql.add("insert into Car_Check values(1,1,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(2,2,\"Clear\",\"Door scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(3,3,\"Bumber scratch\",\"Door scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(4,4,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(5,5,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(6,6,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(7,7,\"Clear\",\"Window scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(8,8,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(9,9,\"Clear\",\"Clear\",\"Door scratch\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(10,10,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(15,15,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(12,12,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(13,13,\"Bumber Broken\",\"Window scratch\",\"Clear\",\"Clear\",\"Y\");");
        sql.add("insert into Car_Check values(14,14,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");
        sql.add("insert into Car_Check values(16,16,\"Clear\",\"Clear\",\"Clear\",\"Clear\",\"N\");");

        sql.add("INSERT INTO Car_Check VALUES (11, 11, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (17, 17, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (18, 18, 'Bumber Broken', 'Clear', 'Clear', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (19, 19, 'Bumber Broken', 'Clear', 'Clear', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (20, 20, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (21, 21, 'Clear', 'Window scratch', 'Clear', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (22, 22, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (23, 23, 'Clear', 'Window scratch', 'Clear', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (24, 24, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (25, 25, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (26, 26, 'Clear', 'Window scratch', 'Clear', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (27, 27, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("INSERT INTO Car_Check VALUES (28, 28, 'Clear', 'Clear', 'Door scratch', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (29, 29, 'Clear', 'Clear', 'Door scratch', 'Clear', 'Y')");
        sql.add("INSERT INTO Car_Check VALUES (30, 30, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");

        sql.add("insert into Car_Check values(31, 31, 'Clear', '손잡이 깨짐', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(32, 32, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("insert into Car_Check values(33, 33, 'Clear', 'Clear', 'Clear', '트렁크 안닫힘', 'Y')");
        sql.add("insert into Car_Check values(34, 34, 'Clear', 'Clear', '조수석 창문 깨짐', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(35, 35, '와이퍼 고장남', 'Clear', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(36, 36, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("insert into Car_Check values(37, 37, 'Clear', '운전석 손잡이 밑부분 긁혀서 도색 벗겨짐', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(38, 38, 'Clear', 'Clear', '사이드 미러 깨짐', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(39, 39, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("insert into Car_Check values(40, 40, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("insert into Car_Check values(41, 41, 'Clear', 'Clear', 'Clear', '뒷범퍼 찌그러짐', 'Y')");
        sql.add("insert into Car_Check values(42, 42, 'Clear', 'Clear', 'Clear', 'Clear', 'N')");
        sql.add("insert into Car_Check values(43, 43, 'Clear', '사이드 미러 부러짐', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(44, 44, 'Clear', '발 받침대 찌그러짐', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(45, 45, '왼쪽 라이트 깨짐', 'Clear', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(46, 46, 'Clear', 'Clear', '운전석 창문 작동 안됨', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(47, 47, '보닛 찌그러짐', 'Clear', 'Clear', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(48, 48, 'Clear', 'Clear', 'Clear', '번호판 떨어짐', 'Y')");
        sql.add("insert into Car_Check values(49, 49, 'Clear', '뒤 타이어 펑크', '뒤 타이어 펑크', 'Clear', 'Y')");
        sql.add("insert into Car_Check values(50, 50, 'Clear', '주유구 고장', 'Clear', 'Clear', 'Y')");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
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

        sql.add("INSERT INTO Repairshop VALUES (16, '감정비소', '서울시 동작구', '02-2164-4645', '감감감', 'kam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (17, '남정비소', '서울시 강서구', '02-6546-8462', '남남남', 'nam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (18, '담정비소', '서울시 동작구', '02-2164-4645', '담담담', 'dam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (19, '람정비소', '서울시 서초구', '02-2041-4562', '람람람', 'ram@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (20, '맘정비소', '서울시 관악구', '02-5421-7861', '맘맘맘', 'mam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (21, '밤정비소', '서울시 노원구', '02-2156-8047', '밤밤밤', 'bam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (22, '삼정비소', '서울시 도봉구', '02-1235-4645', '삼삼삼', 'sam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (23, '암정비소', '서울시 강동구', '02-5264-1635', '암암암', 'am@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (24, '잠정비소', '서울시 송파구', '02-2137-9845', '잠잠잠', 'jam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (25, '참정비소', '서울시 광진구', '02-8572-6110', '참참참', 'cham@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (26, '캄정비소', '서울시 성북구', '02-5167-2342', '캄캄캄', 'cam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (27, '탐정비소', '서울시 동작구', '02-2345-7378', '탐탐탐', 'tam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (28, '팜정비소', '서울시 은평구', '02-2487-7821', '팜팜팜', 'pam@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (29, '함정비소', '서울시 마포구', '02-2264-1345', '함함함', 'ham@sjgar.com')");
        sql.add("INSERT INTO Repairshop VALUES (30, '검정비소', '서울시 강서구', '02-5464-8701', '검검검', 'kum@sjgar.com')");

        sql.add("insert into Repairshop values(31, '모터스 정비소', '서울 서초구', '02-1111-0000', '김정현', 'hyunii@naver.com');");
        sql.add("insert into Repairshop values(32, '애니카 정비소', '경기 의정부시', '031-2222-1111', '김재환', 'kimjae@gmail.com');");
        sql.add("insert into Repairshop values(33, '그린카 정비소', '서울 강동구', '02-3333-4444', '안지영', 'jiiyoung@gmail.com');");
        sql.add("insert into Repairshop values(34, '하이카 정비소', '서울 중랑구', '02-4444-5555', '이지은', 'dlwldms@naver.com');");
        sql.add("insert into Repairshop values(35, '카포스 정비소', '인천 남동구', '032-2222-6666', '박승우', 'qkrtmddn@hanmail.net');");
        sql.add("insert into Repairshop values(36, ' 카앤텍 정비소', '서울 성동구', '032-2222-6666', '최승준', 'tmdwns@hanmail.net');");
        sql.add("insert into Repairshop values(37, '보람 정비소', '인천 남동구', '032-1111-9999', '임지윤', 'limjiji@hanmail.net');");
        sql.add("insert into Repairshop values(38, '블루핸즈 정비소', '경기 남양주시', '031 8888-1111', '이민욱', 'wook2@hanmail.net');");
        sql.add("insert into Repairshop values(39, '유림 정비소', '대구 서구', '053-2222-0000', '안동윤', 'dong939@hanmail.net');");
        sql.add("insert into Repairshop values(40, '차박사 정비소', '충청남도 아산', '041-3333-6666', '신지민', 'shinzimin@hanmail.net');");
        sql.add("insert into Repairshop values(41, '세븐자동차 정비소', '전라남도 여수시', '061-1111-0000', '김영진', 'yjkim@hanmail.net');");
        sql.add("insert into Repairshop values(42, '세종 정비소', '강원도 원주시', '033-1212-2323', '강지원', 'kangji1@hanmail.net');");
        sql.add("insert into Repairshop values(43, '프라자 정비소', '부산 서구', '051-1111-5555', '이찬원', 'leechan1@hanmail.net');");
        sql.add("insert into Repairshop values(44, '킹콩 정비소', '강원도 원주시', '033-977-6666', '장민호', 'jangminho23@hanmail.net');");
        sql.add("insert into Repairshop values(45, '올카 정비소', '경기 수원시', '031-2222-3333', '임영웅', 'limhero@hanmail.net');");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.clear();


        sql.add("insert into Repair_List values(1,1,1,1,1,\"Window change\",\"2019-2-3\",120000,\"2019-5-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(2,2,2,2,2,\"Bumper change\",\"2019-3-1\",400000,\"2019-6-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(3,3,3,3,3,\"Window change\",\"2019-4-3\",200000,\"2019-7-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(4,4,4,4,4,\"Window change\",\"2019-5-5\",100000,\"2019-8-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(5,5,5,5,5,\"Window change\",\"2019-6-16\",300000,\"2019-5-11\",\"Nothing\");");
        sql.add("insert into Repair_List values(6,6,6,6,6,\"Bumper change\",\"2019-5-15\",100000,\"2019-8-14\",\"Nothing\");");
        sql.add("insert into Repair_List values(7,7,7,7,7,\"Bumper change\",\"2019-5-3\",130000,\"2019-7-13\",\"Nothing\");");
        sql.add("insert into Repair_List values(8,8,8,8,8,\"Window change\",\"2019-4-16\",400000,\"2019-8-21\",\"Nothing\");");
        sql.add("insert into Repair_List values(9,9,9,9,9,\"Window change\",\"2019-7-5\",300000,\"2019-9-11\",\"Nothing\");");
        sql.add("insert into Repair_List values(10,10,10,10,10,\"Window change\",\"2019-9-15\",200000,\"2019-7-21\",\"Nothing\");");
        sql.add("insert into Repair_List values(11,11,11,11,11,\"Window change\",\"2020-1-6\",120000,\"2019-8-4\",\"Nothing\");");
        sql.add("insert into Repair_List values(12,12,12,12,12,\"Bumper change\",\"2019-8-5\",1000000,\"2019-8-6\",\"Nothing\");");
        sql.add("insert into Repair_List values(13,13,13,13,13,\"Bumper change\",\"2019-7-18\",300000,\"2019-8-9\",\"Nothing\");");
        sql.add("insert into Repair_List values(14,14,14,14,14,\"Window change\",\"2019-9-6\",300000,\"2020-1-1\",\"Nothing\");");
        sql.add("insert into Repair_List values(15,15,15,15,15,\"Window change\",\"2019-4-15\",100000,\"2020-1-2\",\"Nothing\");");

        sql.add("INSERT INTO Repair_List VALUES (16, 16, 16, 16, 16, '타이어 교체', '2020-06-15', 132500, '2020-06-18', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (17, 17, 17, 17, 17, '유리 교체', '2017-10-20', 564000, '2017-10-30', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (18, 18, 18, 18, 18, '엔진 교체', '2019-05-10', 135100, '2019-05-20', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (19, 19, 19, 19, 19, '범퍼 교체', '2017-06-11', 216200, '2017-06-21', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (20, 20, 20, 20, 20, '타이어 교체', '2020-01-11', 216200, '2020-01-21', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (21, 21, 21, 21, 21, '타이어 교체', '2020-05-01', 210600, '2020-05-11', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (27, 27, 27, 27, 27, '엔진 교체', '2020-06-15', 216310, '2020-06-25', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (28, 28, 28, 28, 28, '유리 교체', '2019-06-11', 123000, '2019-06-21', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (29, 29, 29, 29, 29, '유리 교체', '2020-02-23', 321600, '2020-03-03', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (30, 30, 30, 30, 30, '범퍼 교체', '2019-12-16', 130000, '2019-12-26', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (26, 26, 26, 26, 26, '엔진 교체', '2018-12-11', 213000, '2018-12-21', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (22, 22, 22, 22, 22, '내부 수리', '2020-05-12', 234500, '2020-05-22', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (23, 23, 23, 23, 23, '네비게이션 수리', '2020-04-03', 130000, '2020-04-13', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (24, 24, 24, 24, 24, '내부 수리', '2019-05-01', 210000, '2019-05-11', '없음')");
        sql.add("INSERT INTO Repair_List VALUES (25, 25, 25, 25, 25, '엔진 교체', '2019-10-05', 312000, '2019-10-15', '없음')");

        sql.add("insert into Repair_List values(31, 31, 31, 31, 31, 'Window change', '2020-01-10', 200000, '2020-01-13', 'Nothing');");
        sql.add("insert into Repair_List values(32, 32, 32, 32, 32, 'Bumper', '2020-01-25', 150000, '2020-01-27', 'Nothing');");
        sql.add("insert into Repair_List values(33, 33, 33, 33, 33, 'Window change', '2020-02-12', 500000, '2020-02-18', 'Nothing');");
        sql.add("insert into Repair_List values(34, 34, 34, 34, 34, 'Bumper', '2020-02-25', 320000, '2020-03-01', 'Nothing');");
        sql.add("insert into Repair_List values(35, 35, 35, 35, 35, 'Window change', '2020-03-21', 160000, '2020-03-23', 'Nothing');");
        sql.add("insert into Repair_List values(36, 36, 36, 36, 36, 'Bumper', '2020-03-27', 240000, '2020-03-30', 'Nothing');");
        sql.add("insert into Repair_List values(37, 37, 37, 37, 37, 'Bumper', '2020-05-15', 550000, '2020-05-21', 'Nothing');");
        sql.add("insert into Repair_List values(38, 38, 38, 38, 38, 'Window change', '2020-05-21', 230000, '2020-05-24', 'Nothing');");
        sql.add("insert into Repair_List values(39, 39, 39, 39, 39, 'Bumper', '2020-05-30', 110000, '2020-06-02', 'Nothing');");
        sql.add("insert into Repair_List values(40, 40, 40, 40, 40, 'Bumper', '2020-06-06', 360000, '2020-06-10', 'Nothing');");
        sql.add("insert into Repair_List values(41, 41, 41, 41, 41, 'Window change', '2020-06-08', 340000, '2020-06-12', 'Nothing');");
        sql.add("insert into Repair_List values(42, 42, 42, 42, 42, 'Bumper', '2020-06-13', 80000, '2020-06-14', 'Nothing');");
        sql.add("insert into Repair_List values(43, 43, 43, 43, 43, 'Window change', '2020-06-15', 200000, '2020-06-18', 'Nothing');");
        sql.add("insert into Repair_List values(44, 44, 44, 44, 44, 'Bumper', '2020-06-17', 180000, '2020-06-19', 'Nothing');");
        sql.add("insert into Repair_List values(45, 45, 45, 45, 45, 'Window change', '2020-06-22', 100000, '2020-06-24', 'Nothing');");

        try {
            stmt = conn.createStatement();
            for(String s:sql) {
                stmt.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.clear();

    }


}