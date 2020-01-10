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

package com.demo.lss.aihotel.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.demo.lss.aihotel.model.MyLog;
import com.demo.lss.aihotel.view.MyLogView;

import zuo.biao.library.base.BaseAdapter;

/**用户adapter
 * @author Lemon
 */
public class MyLogAdapter extends BaseAdapter<MyLog, MyLogView> {
	//	private static final String TAG = "MyLogAdapter";

	public MyLogAdapter(Activity context) {
		super(context);
	}

	@Override
	public MyLogView createView(int position, ViewGroup parent) {
		return new MyLogView(context, parent);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

}