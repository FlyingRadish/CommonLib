package com.quxue.common;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quxue.common.customView.Divider;

import java.util.List;

/**
 * Created by houxg on 2015/7/13.
 */
public class TitleManager {
    Activity activity;

    public TitleManager(Activity activity) {
        this.activity = activity;
    }

    public void initTitle(List<View> actionItems, View.OnClickListener backListener) {
        setTitle(activity.getTitle());
        View view = activity.findViewById(R.id.btn_back);
        if (view != null) {
            view.setOnClickListener(backListener);
        }
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.panel_title_action);
        if (linearLayout == null) {
            return;
        }
        if (actionItems == null || actionItems.size() == 0) {
            return;
        }
        for (View v : actionItems) {
            linearLayout.addView(v, v.getLayoutParams());
        }
    }

    public void setTitle(CharSequence title) {
        TextView textView = (TextView) activity.findViewById(R.id.text_activity_title);
        if (textView != null) {
            textView.setText(title);
        }
    }

    public TextView getTitleView() {
        return (TextView) activity.findViewById(R.id.text_activity_title);
    }

    public void setTitleBackgroundResources(int colorId) {
        View titlePanel = activity.findViewById(R.id.panel_activity_title);
        if (titlePanel != null) {
            titlePanel.setBackgroundResource(colorId);
        }
    }

    public View getTitlePanel() {
        return activity.findViewById(R.id.panel_activity_title);
    }

    public void setTitleFontColor(int colorId) {
        TextView title = (TextView) activity.findViewById(R.id.text_activity_title);
        if (title != null) {
            title.setTextColor(activity.getResources().getColor(colorId));
        }

    }

    protected ImageButton getBackButton() {
        return (ImageButton) activity.findViewById(R.id.btn_back);
    }

    protected Button getRightButton() {
        return (Button) activity.findViewById(R.id.btn_title_right);
    }

    public void setLeftActionVisibility(boolean isHide) {
        View view = activity.findViewById(R.id.btn_back);
        if (view == null) {
            return;
        }
        if (isHide) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

<<<<<<< .mine
    public void setRightButtonVisibility() {
=======
    public void setRightButtonVisible(){
>>>>>>> .r444
        View view = activity.findViewById(R.id.btn_title_right);
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
<<<<<<< .mine
=======
    }

>>>>>>> .r444
    public void setRightButtonText(String text){
        View view = activity.findViewById(R.id.btn_title_right);
        if (view == null) {
            return;
        }
        ((Button)view).setText(text);
    }


    protected Button getActionItem(int id, String text) {
        Resources res = activity.getResources();
        Button button = new Button(activity, null);
        button.setId(id);
        button.setText(text);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.title_action_text_size));
        button.setBackgroundResource(R.drawable.btn_transparent_black);
        button.setTextColor(activity.getResources().getColor(R.color.white));
        int padding = (int) res.getDimension(R.dimen.title_action_padding);
        button.setPadding(padding, 0, padding, 0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(lp);
        return button;
    }

    protected ImageButton getActionItem(int id, int drawableId) {
        Resources res = activity.getResources();
        ImageButton button = new ImageButton(activity, null);
        button.setId(id);
        button.setImageResource(drawableId);
        button.setBackgroundResource(R.drawable.btn_transparent_black);
        int padding = (int) res.getDimension(R.dimen.title_action_padding);
        button.setPadding(padding, 0, padding, 0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(lp);
        return button;
    }

    protected View getActionDivider(int colorId) {
        Resources res = activity.getResources();
        Divider v = new Divider(activity);
        v.init(res.getColor(colorId), 0);
        int padding = (int) res.getDimension(R.dimen.title_action_divider_padding);
        v.setPadding(0, padding, 0, padding);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) res.getDimension(R.dimen.title_action_divider_wid), ViewGroup.LayoutParams.MATCH_PARENT);
        int margin = (int) res.getDimension(R.dimen.title_action_divider_margin);
        lp.setMargins(margin, 0, margin, 0);
        v.setLayoutParams(lp);
        return v;
    }


}
