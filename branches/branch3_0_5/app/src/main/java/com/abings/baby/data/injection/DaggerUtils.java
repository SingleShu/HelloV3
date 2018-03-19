package com.abings.baby.data.injection;

import android.app.Activity;

import com.abings.baby.ZSApp;
import com.abings.baby.data.injection.component.ActivityComponent;
import com.abings.baby.data.injection.component.DaggerActivityComponent;
import com.abings.baby.data.injection.module.ActivityModule;
import com.hellobaby.library.injection.component.BaseActivityComponent;

/**
 * Created by zwj on 2016/11/22.
 * description :
 */

public class DaggerUtils {
    public static ActivityComponent getActivityComponent(BaseActivityComponent mActivityComponent, Activity activity) {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(activity))
                    .applicationComponent(ZSApp.getInstance().getComponent())
                    .build();
        }
        return (ActivityComponent) mActivityComponent;
    }
}
