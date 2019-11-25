package com.bw.wuxinzhong20191121.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public class ShopCarBean {
    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String categoryName;
        public List<ShoppingCartListBean> shoppingCartList;
        public boolean check;

        public static class ShoppingCartListBean {

            public int commodityId;
            public String commodityName;
            public int count;
            public String pic;
            public double price;
            public boolean check;
        }
    }
}
