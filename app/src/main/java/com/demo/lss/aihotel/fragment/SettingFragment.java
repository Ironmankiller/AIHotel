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

package com.demo.lss.aihotel.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.lss.aihotel.R;
import com.demo.lss.aihotel.activity.AboutActivity;
import com.demo.lss.aihotel.activity.ActiveActivity;
import com.demo.lss.aihotel.activity.ServerSettingActivity;
import com.demo.lss.aihotel.faceserver.FaceServer;
import com.demo.lss.aihotel.manager.DataManager;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.util.SettingUtil;

/**设置fragment
 * @author Lemon
 * @use new SettingFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 */
public class SettingFragment extends BaseFragment implements OnClickListener ,AlertDialog.OnDialogButtonClickListener {
	private static final String TAG = "SettingFragment";
	private Boolean isActive = false;
	private String envStr;

	//与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**创建一个Fragment实例
	 * @return
	 */
	public static SettingFragment createInstance() {
		return new SettingFragment();
	}

	//与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//类相关初始化，必须使用<<<<<<<<<<<<<<<<
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.setting_fragment);
		//类相关初始化，必须使用>>>>>>>>>>>>>>>>

		//功能归类分区方法，必须调用<<<<<<<<<<
		initData();
		initView();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		return view;
	}



	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private TextView tvSettingEnvironment;
	private TextView tvSettingActive;
	private TextView tvSettingServerIP;
	@Override
	public void initView() {//必须调用

		tvSettingEnvironment = findView(R.id.llSettingEnvironmentTx);
		tvSettingActive = findView(R.id.llSettingActiveTx);
		tvSettingServerIP = findView(R.id.llSettingServerIPTx);

		if(isActive!=null&&isActive) {
			tvSettingActive.setText("已激活");
		} else{
			tvSettingActive.setText("未激活");
		}
		tvSettingEnvironment.setText(envStr);
		tvSettingServerIP.setText(SettingUtil.getCurrentServerAddress(false));
	}




	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private static final String[] TOPBAR_ENVIRONMENT_NAMES = {"前台", "电梯口", "房门","会议室","健身房","餐厅"};








	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须调用

		isActive = DataManager.getInstance().getActiveState();
		envStr = DataManager.getInstance().getEnvironment();
	}



	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用

		findView(R.id.llSettingActive).setOnClickListener(this);
		findView(R.id.llSettingEnvironment).setOnClickListener(this);
		findView(R.id.llSettingServer).setOnClickListener(this);
		findView(R.id.llSettingAbout).setOnClickListener(this);
		findView(R.id.llSettingClearFace).setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {//直接调用不会显示v被点击效果
		switch (v.getId()) {
			case R.id.llSettingActive:
				if(isActive!=null && isActive==true) {
					new AlertDialog(context, "提示", "这台设备已被激活", false, 0, this).show();
				} else {
					toActivity(ActiveActivity.createIntent(context),REQUEST_TO_ACTIVE);
				}
				break;
			case R.id.llSettingEnvironment:
				toActivity(BottomMenuWindow.createIntent(context, TOPBAR_ENVIRONMENT_NAMES)
						.putExtra(BottomMenuWindow.INTENT_TITLE, "选择工作环境"), REQUEST_TO_BOTTOM_MENU, false);
				break;
			case R.id.llSettingServer:
				toActivity(ServerSettingActivity.createIntent(context
						, SettingUtil.getServerAddress(false), SettingUtil.getServerAddress(true)
						, SettingUtil.APP_SETTING, Context.MODE_PRIVATE
						, SettingUtil.KEY_SERVER_ADDRESS_NORMAL, SettingUtil.KEY_SERVER_ADDRESS_TEST));
				break;
			case R.id.llSettingAbout:
				toActivity(AboutActivity.createIntent(context));
				break;
			case R.id.llSettingClearFace:
				clearFaces();
				break;
			default:
				break;
		}
	}


	//生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_SELECT_PICTURE = 20;
	private static final int REQUEST_TO_CUT_PICTURE = 21;
	public static final int REQUEST_TO_CAMERA_SCAN = 22;
	private static final int REQUEST_TO_EDIT_TEXT_INFO = 23;
	private static final int REQUEST_TO_SERVER_SETTING = 24;
	private static final int REQUEST_TO_DEMO_BOTTOM_WINDOW = 25;

	private static final int REQUEST_TO_ACTIVE = 30;
	private static final int REQUEST_TO_BOTTOM_MENU = 31;
	private static final int REQUEST_TO_PLACE_PICK = 32;
	private static final int REQUEST_TO_DATE_PICKER = 33;
	private static final int REQUEST_TO_TIME_PICKER = 34;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
			case REQUEST_TO_BOTTOM_MENU:
				int position = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
				tvSettingEnvironment.setText(TOPBAR_ENVIRONMENT_NAMES[position]);
				DataManager.getInstance().saveEnvironment(TOPBAR_ENVIRONMENT_NAMES[position]);
				break;
			case REQUEST_TO_ACTIVE:
				isActive = true;
				tvSettingActive.setText("已激活");
				DataManager.getInstance().saveActiveState(true);
				new AlertDialog(context, "提示", "这台设备已被激活", false, 0, this).show();
				break;
			default:
				break;
		}
	}

	//生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public void onDialogButtonClick(int requestCode, boolean isPositive) {
		if (! isPositive) {
			return;
		}
	}

	public void clearFaces() {
		int faceNum = FaceServer.getInstance().getFaceNumber(getContext());
		if (faceNum == 0) {
			showShortToast(getString(R.string.batch_process_no_face_need_to_delete));
		} else {
			android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(getContext())
					.setTitle(R.string.batch_process_notification)
					.setMessage(getString(R.string.batch_process_confirm_delete, faceNum))
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							int deleteCount = FaceServer.getInstance().clearAllFaces(getContext());
							showShortToast(deleteCount + " faces cleared!");
						}
					})
					.setNegativeButton(R.string.cancel, null)
					.create();
			dialog.show();
		}
	}



	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}