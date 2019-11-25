package com.bw.wuxinzhong20191121.view.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.wuxinzhong20191121.R;
import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;


public class AdderView extends LinearLayout implements View.OnClickListener {

    public TextView add_jian, add_jia;
    public EditText add_num;

    public AdderView(Context context) {
        super(context);
        init(context);
    }

    public AdderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.adder_layout, this, true);
        add_jian = view.findViewById(R.id.add_jian);
        add_num = view.findViewById(R.id.add_num);
        add_jia = view.findViewById(R.id.add_jia);
        add_jian.setOnClickListener(this);
        add_jia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ShopCarBean.ResultBean.ShoppingCartListBean cartListBean = (ShopCarBean.ResultBean.ShoppingCartListBean) getTag();

        switch (v.getId()) {
            case R.id.add_jian:
                if (cartListBean.count > 1) {
                    cartListBean.count = cartListBean.count - 1;
                }
                break;
            case R.id.add_jia:
                cartListBean.count = cartListBean.count + 1;
                break;
        }

        mWork.backWork();
    }

    public void setCount(int count) {
        add_num.setText(String.valueOf(count));
    }

    private work mWork;

    public void setWork(work work) {
        mWork = work;
    }

    public interface work {
        void backWork();
    }
}
