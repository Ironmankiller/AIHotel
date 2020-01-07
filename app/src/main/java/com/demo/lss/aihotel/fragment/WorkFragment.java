package com.demo.lss.aihotel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.lss.aihotel.R;
import com.demo.lss.aihotel.activity.AboutActivity;
import com.demo.lss.aihotel.activity.ActiveActivity;
import com.demo.lss.aihotel.activity.RegisterAndRecognizeActivity;
import com.demo.lss.aihotel.activity.ServerActivity;
import com.demo.lss.aihotel.manager.DataManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.ui.AlertDialog;

public class WorkFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "WorkFragment";

    boolean libraryExists = true;
    // Demo 所需的动态库文件
    private static final String[] LIBRARIES = new String[]{
            // 人脸相关
            "libarcsoft_face_engine.so",
            "libarcsoft_face.so",
            // 图像库相关
            "libarcsoft_image_util.so",
    };

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**创建一个Fragment实例
     * @return
     */
    public static WorkFragment createInstance() {
        return new WorkFragment();
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.work_fragment);
        //类相关初始化，必须使用>>>>>>>>>>>>>>>>
        libraryExists = checkSoFile(LIBRARIES);
        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }



    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initView() {//必须调用

    }




    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {//必须调用

    }



    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public static final int WORK_QIANTAI=0;
    public static final int WORK_DIANTI=1;
    public static final int WORK_FANGMEN=2;
    public static final int WORK_JIANSHENFANG=3;
    public static final int WORK_CANTING=4;
    public static final int WORK_HUIYISHI=5;






    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initEvent() {//必须调用

        findView(R.id.llWorkCanting).setOnClickListener(this);
        findView(R.id.llWorkDianti).setOnClickListener(this);
        findView(R.id.llWorkFangmen).setOnClickListener(this);
        findView(R.id.llWorkHuiyishi).setOnClickListener(this);
        findView(R.id.llWorkJianshenfang).setOnClickListener(this);
        findView(R.id.llWorkQiantai).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {//直接调用不会显示v被点击效果
        if (!libraryExists) {
            showShortToast(getString(R.string.library_not_found));
            return;
        }
        switch (v.getId()) {
            case R.id.llWorkQiantai:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_QIANTAI);
                break;
            case R.id.llWorkDianti:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_DIANTI);
                break;
            case R.id.llWorkFangmen:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_FANGMEN);
                break;
            case R.id.llWorkJianshenfang:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_JIANSHENFANG);
                break;
            case R.id.llWorkCanting:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_CANTING);
                break;
            case R.id.llWorkHuiyishi:
                checkLibraryAndJump(RegisterAndRecognizeActivity.class,WORK_HUIYISHI);
                break;
            default:
                break;
        }
    }


    //生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    /**
     * 检查能否找到动态链接库，如果找不到，请修改工程配置
     *
     * @param libraries 需要的动态链接库
     * @return 动态库是否存在
     */
    private boolean checkSoFile(String[] libraries) {
        File dir = new File(context.getApplicationInfo().nativeLibraryDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        List<String> libraryNameList = new ArrayList<>();
        for (File file : files) {
            libraryNameList.add(file.getName());
        }
        boolean exists = true;
        for (String library : libraries) {
            exists &= libraryNameList.contains(library);
        }
        return exists;
    }


    void checkLibraryAndJump(Class activityClass,int flag) {
        if (!libraryExists) {
            showShortToast(getString(R.string.library_not_found));
            return;
        }
        Intent intent = new Intent(context,activityClass);
        startActivity(intent.addFlags(flag));
    }
    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
