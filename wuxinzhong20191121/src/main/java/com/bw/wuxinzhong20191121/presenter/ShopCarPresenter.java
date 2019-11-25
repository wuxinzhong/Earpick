package com.bw.wuxinzhong20191121.presenter;

import com.bw.wuxinzhong20191121.constraint.Constraint;
import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;
import com.bw.wuxinzhong20191121.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public class ShopCarPresenter extends BasePresenter<Constraint.IShopCarView> {

    public void shopCar(String sessionId,int userId){
        HttpUtils.getInstance().getConstant().SHOP_CAR_BEAN(sessionId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopCarBean>() {
                    @Override
                    public void accept(ShopCarBean shopCarBean) throws Exception {
                        getView().shopCarSuccess(shopCarBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().shopCarError(throwable.getMessage());
                    }
                });
    }

}
