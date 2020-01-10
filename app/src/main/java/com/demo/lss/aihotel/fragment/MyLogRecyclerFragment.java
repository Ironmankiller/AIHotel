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

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.demo.lss.aihotel.adapter.MyLogAdapter;
import com.demo.lss.aihotel.model.MyLog;
import com.demo.lss.aihotel.utils.HttpRequest;
import com.demo.lss.aihotel.utils.TestUtil;
import com.demo.lss.aihotel.view.MyLogView;

import java.util.List;

import zuo.biao.library.base.BaseHttpRecyclerFragment;
import zuo.biao.library.interfaces.AdapterCallBack;
import zuo.biao.library.interfaces.CacheCallBack;
import zuo.biao.library.util.JSON;

/**用户列表界面fragment
 * @author Lemon
 * @use new UserListFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 * @must 查看 .HttpManager 中的@must和@warn
 *       查看 .SettingUtil 中的@must和@warn
 */
public class MyLogRecyclerFragment extends BaseHttpRecyclerFragment<MyLog, MyLogView, MyLogAdapter> implements CacheCallBack<MyLog> {
    private static final String TAG = "MyLogRecyclerFragment";

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public static final String ARGUMENT_RANGE = "ARGUMENT_RANGE";

    public static MyLogRecyclerFragment createInstance(int range) {
        MyLogRecyclerFragment fragment = new MyLogRecyclerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_RANGE, range);

        fragment.setArguments(bundle);
        return fragment;
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    public static final int RANGE_ALL = HttpRequest.USER_LIST_RANGE_ALL;
    public static final int RANGE_RECOMMEND = HttpRequest.USER_LIST_RANGE_RECOMMEND;

    private int range = RANGE_ALL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        argument = getArguments();
        if (argument != null) {
            range = argument.getInt(ARGUMENT_RANGE, range);
        }


        initCache(this);

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        srlBaseHttpRecycler.autoRefresh();

        return view;
    }


    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initView() {//必须调用
        super.initView();

    }

    @Override
    public void setList(final List<MyLog> list) {
        setList(new AdapterCallBack<MyLogAdapter>() {

            @Override
            public MyLogAdapter createAdapter() {
                return new MyLogAdapter(context);
            }

            @Override
            public void refreshAdapter() {
                adapter.refresh(list);
            }
        });
    }



    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {//必须调用
        super.initData();

    }

    @Override
    public void getListAsync(final int page) {
        //实际使用时用这个，需要配置服务器地址		HttpRequest.getUserList(range, page, -page, this);

        //仅测试用<<<<<<<<<<<
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                onHttpResponse(-page, page >= 5 ? null : JSON.toJSONString(TestUtil.getMyLogList(page, getCacheCount())), null);
            }
        }, 1000);
        //仅测试用>>>>>>>>>>>>
    }

    @Override
    public List<MyLog> parseArray(String json) {
        return JSON.parseArray(json, MyLog.class);
    }


    @Override
    public Class<MyLog> getCacheClass() {
        return MyLog.class;
    }
    @Override
    public String getCacheGroup() {
        return "range=" + range;
    }
    @Override
    public String getCacheId(MyLog data) {
        return data == null ? null : "" + data.getId();
    }
    @Override
    public int getCacheCount() {
        return 10;
    }

    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Override
    public void initEvent() {//必须调用
        super.initEvent();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id > 0) {
            //toActivity(MyLogActivity.createIntent(context, id));
        }
    }


    //生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}