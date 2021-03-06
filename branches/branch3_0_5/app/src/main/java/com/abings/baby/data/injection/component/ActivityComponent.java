package com.abings.baby.data.injection.component;

import android.content.Context;

import com.abings.baby.data.injection.module.ActivityModule;
import com.abings.baby.ui.Information.FavoriteActivity;
import com.abings.baby.ui.Information.InformationFragment;
import com.abings.baby.ui.Information.WebViewActivity;
import com.abings.baby.ui.album.AlbumActivity;
import com.abings.baby.ui.alert.AlertActivity;
import com.abings.baby.ui.changepwd.ChangePwdActivity;
import com.abings.baby.ui.contact.ContactsFragment;
import com.abings.baby.ui.event.EventDetailActivity;
import com.abings.baby.ui.event.EventListActivity;
import com.abings.baby.ui.login.LoginActivity;
import com.abings.baby.ui.login.create.CreateBabyActivity;
import com.abings.baby.ui.login.forgetpwd.ForgetPwdActivity;
import com.abings.baby.ui.login.needbaby.NeedBabyActivity;
import com.abings.baby.ui.login.register.RegisterActivity;
import com.abings.baby.ui.main.MainActivity;
import com.abings.baby.ui.main.fm.BabyFragment;
import com.abings.baby.ui.main.fm.MeFragment;
import com.abings.baby.ui.main.fm.SchoolFragment;
import com.abings.baby.ui.main.fm.aboutme.AboutMeBabyFragment;
import com.abings.baby.ui.main.fm.aboutme.AboutMeFamilyFragment;
import com.abings.baby.ui.main.fm.aboutme.AboutMeSettingFragment;
import com.abings.baby.ui.main.fm.school.SchoolAllFragment;
import com.abings.baby.ui.main.fm.school.SchoolCookbookFragment;
import com.abings.baby.ui.main.fm.school.SchoolDynamicFragment;
import com.abings.baby.ui.main.fm.school.SchoolEventFragment;
import com.abings.baby.ui.main.fm.school.SchoolNewsFragment;
import com.abings.baby.ui.measuredata.AddMeasureDataActivity;
import com.abings.baby.ui.measuredata.LineCharActivity;
import com.abings.baby.ui.measuredata.MeasureActivity;
import com.abings.baby.ui.measuredata.MeasureDetailActivity;
import com.abings.baby.ui.measuredata.remark.ReMarkActivity;
import com.abings.baby.ui.measuredata.teachingplan.TeachingPlanActivity;
import com.abings.baby.ui.message.MsgCenterActivity;
import com.abings.baby.ui.message.build.MsgBuildActivity;
import com.abings.baby.ui.message.fm.InBoxFragment;
import com.abings.baby.ui.message.fm.OutBoxFragment;
import com.abings.baby.ui.onlytext.EditTextActivity;
import com.abings.baby.ui.onlytext.TextActivity;
import com.abings.baby.ui.publish.PublishActivity;
import com.abings.baby.ui.publishpicture.PublishPictureActivity;
import com.abings.baby.ui.publishvideo.PublishVideoActivity;
import com.abings.baby.ui.publishvideo.VideoPlayActivity;
import com.abings.baby.ui.search.BabyIndexSearchActivity;
import com.abings.baby.ui.search.InformationSearchActivity;
import com.abings.baby.ui.search.SearchActivity;
import com.hellobaby.library.injection.ActivityContext;
import com.hellobaby.library.injection.PerActivity;
import com.hellobaby.library.injection.component.BaseActivityComponent;
import com.hellobaby.library.ui.alert.AlertDetailActivity;
import com.hellobaby.library.ui.alert.BaseAlertActivity;

import dagger.Component;

/**
 * Created by zwj on 2016/9/27.
 * description :
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent extends BaseActivityComponent {
    @ActivityContext
    Context context();

    void inject(MainActivity mainActivity);

    void inject(BabyFragment babyFragment);

    void inject(SchoolFragment schoolFragment);

    void inject(MeFragment meFragment);

    void inject(LoginActivity loginActivity);

    void inject(PublishActivity publishActivity);

    void inject(PublishPictureActivity publishPictureActivity);

    void inject(PublishVideoActivity publishVideoActivity);

    void inject(AboutMeBabyFragment publishPictureActivity);

    void inject(AboutMeFamilyFragment publishPictureActivity);

    void inject(AboutMeSettingFragment publishPictureActivity);

    void inject(BaseAlertActivity publishPictureActivity);

    void inject(AlertActivity alertActivity);

    void inject(AlertDetailActivity publishPictureActivity);

    void inject(MsgCenterActivity publishPictureActivity);

    void inject(LineCharActivity lineCharActivity);

    void inject(MeasureActivity measureActivity);

    void inject(MeasureDetailActivity measureActivity);

    void inject(AddMeasureDataActivity measureActivity);


    void inject(SchoolAllFragment schoolAllFragment);

    void inject(SchoolNewsFragment newsFragment);

    void inject(SchoolDynamicFragment dynamicFragment);

    void inject(SchoolCookbookFragment cookbookFragment);

    void inject(SchoolEventFragment eventFragment);

    void inject(AlbumActivity albumActivity);

    void inject(CreateBabyActivity albumActivity);

    void inject(ContactsFragment contactsFragment);

    void inject(TextActivity albumActivity);

    void inject(EditTextActivity albumActivity);

    void inject(EventListActivity albumActivity);

    void inject(EventDetailActivity albumActivity);

    void inject(RegisterActivity registerActivity);

    void inject(ReMarkActivity reMarkActivity);

    void inject(InBoxFragment reMarkActivity);

    void inject(OutBoxFragment reMarkActivity);

    void inject(MsgBuildActivity reMarkActivity);

    void inject(VideoPlayActivity videoPlayActivity);

    void inject(ForgetPwdActivity forgetPwdActivity);

    void inject(ChangePwdActivity changePwdActivity);

    void inject(InformationFragment changePwdActivity);

    void inject(FavoriteActivity changePwdActivity);

    void inject(WebViewActivity changePwdActivity);

    void inject(TeachingPlanActivity changePwdActivity);

    void inject(BabyIndexSearchActivity changePwdActivity);

    void inject(SearchActivity changePwdActivity);

    void inject(InformationSearchActivity changePwdActivity);

    void inject(NeedBabyActivity needBabyActivity);
}
