package com.bw.wuxinzhong20191121.presenter;

import com.bw.wuxinzhong20191121.view.interfaces.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public class BasePresenter<V extends IBaseView> {

    public V mV;

    public void attachView(V v) {
        mV = v;
    }

    public void detachView() {
        mV = null;
    }

    public V getView() {
        return mV;
    }

}
