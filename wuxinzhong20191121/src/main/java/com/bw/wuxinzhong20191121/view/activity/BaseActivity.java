package com.bw.wuxinzhong20191121.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.wuxinzhong20191121.R;
import com.bw.wuxinzhong20191121.presenter.BasePresenter;
import com.bw.wuxinzhong20191121.view.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;
    private Unbinder mBind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        mBind = ButterKnife.bind(this);
        initListener();
        presenter = getPresenter();
        presenter.attachView(this);
        initData();
    }

    abstract void initData();

    abstract P getPresenter();

    abstract void initListener();

    abstract int initLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        presenter.detachView();
    }
}
