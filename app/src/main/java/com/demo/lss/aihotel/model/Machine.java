package com.demo.lss.aihotel.model;

import zuo.biao.library.base.BaseModel;
import zuo.biao.library.util.StringUtil;

public class Machine extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String environment;     //工作环境

    /**默认构造方法，JSON等解析时必须要有
     */
    public Machine() {
        //default
    }
    public Machine(int id) {
        this();
        this.id = id;
    }
    public Machine(int id, String environment) {
        this(id);
        this.environment = environment;
    }

    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }

    @Override
    protected boolean isCorrect() {//根据自己的需求决定，也可以直接 return true
        return StringUtil.isNotEmpty(environment, true);
    }


}
