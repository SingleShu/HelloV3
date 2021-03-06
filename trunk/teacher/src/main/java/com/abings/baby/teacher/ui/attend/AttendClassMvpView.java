package com.abings.baby.teacher.ui.attend;

import com.hellobaby.library.data.model.BabyAttendancesModel;
import com.hellobaby.library.data.model.ClassModel;
import com.hellobaby.library.data.model.TeacherStudentAttendModel;
import com.hellobaby.library.ui.base.MvpView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */

public interface AttendClassMvpView extends MvpView {
    void showClassData(List<ClassModel> classmodels,String currentDate);
    //上午
    void showClass2Data(List<BabyAttendancesModel> babyAttendancesModels);
    void showClass3Data(List<TeacherStudentAttendModel> babyAttendancesModels);
    //下午
    void showClass4Data(List<BabyAttendancesModel> babyAttendancesModels);
    void finishSuccess();
}
