package com.bw.wuxinzhong20191121.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bw.wuxinzhong20191121.R;
import com.bw.wuxinzhong20191121.constraint.Constraint;
import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;
import com.bw.wuxinzhong20191121.presenter.ShopCarPresenter;
import com.bw.wuxinzhong20191121.view.adapter.ShopCarAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<ShopCarPresenter> implements Constraint.IShopCarView, View.OnClickListener, ShopCarAdapter.mySumPrice {

    @BindView(R.id.expand)
    ExpandableListView expand;
    @BindView(R.id.all_check)
    CheckBox allCheck;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.all_btn_jie)
    Button allBtnJie;
    private ShopCarAdapter mShopCarAdapter;


    @OnClick(R.id.all_btn_jie)
    public void onViewClicked() {
    }

    @Override
    public void shopCarSuccess(ShopCarBean shopCarBean) {

        if (shopCarBean.status.equals("0000")) {

            mShopCarAdapter.addAll(shopCarBean.result);
            mShopCarAdapter.notifyDataSetChanged();

        }
        mShopCarAdapter.setMySumPrice(this);
        for (int i = 0; i < shopCarBean.result.size(); i++) {
            expand.expandGroup(i);
        }

    }

    @Override
    public void shopCarError(String s) {

    }

    @Override
    void initData() {




        mShopCarAdapter = new ShopCarAdapter();
        expand.setAdapter(mShopCarAdapter);

        presenter.shopCar("15743027217657619", 7619);
    }

    @Override
    ShopCarPresenter getPresenter() {
        return new ShopCarPresenter();
    }

    @Override
    void initListener() {
        allCheck.setOnClickListener(this);


    }

    @Override
    int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all_check:
                mShopCarAdapter.allCheck(allCheck.isChecked());
                mShopCarAdapter.sumPrice();
                break;
        }
    }

    @Override
    public void sumPrices(double numPrice, boolean ac) {
        allCheck.setChecked(ac);
        allPrice.setText(String.valueOf(numPrice));
    }
}
