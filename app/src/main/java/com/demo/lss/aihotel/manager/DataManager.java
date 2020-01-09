/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.demo.lss.aihotel.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.lss.aihotel.application.AIHotelApplication;
import com.demo.lss.aihotel.model.Machine;

import zuo.biao.library.util.JSON;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**数据工具类
 * @author Lemon
 */
public class DataManager {
	private final String TAG = "DataManager";

	private Context context;
	private DataManager(Context context) {
		this.context = context;
	}

	private static DataManager instance;
	public static DataManager getInstance() {
		if (instance == null) {
			synchronized (DataManager.class) {
				if (instance == null) {
					instance = new DataManager(AIHotelApplication.getInstance());
				}
			}
		}
		return instance;
	}

	//用户 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String PATH_USER = "PATH_USER";

	public final String KEY_CURRENT_USER_ID = "KEY_CURRENT_USER_ID";
	public final String KEY_LAST_USER_ID = "KEY_LAST_USER_ID";

	/**
	 * 保存激活状态
	 * @param isActive
	 */
	public void saveActiveState(Boolean isActive) {
		saveActiveState(context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE), isActive);
	}

	/**
	 * 保存激活状态
	 * @param sdf
	 * @param isActive
	 */
	public void saveActiveState(SharedPreferences sdf, Boolean isActive) {
		if (sdf == null || isActive == null) {
			Log.e(TAG, "saveActiveState sdf == null || user == null >> return;");
			return;
		}
		Log.i(TAG, "saveActiveState  state = " + isActive);
		sdf.edit().remove("active").putString("active", JSON.toJSONString(isActive)).commit();
	}

	/**
	 * 获得激活状态
	 * @param machineId
	 * @return
	 */
	public Boolean getActiveState() {
		SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
		if (sdf == null) {
			Log.e(TAG, "get sdf == null >>  return;");
			return null;
		}
		return JSON.parseObject(sdf.getString("active", null), Boolean.class);
	}

	/**
	 * 保存工作环境
	 * @param env
	 */
	public void saveEnvironment(String env) {
		saveEnvironment(context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE), env);
	}

	/**
	 * 保存工作环境
	 * @param sdf
	 * @param env
	 */
	public void saveEnvironment(SharedPreferences sdf, String env) {
		if (sdf == null || env == null) {
			Log.e(TAG, "saveEnvironment sdf == null || user == null >> return;");
			return;
		}
		Log.i(TAG, "saveEnvironment  state = " + env);
		sdf.edit().remove("environment").putString("environment", JSON.toJSONString(env)).commit();
	}

	/**
	 * 获得工作环境
	 * @param machineId
	 * @return
	 */
	public String getEnvironment() {
		SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
		if (sdf == null) {
			Log.e(TAG, "get sdf == null >>  return;");
			return null;
		}
		return sdf.getString("environment", null);
	}

	/**获取当前用户id
	 * @param context
	 * @return
	 */
	public long getCurrentMachineId() {
		Machine machine = getCurrentMachine();
		return machine == null ? 0 : machine.getId();
	}
	/**获取当前用户
	 * @param context
	 * @return
	 */
	public Machine getCurrentMachine() {
		SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
		return sdf == null ? null : getMachine(sdf.getLong(KEY_CURRENT_USER_ID, 0));
	}
	

	/**获取用户
	 * @param context
	 * @param machineId
	 * @return
	 */
	public Machine getMachine(long machineId) {
		SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
		if (sdf == null) {
			Log.e(TAG, "get sdf == null >>  return;");
			return null;
		}
		Log.i(TAG, "getMachine  machineId = " + machineId);
		return JSON.parseObject(sdf.getString(StringUtil.getTrimedString(machineId), null), Machine.class);
	}


	/**保存当前用户,只在登录或注销时调用
	 * @param context
	 * @param machine  machine == null >> machine = new Machine();
	 */
	public void saveCurrentMachine(Machine machine) {
		SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
		if (sdf == null) {
			Log.e(TAG, "saveMachine sdf == null  >> return;");
			return;
		}
		if (machine == null) {
			Log.w(TAG, "saveMachine  machine == null >>  machine = new Machine();");
			machine = new Machine();
		}
		SharedPreferences.Editor editor = sdf.edit();
		editor.remove(KEY_LAST_USER_ID).putLong(KEY_LAST_USER_ID, getCurrentMachineId());
		editor.remove(KEY_CURRENT_USER_ID).putLong(KEY_CURRENT_USER_ID, machine.getId());
		editor.commit();

		saveMachine(sdf, machine);
	}

	/**保存用户
	 * @param context
	 * @param machine
	 */
	public void saveMachine(Machine machine) {
		saveMachine(context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE), machine);
	}
	/**保存用户
	 * @param context
	 * @param sdf
	 * @param machine
	 */
	public void saveMachine(SharedPreferences sdf, Machine machine) {
		if (sdf == null || machine == null) {
			Log.e(TAG, "saveMachine sdf == null || machine == null >> return;");
			return;
		}
		String key = StringUtil.getTrimedString(machine.getId());
		Log.i(TAG, "saveMachine  key = machine.getId() = " + machine.getId());
		sdf.edit().remove(key).putString(key, JSON.toJSONString(machine)).commit();
	}
	

	//用户 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




}
