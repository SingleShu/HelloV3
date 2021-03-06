package com.abings.baby.teacher.ui.reviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abings.baby.teacher.R;
import com.abings.baby.teacher.ZSApp;
import com.abings.baby.teacher.ui.base.BaseTitleActivity;
import com.abings.baby.teacher.ui.reviews.type.ReviewsTypeActivity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hellobaby.library.data.model.ClassModel;
import com.hellobaby.library.widget.baseadapter.BaseAdapter;
import com.hellobaby.library.widget.baseadapter.OnItemClickListeners;
import com.hellobaby.library.widget.baseadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zwj on 2016/12/1.
 * description : 学生评语
 */

public class ReviewsClassActivity extends BaseTitleActivity implements ReviewsMvpView {
    @Inject
    ReviewsPresenter presenter;
    @BindView(R.id.reviewsClass_rv)
    RecyclerView recyclerView;
    List<ClassModel> list;
    BaseAdapter<ClassModel> adapter;
    String day = "";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_reviews_class;
    }

    @Override
    protected void initDaggerInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewsAndEvents(@Nullable Bundle savedInstanceState) {
        presenter.attachView(this);
        setBtnLeftClickFinish();
        list = new ArrayList<>();
        list.addAll(ZSApp.getInstance().getClassModelList());
        adapter = new BaseAdapter<ClassModel>(bContext, list, false) {
            @Override
            protected void convert(ViewHolder holder, ClassModel data) {
                holder.setText(R.id.reviewsClassItem_className, data.getClassName());
            }

            @Override
            protected int getItemLayoutId() {
                return R.layout.recycler_item_reviews_class;
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(bContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListeners<ClassModel>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, ClassModel data, int position) {
                Intent intent = new Intent(bContext,ReviewsTypeActivity.class);
                intent.putExtra("class", data.getClassId());
                intent.putExtra("searchday", day);
                intent.putExtra("classname", data.getClassName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(Object o) {

    }


    @Override
    public void showJSONObject(JSONObject jsonObject) {

    }

    @Override
    public void showJSONArray(JSONArray jsonObject) {

    }

    @Override
    public void updateFinish() {

    }
}
