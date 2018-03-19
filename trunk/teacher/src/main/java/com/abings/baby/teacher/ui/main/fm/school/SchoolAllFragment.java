package com.abings.baby.teacher.ui.main.fm.school;

import com.abings.baby.teacher.R;
import com.abings.baby.teacher.ZSApp;
import com.abings.baby.teacher.data.injection.DaggerUtils;
import com.abings.baby.teacher.ui.event.EventDetailActivity;
import com.abings.baby.teacher.ui.event.EventListMvpView;
import com.abings.baby.teacher.ui.event.EventListPresenter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hellobaby.library.Const;
import com.hellobaby.library.data.model.EventBusObject;
import com.hellobaby.library.data.model.EventModel;
import com.hellobaby.library.data.model.PageModel;
import com.hellobaby.library.ui.base.BaseLibActivity;
import com.hellobaby.library.ui.main.fm.school.SchoolItem;
import com.hellobaby.library.ui.main.fm.school.SchoolRecyclerViewFragment;
import com.hellobaby.library.utils.DateUtil;
import com.hellobaby.library.utils.LogZS;
import com.hellobaby.library.widget.ToastUtils;
import com.hellobaby.library.widget.baseadapter.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by zwj on 2016/12/20.
 * description :
 */

public class SchoolAllFragment extends SchoolRecyclerViewFragment implements EventListMvpView {
    @Inject
    EventListPresenter presenter;
    String cook = "早餐：%s\n早点：%s\n午餐：%s\n午点：%s";
    String eventstring = "活动地点：%s\n活动时间：%s";


