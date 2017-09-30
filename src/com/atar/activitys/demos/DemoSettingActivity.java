package com.atar.activitys.demos;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.enums.SkinMode;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.atar.activitys.AtarRefreshScrollViewActivity;
import com.atar.activitys.R;
import com.zf.view.UISwitchButton;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2017-09-30-上午10:26
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class DemoSettingActivity extends AtarRefreshScrollViewActivity implements OnCheckedChangeListener {

    private UISwitchButton common_ui_switch_button;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addScrollView(R.layout.activity_setting);
    }

    @Override
    protected void initScrollControl() {
        common_ui_switch_button = (UISwitchButton) findViewById(R.id.common_ui_switch_button);
        if (getCurrentSkinType() == SkinMode.DAY_MODE) {
            common_ui_switch_button.setChecked(false);
        } else {
            common_ui_switch_button.setChecked(true);
        }
        common_ui_switch_button.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int skinType = 0;
        if (isChecked) {
            skinType = SkinMode.NIGHT_MODE;
        } else {
            skinType = SkinMode.DAY_MODE;
        }
        AppConfigSetting.getInstance().putInt(SkinMode.SKIN_MODE_KEY, skinType);
        for (Activity activity : ActivityManager.getActivityManager().getActivityStack()) {
            if (activity instanceof CommonActivity) {
                ((CommonActivity) activity).loadSkin(skinType);
            }
        }
    }

    @Override
    public void ChangeSkin(int skinType) {
        super.ChangeSkin(skinType);
        SkinUtils.setTextColor(this, R.string.txt_day_black_night_greywhite_color, skinType, (TextView) findViewById(R.id.txt_yejianmoshi));
    }
}
