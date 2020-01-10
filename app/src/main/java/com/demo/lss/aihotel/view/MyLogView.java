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

package com.demo.lss.aihotel.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.demo.lss.aihotel.R;
import com.demo.lss.aihotel.faceserver.FaceServer;
import com.demo.lss.aihotel.model.MyLog;

import java.io.File;

import zuo.biao.library.base.BaseModel;
import zuo.biao.library.base.BaseView;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.StringUtil;

/**用户View
 * @author Lemon
 * @use
 * <br> UserView userView = new UserView(context, resources);
 * <br> adapter中使用:[具体参考.BaseViewAdapter(getView使用自定义View的写法)]
 * <br> convertView = userView.createView(inflater);
 * <br> userView.bindView(position, data);
 * <br> 或  其它类中使用:
 * <br> containerView.addView(userView.createView(inflater));
 * <br> userView.bindView(data);
 * <br> 然后
 * <br> userView.setOnDataChangedListener(onDataChangedListener);data = userView.getData();//非必需
 * <br> userView.setOnClickListener(onClickListener);//非必需
 */
public class MyLogView extends BaseView<MyLog> implements OnClickListener {
    private static final String TAG = "UserView";

    public MyLogView(Activity context, ViewGroup parent) {
        super(context, R.layout.mylog_view, parent);
    }

    public ImageView ivMyLogHead;
    public ImageView ivMyLogStar;

    public TextView tvMyLogSex;
    public TextView tvMyLogName;

    public TextView tvMyLogId;
    public TextView tvMyLogEnvironment;
    public TextView tvMyLogDate;

    @SuppressLint("InflateParams")
    @Override
    public View createView() {
        ivMyLogHead = findView(R.id.ivMyLogHead, this);
        ivMyLogStar = findView(R.id.ivMyLogStar, this);

        tvMyLogSex = findView(R.id.tvMyLogSex, this);

        tvMyLogName = findView(R.id.tvMyLogName);
        tvMyLogId = findView(R.id.tvMyLogId);
        tvMyLogEnvironment = findView(R.id.tvMyLogEnvironment);
        tvMyLogDate = findView(R.id.tvMyLogDate);

        return super.createView();
    }

    @Override
    public void bindView(MyLog data_){
        super.bindView(data_ != null ? data_ : new MyLog());

        File imgFile = new File(FaceServer.ROOT_PATH + File.separator + FaceServer.SAVE_IMG_DIR + File.separator + data.getUserName() + FaceServer.IMG_SUFFIX);
        Glide.with(ivMyLogHead)
                .load(imgFile)
                .into(ivMyLogHead);
//"http://192.168.42.17:8080/HelloWeb/"+data.getId()+".jpg"
        ivMyLogStar.setImageResource(data.getUserStar() ? R.drawable.star_light : R.drawable.star);

        tvMyLogSex.setBackgroundResource(data.getUserSex() == MyLog.SEX_FEMALE
                ? R.drawable.circle_pink : R.drawable.circle_blue);
        tvMyLogSex.setText(data.getUserSex() == MyLog.SEX_FEMALE ?  "女" : "男");
        tvMyLogSex.setTextColor(getColor(data.getUserSex() == MyLog.SEX_FEMALE ? R.color.pink : R.color.blue));

        tvMyLogName.setText(StringUtil.getTrimedString(data.getUserName().substring(0,data.getUserName().length()-1)));
        tvMyLogId.setText("机器编号:" + data.getId());
        tvMyLogDate.setText("时间:" + StringUtil.getNoBlankString(data.getDate()));
        tvMyLogEnvironment.setText("工作环境:" + StringUtil.getNoBlankString(data.getMachine().getEnvironment()));
    }

    @Override
    public void onClick(View v) {
        if (BaseModel.isCorrect(data) == false) {
            return;
        }
        switch (v.getId()) {
            case R.id.ivMyLogHead:
                //toActivity(WebViewActivity.createIntent(context, data.getName(), data.getHead()));
                break;
            default:
                switch (v.getId()) {
                    case R.id.ivMyLogStar:
                        data.setUserStar(! data.getUserStar());
                        break;
                    case R.id.tvMyLogSex:
                        data.setUserSex(data.getUserSex() == MyLog.SEX_FEMALE ? MyLog.SEX_MAIL : MyLog.SEX_FEMALE);
                        break;
                }
                bindView(data);
                break;
        }
    }
}