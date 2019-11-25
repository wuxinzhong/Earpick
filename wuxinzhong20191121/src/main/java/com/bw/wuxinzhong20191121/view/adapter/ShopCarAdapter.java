package com.bw.wuxinzhong20191121.view.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.wuxinzhong20191121.R;
import com.bw.wuxinzhong20191121.model.bean.ShopCarBean;
import com.bw.wuxinzhong20191121.view.myview.AdderView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShopCarAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private List<ShopCarBean.ResultBean> mGroupList = new ArrayList<>();

    public void addAll(List<ShopCarBean.ResultBean> mGroup) {
        if (mGroup != null)
            this.mGroupList.addAll(mGroup);
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupList.get(groupPosition).shoppingCartList.size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.group_item, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.group_name = convertView.findViewById(R.id.group_name);
            groupViewHolder.group_check = convertView.findViewById(R.id.group_check);

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.group_name.setText(mGroupList.get(groupPosition).categoryName);
        groupViewHolder.group_check.setChecked(mGroupList.get(groupPosition).check);
        groupViewHolder.group_check.setTag(groupPosition);
        groupViewHolder.group_check.setOnClickListener(this);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.child_item, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.child_check = convertView.findViewById(R.id.child_check);
            childViewHolder.child_img = convertView.findViewById(R.id.child_img);
            childViewHolder.child_name = convertView.findViewById(R.id.child_name);
            childViewHolder.child_price = convertView.findViewById(R.id.child_price);
            childViewHolder.adder = convertView.findViewById(R.id.adder);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //获取商品集合
        ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean = mGroupList.get(groupPosition).shoppingCartList.get(childPosition);

        childViewHolder.child_price.setText(String.valueOf(shoppingCartListBean.price));
        childViewHolder.child_name.setText(shoppingCartListBean.commodityName);
        Uri uri = Uri.parse(shoppingCartListBean.pic);
        childViewHolder.child_img.setImageURI(uri);
        childViewHolder.adder.setCount(shoppingCartListBean.count);
        childViewHolder.adder.setTag(shoppingCartListBean);
        childViewHolder.adder.setWork(new AdderView.work() {
            @Override
            public void backWork() {
                notifyDataSetChanged();
                sumPrice();
            }
        });

        childViewHolder.child_check.setChecked(shoppingCartListBean.check);
        childViewHolder.child_check.setTag(shoppingCartListBean);
        childViewHolder.child_check.setOnClickListener(this);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_check:
                int groupPosition = (int) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                ShopCarBean.ResultBean resultBean = mGroupList.get(groupPosition);
                resultBean.check = checkBox.isChecked();
                for (int i = 0; i < resultBean.shoppingCartList.size(); i++) {
                    ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.shoppingCartList.get(i);
                    shoppingCartListBean.check = checkBox.isChecked();
                }

                notifyDataSetChanged();
                sumPrice();
                break;

            case R.id.child_check:

                //商品选中
                ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean = (ShopCarBean.ResultBean.ShoppingCartListBean) v.getTag();
                CheckBox checkBox1 = (CheckBox) v;
                shoppingCartListBean.check = checkBox1.isChecked();

                childToGroup();

                sumPrice();

                break;
        }
    }


    //商品全部选中店铺选中
    private void childToGroup() {
        for (int i = 0; i < mGroupList.size(); i++) {
            ShopCarBean.ResultBean resultBean = mGroupList.get(i);
            boolean isCheck = true;
            for (int j = 0; j < resultBean.shoppingCartList.size(); j++) {
                ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.shoppingCartList.get(j);
                isCheck = isCheck && shoppingCartListBean.check;
            }
            resultBean.check = isCheck;
        }
        notifyDataSetChanged();
    }


    //全选
    public void allCheck(boolean isChecked) {

        for (int i = 0; i < mGroupList.size(); i++) {
            mGroupList.get(i).check = isChecked;

            for (int j = 0; j < mGroupList.get(i).shoppingCartList.size(); j++) {
                mGroupList.get(i).shoppingCartList.get(j).check = isChecked;
            }

        }
        notifyDataSetChanged();
        sumPrice();

    }

    //计算价格
    public void sumPrice() {
        double num = 0;
        boolean ac = true;
        for (int i = 0; i < mGroupList.size(); i++) {
            ShopCarBean.ResultBean resultBean = mGroupList.get(i);
            ac = ac && resultBean.check;

            for (int i1 = 0; i1 < mGroupList.get(i).shoppingCartList.size(); i1++) {
                ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean = mGroupList.get(i).shoppingCartList.get(i1);
                if (shoppingCartListBean.check)
                    num += shoppingCartListBean.price * shoppingCartListBean.count;
            }
        }

        if (mMySumPrice != null)
            mMySumPrice.sumPrices(num,ac);
    }


    private mySumPrice mMySumPrice;

    public void setMySumPrice(mySumPrice mySumPrice) {
        mMySumPrice = mySumPrice;
    }

    public interface mySumPrice {
        void sumPrices(double numPrice,boolean ac);
    }


    public class GroupViewHolder {
        private CheckBox group_check;
        private TextView group_name;
    }

    public class ChildViewHolder {
        private CheckBox child_check;
        private SimpleDraweeView child_img;
        private TextView child_name;
        private TextView child_price;
        private AdderView adder;
    }
}
