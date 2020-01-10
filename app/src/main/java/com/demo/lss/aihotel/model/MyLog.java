package com.demo.lss.aihotel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import zuo.biao.library.base.BaseModel;
import zuo.biao.library.util.StringUtil;

public class MyLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    public static final int SEX_MAIL = 0;
    public static final int SEX_FEMALE = 1;
    public static final int SEX_UNKNOWN = 2;

    private Machine machine;
    private String userName;
    private int userSex;
    private Boolean userStar;
    private String date;

    /**默认构造方法，JSON等解析时必须要有
     */
    public MyLog() {
        machine = new Machine();
        //default
    }
    public MyLog(Machine machine,String userName,int userSex,Boolean userStar) {
        this();
        this.machine = machine;
        this.userName = userName;
        this.userSex = userSex;
        this.userStar = userStar;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");//可以方便地修改日期格式
        this.date = dateFormat.format( now );
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public Boolean getUserStar() {
        return userStar;
    }

    public void setUserStar(Boolean userStar) {
        this.userStar = userStar;
    }

    public long getId() {
        return this.machine.getId();
    }

    public void setId(int Id) {
        this.machine.setId(Id);
    }

    @Override
    protected boolean isCorrect() {
        return false;
    }
}