    @Override
    protected void initDaggerInject() {
        DaggerUtils.getActivityComponent(((BaseLibActivity) getActivity()).getActivityComponent(), getActivity()).inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        bAdapter.setIsAll(true);
        presenter.attachView(this);
//        refreshSchoolAll();
        bAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onEvent(String message) {
        if (message.equals("reflushfr") || message.equals("reflushfre"))
            refreshSchoolAll();
    }

    /**
     * 作者：ShuWen
     * 日期：2018/1/3  14:39
     * 描述：从SchoolMasterChooseActivity选择后回调更新数据
     *
     * @return [返回类型说明]
     */
    @Subscribe
    public void onEvent(EventBusObject busObject){
        if (busObject.getFromWhere().equals(SchoolMasterChooseActivity.class.getSimpleName())
                && busObject.getToWhere().equals(SchoolMasterChooseActivity.TO_WHERE)){
            LogZS.i("更新全部数据："+busObject.toString());
            mSwipeRefreshLayout.setRefreshing(true);
            pageModel = new PageModel();
            classesIDS = busObject.getMsg();
            if (busObject.getMsg().equals(SchoolMasterChooseActivity.RESULT_DATA)){
                refreshSchoolAll();
            }else {
                presenter.selectEventBySchoolAdminPage(busObject.getMsg(),Const.SCHOOL_ALL, pageModel.getPageNum());
            }
        }
    }

    @Subscribe
    public void onEvent(Integer id) {
        presenter.deleteDynamicById(id + "");
        showProgress(true);
        EventBus.getDefault().post("reflushfr");
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void refreshSchoolAll() {
        bListData.clear();
        pageModel = new PageModel();
        if (!classesIDS.equals(SchoolMasterChooseActivity.RESULT_DATA)){
            presenter.selectEventBySchoolAdminPage(classesIDS,Const.SCHOOL_ALL, pageModel.getPageNum());
        }else {
            presenter.selectSchoolOnTeacherPage(Const.SCHOOL_ALL, pageModel.getPageNum());
        }
    }

    @Override
    public void initAdapter() {
        bAdapter = new SchoolRVAdapter(getContext(), bListData, EventDetailActivity.class);
        bAdapter.setLoadingView(R.layout.footer_more);
        bAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (pageModel.getPageNum() != pageModel.getPages())
                    if (!classesIDS.equals(SchoolMasterChooseActivity.RESULT_DATA)){
                        presenter.selectEventBySchoolAdminPage(classesIDS,Const.SCHOOL_ALL, pageModel.getPageNum()+1);
                    }else {
                        presenter.selectSchoolOnTeacherPage(Const.SCHOOL_ALL, pageModel.getPageNum() + 1);
                    }
                else {
                    bAdapter.setLoadEndView(R.layout.footer_loadend);
                }
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initListData() {
        pageModel = new PageModel();
        if (!classesIDS.equals(SchoolMasterChooseActivity.RESULT_DATA)){
            presenter.selectEventBySchoolAdminPage(classesIDS,Const.SCHOOL_ALL, pageModel.getPageNum());
        }else {
            presenter.selectSchoolOnTeacherPage(Const.SCHOOL_ALL, pageModel.getPageNum());
        }
    }

    @Override
    public void showEventData(List<EventModel> list) {

    }

    @Override
    public void showListData(JSONArray jsonArray) {
        if(pageModel.getPageNum()==1){
            bListData.clear();
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (!jsonObject.getString("dynamicId").equals("0.0")) {
                List<String> sList1 = new ArrayList<>();
                SchoolItem schoolItem = new SchoolItem(SchoolItem.TYPE_DYNAMIC, jsonObject.getString("description"), sList1, jsonObject.getString("headImageurl"));
                if (null != jsonObject.get("coverImageurl")) { //是视频
                    schoolItem.setVideoImageUrl(Const.URL_dynamicFirstFrame + jsonObject.get("coverImageurl"));
                    schoolItem.setVideoUrl(jsonObject.getString("imageurl") == null ? "" : jsonObject.getString("imageurl"));
                } else if (null != jsonObject.getString("imageurl")) {
                    List<String> sList2 = Arrays.asList((jsonObject.getString("imageurl").split(",")));
                    for (String s : sList2) {
                        sList1.add(Const.URL_dynamicImgs + s);
                    }
                }
                schoolItem.setCreator(ZSApp.getInstance().getTeacherId().equals("" + jsonObject.getInteger("creatorId")));
                schoolItem.setId(jsonObject.getInteger("dynamicId"));
                schoolItem.setPublish(DateUtil.getDescriptionTimeFromTimestampInSchool(jsonObject.getLong("createTime")));
                schoolItem.setFromname(jsonObject.getString("teacherName"));
                bListData.add(schoolItem);
            } else if (!jsonObject.getString("newsinfoId").equals("0.0")) {
                String images = jsonObject.getString("newInfoImageurls");
                List<String> sList1 = new ArrayList<>();
                SchoolItem schoolItem = new SchoolItem(SchoolItem.TYPE_NEWS, jsonObject.getString("title"), Arrays.asList(images.split(",")), jsonObject.getString("headImageurl"));
                schoolItem.setId(jsonObject.getInteger("newsinfoId"));
                schoolItem.setNewsUrl(jsonObject.getString("detailsUrl"));
                schoolItem.setPublish(DateUtil.getDescriptionTimeFromTimestamp(jsonObject.getLong("createTime")));
                schoolItem.setFromname(jsonObject.getString("teacherName"));
                bListData.add(schoolItem);
            } else if (!jsonObject.getString("recipeId").equals("0.0")) {
                String caidan = "";
                String[] old = jsonObject.getString("recipe").split("¨");
//                caidan= String.format(cook, old[0],old[1],old[2],old[3]);
                if (old.length > 0) {
                    caidan = caidan + "早餐：" + old[0] + "\n";
                }
                if (old.length > 1) {
                    caidan = caidan + "早点：" + old[1] + "\n";
                }
                if (old.length > 2) {
                    caidan = caidan + "午餐：" + old[2] + "\n";
                }
                if (old.length > 3) {
                    caidan = caidan + "午点：" + old[3] + "\n";
                }
//                JSONArray jsonArray1=jsonObject.getJSONArray("trecipeDay");
//                Map<Integer,String> cookmap=new HashMap<>();
//                for(int j=0;j<jsonArray1.size();j++) {
//                    JSONObject sonjsonObject=jsonArray1.getJSONObject(j);
//                    cookmap.put(sonjsonObject.getInteger("type"),sonjsonObject.getString("recipe"));
//                }
//                if(cookmap.containsKey(1)){
//                    caidan=caidan+"早餐："+cookmap.get(1)+"\n";
//                }
//                if(cookmap.containsKey(2)){
//                    caidan=caidan+"早点："+cookmap.get(2)+"\n";
//                }
//                if(cookmap.containsKey(3)){
//                    caidan=caidan+"午餐："+cookmap.get(3)+"\n";
//                }
//                if(cookmap.containsKey(4)){
//                    caidan=caidan+"午点："+cookmap.get(4)+"\n";
//                }
//                if(!caidan.equals("")){
//                    caidan=caidan.substring(0,caidan.length()-1);
//                }
                SchoolItem schoolItem;
                schoolItem = new SchoolItem(SchoolItem.TYPE_COOKBOOK, DateUtil.long2cookTime(jsonObject.getLong("recipeDate")), caidan, jsonObject.getString("headImageurl"));
                schoolItem.setPublish(DateUtil.getDescriptionTimeFromTimestamp(jsonObject.getLong("createTime")));
                schoolItem.setFromname(jsonObject.getString("teacherName"));
                schoolItem.setPublish(DateUtil.getDescriptionTimeFromTimestamp(jsonObject.getLong("createTime")));
                if(!caidan.equals(""))
                bListData.add(schoolItem);
            } else if (!jsonObject.getString("eventId").equals("0.0")) {
                EventModel event = JSONObject.parseObject(jsonObject.toJSONString(), EventModel.class);
                SchoolItem schoolitem = new SchoolItem(SchoolItem.TYPE_EVENT, event.getEventTitle(), String.format(eventstring, event.getEventAddress(), DateUtil.getFormatTimeFromTimestamp(event.getEventStartTime(), "yyyy年MM月dd日")), event, event.getHeadImageurl());
                schoolitem.setFromname(event.getTeacherName());
                schoolitem.setPublish(DateUtil.getDescriptionTimeFromTimestamp(event.getCreateTime()));
                bListData.add(schoolitem);
            }
        }
        bAdapter.notifyDataSetChanged();
        if (pageModel.getPageNum() != pageModel.getPages()) {
            bAdapter.setLoadingView(R.layout.footer_more);
        } else {
            bAdapter.setLoadEndView(R.layout.footer_loadend);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
        if (pageModel.getPageNum() != pageModel.getPages()) {
            bAdapter.setLoadingView(R.layout.footer_more);
        } else {
            bAdapter.setLoadEndView(R.layout.footer_loadend);
        }
    }

    @Override
    public void showData(Object o) {

    }

    public void showError(String err) {
        super.showError(err);
        bAdapter.setLoadEndView(R.layout.footer_loadend);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
