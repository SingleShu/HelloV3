package com.hellobaby.library.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.hellobaby.library.R;
import com.hellobaby.library.data.model.ClassModel;
import com.hellobaby.library.data.model.TeacherModel;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by zwj on 2016/10/17.
 * 底部对话框工具类
 */
public class BottomDialogUtils {

//    /**
//     * 底部的对话框
//     * @param activity
//     * @param items 选项
//     * @param onItemClickListener 每个item点击的监听
//     * @return
//     */
//    public static CustomSheetDialog bottomListDialog(Activity activity, String[] items, @NonNull final OnItemClickListener onItemClickListener) {
//        final CustomSheetDialog dialog = new CustomSheetDialog(activity, items, null);
//        dialog.isTitleShow(false).isCancelShow(false)
//                .itemTextColor(activity.getResources().getColor(android.R.color.white))
//                .itemPressColor(activity.getResources().getColor(android.R.color.holo_orange_light))
//                .lvBgColor(activity.getResources().getColor(android.R.color.darker_gray))
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                onItemClickListener.onItemClick(parent,view,position,id);
//                dialog.dismiss();
//            }
//        });
//        return dialog;
//    }
//
//    public interface OnItemClickListener{
//        void onItemClick(AdapterView<?> parent, View view, int position, long id);
//    }


