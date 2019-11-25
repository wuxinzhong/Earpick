package com.bw.wuxinzhong20191121.constraint;

import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;
import com.bw.wuxinzhong20191121.view.interfaces.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public interface Constraint {

    interface IShopCarView extends IBaseView {
        void shopCarSuccess(ShopCarBean shopCarBean);

        void shopCarError(String s);
    }

}
