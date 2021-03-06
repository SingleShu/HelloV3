package com.abings.baby.teacher.ui.Information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.abings.baby.teacher.data.injection.DaggerUtils;
import com.hellobaby.library.data.model.InformationModel;
import com.hellobaby.library.ui.main.fm.school.SchoolItem;
import com.hellobaby.library.ui.webview.BaseWebViewActivity;
import com.hellobaby.library.widget.BottomDialogUtils;
import com.hellobaby.library.widget.custom.ShareBottomDialog;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/1/10.
 */

public class WebViewActivity extends BaseWebViewActivity implements InformationMvp {
    @Inject
    InformationPresenter presenter;

    @Override
    protected void initDaggerInject() {
        DaggerUtils.getActivityComponent(getActivityComponent(), this).inject(this);
    }

    @Override
    protected void initViewsAndEvents(@Nullable Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);
        presenter.attachView(this);
        final SchoolItem schoolItem=(SchoolItem)getIntent().getSerializableExtra("data");
        final InformationModel informationModel=(InformationModel)getIntent().getSerializableExtra("infromationdata");
        if (!getIntent().getBooleanExtra("isFavorite",false)) {
            final String[] items = {"收藏","分享", "取消"};
            bIvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomDialogUtils.getBottomListDialog(bContext, items, new BottomDialogUtils.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, String item, long id) {
                            if (position == 0)
                                presenter.insertTFavorite(getIntent().getStringExtra("favoriteType"), getIntent().getIntExtra("favoriteFavoriteid", 0) + "");
                            if(position==1) {
                                if(null!=schoolItem)
                                    ShareBottomDialog.getShareUrlBottomDialog(bContext, null,schoolItem.getName(),"来自哈喽宝贝教师端",new UMImage(bContext,schoolItem.getListUrl().get(0)),schoolItem.getNewsUrl());
                                else
                                    ShareBottomDialog.getShareUrlBottomDialog(bContext, null,informationModel.getTitle(),"来自哈喽宝贝教师端",new UMImage(bContext,informationModel.getNewInfoImageurls().split(",")[0]),informationModel.getDetailsUrl());
                            }
                        }
                    });
                }
            });
        } else {
            final String[] items = {"取消收藏","分享", "取消"};
            bIvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomDialogUtils.getBottomListDialog(bContext, items, new BottomDialogUtils.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, String item, long id) {
                            if (position == 0)
                                presenter.DeleteTFavorite(getIntent().getStringExtra("favoriteId"));
                            if(position==1) {
                                if(null!=schoolItem)
                                    ShareBottomDialog.getShareUrlBottomDialog(bContext, null,schoolItem.getName(),"来自哈喽宝贝教师端",new UMImage(bContext,schoolItem.getListUrl().get(0)),schoolItem.getNewsUrl());
                                else
                                    ShareBottomDialog.getShareUrlBottomDialog(bContext, null,informationModel.getTitle(),"来自哈喽宝贝教师端",new UMImage(bContext,informationModel.getNewInfoImageurls().split(",")[0]),informationModel.getDetailsUrl());
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public void showListData(List<InformationModel> InformationModel) {

    }

    @Override
    public void showMsgFinish(String msg) {
        showToast(msg);
        finish();
    }
}
