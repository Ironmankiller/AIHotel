package com.demo.lss.aihotel.application;

import android.util.Log;

import com.demo.lss.aihotel.manager.DataManager;
import com.demo.lss.aihotel.model.Machine;

import zuo.biao.library.base.BaseApplication;
import zuo.biao.library.util.StringUtil;

public class AIHotelApplication extends BaseApplication {
    private static final String TAG = "AIHotelApplication";

    private static AIHotelApplication context;
    public static AIHotelApplication getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }


    /**获取当前机器id
     * @return
     */
    public long getCurrentMechineId() {
        currentMachine = getCurrentMechine();
        Log.d(TAG, "getCurrentMechineId  currentMechineId = " + (currentMachine == null ? "null" : currentMachine.getId()));
        return currentMachine == null ? 0 : currentMachine.getId();
    }

    private static Machine currentMachine = null;
    public Machine getCurrentMechine() {
        if (currentMachine == null) {
            currentMachine = DataManager.getInstance().getCurrentMachine();
        }
        return currentMachine;
    }

    public void saveCurrentMechine(Machine machine) {
        if (machine == null) {
            Log.e(TAG, "saveCurrentMechine  currentMachine == null >> return;");
            return;
        }
        if (machine.getId() <= 0 && StringUtil.isNotEmpty(machine.getEnvironment(), true) == false) {
            Log.e(TAG, "saveCurrentMechine  user.getId() <= 0" +
                    " && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
            return;
        }

        currentMachine = machine;
        DataManager.getInstance().saveCurrentMachine(currentMachine);
    }

    public void logout() {
        currentMachine = null;
        DataManager.getInstance().saveCurrentMachine(currentMachine);
    }

    public boolean isLoggedIn() {
        return getCurrentMechineId() > 0;
    }
}
