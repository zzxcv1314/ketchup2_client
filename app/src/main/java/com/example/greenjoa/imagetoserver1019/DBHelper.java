package com.example.greenjoa.imagetoserver1019;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2017-12-15.
 */

public class DBHelper extends SQLiteOpenHelper {
    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 PERSONALINFO이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE PERSONALINFO (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lunchsite TEXT, bussite1 TEXT, bussite2 TEXT, rice INTEGER, hphone TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String lunchsite, String bussite1, int riceflag, String phone) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO PERSONALINFO VALUES(null, '" + name + "', '" + lunchsite + "', '" + bussite1 + "', '"  + "', " + riceflag + ",'" + phone + "');");
        db.close();
    }

    //입력된 이름과 동일한 이름의 행의 번호를 가져옴
    public int getName(String a) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int cursorposition = -1;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONALINFO", null);
        while (cursor.moveToNext()) {
            if (a.equals(cursor.getString(1))) {
                cursorposition = cursor.getPosition();
                break;
            } else {
                continue;
            }
        }
        return cursorposition;  //일치하는 이름이 없으면 음수값-1을 리턴하게된다.
    }

    public String getResult1(int a) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONALINFO", null);
        while (cursor.moveToNext()) {
            if (cursor.getPosition() == a) {
                result += cursor.getString(2)
                        + " \n ";
            } else
                continue;
        }

        return result;
    }

    public String getResult2(int a) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONALINFO", null);
        while (cursor.moveToNext()) {
            if (cursor.getPosition() == a) {
                result += cursor.getString(3)
                        + " \n ";
            } else
                continue;
        }

        return result;
    }

    public String getResult3(int a) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONALINFO", null);
        while (cursor.moveToNext()) {
            if (cursor.getPosition() == a) {
                result += cursor.getString(4)
                        + " \n ";
            } else
                continue;
        }

        return result;
    }

    public String getResult4(int a) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONALINFO", null);
        while (cursor.moveToNext()) {
            if (cursor.getPosition() == a) {
                result += cursor.getString(5)
                        + " \n ";
            } else
                continue;
        }

        return result;
    }
}