    public static CustomActionSheetDialog getBottomListDialog(Activity activity, final String[] items, @NonNull final OnItemClickListener onItemClickListener) {
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, items, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, items[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }


    public interface OnItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, String item, long id);
    }

    //是否删除
    public static CustomActionSheetDialog getBottomDynamicDelDialog(Activity activity, @NonNull final OnItemClickListener onItemClickListener) {
        final String items[] = {"是", "否"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, items, null);
        dialog.isTitleShow(true).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .title("是否确认删除")
                .titleTextColor(R.color.black)
                .titleTextSize_SP(17.5f)
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, items[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    //选择周
    public static CustomActionSheetDialog getBottomWeekDialog(Activity activity, final String[] items, @NonNull final OnItemClickListener onItemClickListener) {
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, items, null);
        dialog.isTitleShow(true).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .title("请选择日期")
                .titleTextColor(R.color.black)
                .titleTextSize_SP(17.5f)
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, items[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }

//    public static CustomActionSheetDialog getBottomListDialog(Activity activity, final List<String> items, @NonNull final OnItemClickListener onItemClickListener) {
//        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, items, null);
//        dialog.isTitleShow(false).isCancelShow(false)
//                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
//                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
//                .lvBgColor(activity.getResources().getColor(R.color.transparent))
//                .dividerColor(activity.getResources().getColor(R.color.transparent))
//                .dividerHeight(0)
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                onItemClickListener.onItemClick(parent, view, position, items.get(position), id);
//                dialog.dismiss();
//            }
//        });
//        return dialog;
//    }

    /**
     * 性别选择，0：男，1：女
     *
     * @param activity
     * @param onItemClickListener
     * @return
     */
    public static CustomActionSheetDialog getBottomGenderDialog(Activity activity, @NonNull final OnItemClickListener onItemClickListener) {
        final String[] genders = {"男孩", "女孩"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, genders[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    /**
     * @param activity
     * @param onItemClickListener
     * @return
     */
    public static CustomActionSheetDialog getBottomAgreeDialog(Activity activity, String title, @NonNull final OnItemClickListener onItemClickListener) {
        final String[] genders = {"同意", "拒绝"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders);
        dialog.isTitleShow(true).title(title)
                .isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvItembgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setCancelable(false);
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, genders[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public interface OnItemClickListener2 {
        void onItemClick(AdapterView<?> parent, View view, int position, MyDialogMenuItem items, long id);
    }

    /**
     * 主要是生日的选择器
     *
     * @param activity
     * @param birthday              2016年7月25日;如果为null,则默认今天的日期
     * @param onDateChangedListener
     * @return
     */
    public static BottomPickerDateDialog getBottomDatePickerDialog(Activity activity, String birthday, boolean needmax, BottomPickerDateDialog.BottomOnDateChangedListener onDateChangedListener) {
        int year = -1;
        int month = -1;
        int day = -1;

        if (birthday != null && !birthday.isEmpty() && birthday.contains("年") && birthday.contains("月") && birthday.contains("日") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("年")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("年") + 1, birthday.indexOf("月")));
            day = Integer.valueOf(birthday.substring(birthday.indexOf("月") + 1, birthday.indexOf("日")));
        }
        if (birthday != null && !birthday.isEmpty() && birthday.contains("-") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("-")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("-") + 1, birthday.lastIndexOf("-")));
            day = Integer.valueOf(birthday.substring(birthday.lastIndexOf("-") + 1, birthday.length()));
        }

        final BottomPickerDateDialog dialog = new BottomPickerDateDialog(activity, null, year, month, day, needmax, onDateChangedListener);
        dialog.show();
        return dialog;
    }

    /**
     * 主要是体重身高生日的选择器
     *
     * @param activity
     * @param birthday              2016年7月25日;如果为null,则默认今天的日期
     * @param onDateChangedListener
     * @return
     */
    public static BottomPickerDateDialog getHeightBottomDatePickerDialog(Activity activity, String birthday, boolean needmax, String minday, BottomPickerDateDialog.BottomOnDateChangedListener onDateChangedListener) {
        int year = -1;
        int month = -1;
        int day = -1;

        if (birthday != null && !birthday.isEmpty() && birthday.contains("年") && birthday.contains("月") && birthday.contains("日") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("年")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("年") + 1, birthday.indexOf("月")));
            day = Integer.valueOf(birthday.substring(birthday.indexOf("月") + 1, birthday.indexOf("日")));
        }
        if (birthday != null && !birthday.isEmpty() && birthday.contains("-") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("-")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("-") + 1, birthday.lastIndexOf("-")));
            day = Integer.valueOf(birthday.substring(birthday.lastIndexOf("-") + 1, birthday.length()));
        }

        final BottomPickerDateDialog dialog = new BottomPickerDateDialog(activity, null, year, month, day, needmax, minday, onDateChangedListener);
        dialog.show();
        return dialog;
    }

    /**
     * 主要是活动生日的选择器
     *
     * @param activity
     * @param birthday              2016年7月25日;如果为null,则默认今天的日期
     * @param onDateChangedListener
     * @return
     */
    public static BottomPickerDateDialog getEvnetBottomDatePickerDialog(Activity activity, String birthday, boolean needmax, BottomPickerDateDialog.BottomOnDateChangedListener onDateChangedListener) {
        int year = -1;
        int month = -1;
        int day = -1;
        String minday;
        if (birthday != null && !birthday.isEmpty() && birthday.contains("年") && birthday.contains("月") && birthday.contains("日") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("年")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("年") + 1, birthday.indexOf("月")));
            day = Integer.valueOf(birthday.substring(birthday.indexOf("月") + 1, birthday.indexOf("日")));
        }
        if (birthday != null && !birthday.isEmpty() && birthday.contains("-") && !birthday.equals("")) {
            year = Integer.valueOf(birthday.substring(0, birthday.indexOf("-")));
            month = Integer.valueOf(birthday.substring(birthday.indexOf("-") + 1, birthday.lastIndexOf("-")));
            day = Integer.valueOf(birthday.substring(birthday.lastIndexOf("-") + 1, birthday.length()));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        minday = sdf.format(new Date());
        final BottomPickerDateDialog dialog = new BottomPickerDateDialog(activity, null, year, month, day, needmax, minday, onDateChangedListener);
        dialog.show();
        return dialog;
    }

    /**
     * 关系选择
     *
     * @param activity
     * @param onItemClickListener
     * @return
     */
    public static CustomActionSheetDialog getBottomRelDialog(Activity activity, @NonNull final OnItemClickListener onItemClickListener) {
        final String[] genders = {"爸爸", "妈妈", "爷爷", "奶奶", "外公", "外婆", "叔叔", "阿姨", "其他"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, genders[position], id);
                dialog.dismiss();
            }
        });
        return dialog;
    }


    /**
     * 退出编辑界面
     *
     * @param activity
     * @return
     */
    public static CustomActionSheetDialog getBottomExitEditDialog(final Activity activity) {
        final String[] genders = {"是否放弃本次编辑？", "放弃", "继续编辑"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    //继续编辑
                    dialog.dismiss();
                } else if (position == 1) {
                    dialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activity.finish();
                        }
                    }, 350);
                    //放弃
                }
            }
        });
        return dialog;
    }
    /**
     * 退出编辑界面
     *
     * @param activity
     * @return
     */
    public static CustomActionSheetDialog getBottomPrizeDrawDialog(final Activity activity, String title, String agree, String disagree, final onSureAndCancelClick onSureClick) {
        final String[] genders = {title, agree, disagree};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                //否
                if (position == 2) {
                    dialog.dismiss();
                    onSureClick.onCancel();
                    //是
                } else if (position == 1) {
                    dialog.dismiss();
                    onSureClick.onItemClick();
                }
            }
        });
        return dialog;
    }
    /**
     * 是否删除邮件
     *
     * @param activity
     * @return
     */
    public static CustomActionSheetDialog getBottomdeleteMsgDialog(final Activity activity, String agree, String disagree, final onSureAndCancelClick onSureClick) {
        final String[] genders = { agree, disagree};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                //否
                if (position == 1) {
                    dialog.dismiss();
                    onSureClick.onCancel();
                    //是
                } else if (position == 0) {
                    dialog.dismiss();
                    onSureClick.onItemClick();
                }
            }
        });
        return dialog;
    }

    /**
     * 是否删除咨询
     *
     * @param activity
     * @return
     */
    public static CustomActionSheetDialog getBottomdeleteDialog(final Activity activity, String disagree, final onSureClick sureClick) {
        final String[] genders = { disagree};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                sureClick.onItemClick();
            }
        });
        return dialog;
    }

    public interface onSureClick{
        void onItemClick();
    }

    public interface onSureAndCancelClick{
        void onItemClick();
        void onCancel();
    }

    public interface OnClickExitEditClickListener {
        /**
         * @param bool false: 继续编辑; true:放弃
         */
        void onClickExitEdit(boolean bool);
    }

    public static CustomActionSheetDialog getBottomExitEditDialog(final Activity activity, final OnClickExitEditClickListener onClickExitEditClickListener) {
        final String[] genders = {"是否放弃本次编辑？", "放弃", "继续编辑"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 2) {
                    //继续编辑
                    if (onClickExitEditClickListener != null) {
                        onClickExitEditClickListener.onClickExitEdit(false);
                    }
                    dialog.dismiss();
                } else if (position == 1) {
                    onClickExitEditClickListener.onClickExitEdit(true);
                    dialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activity.finish();
                        }
                    }, 350);
                    //放弃
                }

            }
        });
        return dialog;
    }

    /**
     * 选择老师
     *
     * @param activity
     * @param teacherModels
     * @param onItemClickListener
     * @return
     */
    public static CustomActionSheetDialog getTeacherBottomDialog(Activity activity, final List<TeacherModel> teacherModels, @NonNull final OnTeacherItemClickListener onItemClickListener) {
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, null, teacherModels);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.et_text_color))
                .itemPressColor(activity.getResources().getColor(R.color.et_text_color))
                .lvBgColor(activity.getResources().getColor(R.color.white))
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(parent, view, position, teacherModels.get(position), id);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public interface OnTeacherItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, TeacherModel item, long id);
    }

    /**
     * 选择班级
     *
     * @param activity
     * @param classModels
     * @param listener
     * @return
     */
    public static BottomSelecteClasssDialog getSelectedClassBottomDialog(Activity activity, final List<ClassModel> classModels, @NonNull final BottomSelecteClasssDialog.SelectedClassListener listener) {
        BottomSelecteClasssDialog dialog = new BottomSelecteClasssDialog(activity, classModels, listener);
        dialog.show();
        return dialog;
    }


    /**
     * 退出编辑界面
     *
     * @param activity
     * @return
     */
    public static CustomActionSheetDialog getBottomBabyCareDialog(String content, final Context activity) {
        final String[] genders = {content, "是", "否"};
        final CustomActionSheetDialog dialog = new CustomActionSheetDialog(activity, genders, null);
        dialog.isTitleShow(false).isCancelShow(false)
                .itemTextColor(activity.getResources().getColor(R.color.white))
                .itemPressColor(activity.getResources().getColor(R.color.black))
                .lvItembgColor(activity.getResources().getColor(R.color.status_view))
                .show();
        dialog.setCancelable(false);
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    //继续编辑
                    dialog.dismiss();
                } else if (position == 1) {
                    dialog.dismiss();
                    //放弃
                }
            }
        });
        return dialog;
    }

    public static TimePickerDialog getBottomTimePicker(Context context, OnDateSetListener onDateSetListener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        return new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(context.getResources().getColor(R.color.white))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12)
                .build();
    }

}
