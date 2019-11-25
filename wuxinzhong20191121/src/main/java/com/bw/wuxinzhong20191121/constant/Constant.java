package com.bw.wuxinzhong20191121.constant;

import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public interface Constant {

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ShopCarBean> SHOP_CAR_BEAN(@Header("sessionId") String sessionId,
                                          @Header("userId") int userId);

}
