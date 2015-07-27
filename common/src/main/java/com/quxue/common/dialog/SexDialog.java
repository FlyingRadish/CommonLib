package com.quxue.common.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;

import com.quxue.common.R;

/**
 * 性别对话框
 * Created by Administrator on 2015/7/10.
 */
public class SexDialog extends BaseDialog {

    /**
     * 性别标识 0女 1男
     */
    static int sex;

    public SexDialog(Context context, int themeId) {
        super(context, themeId);
    }

    public int getSex() {
        return sex;
    }

    public static class Builder extends BaseDialog.Builder {
        int selectManOrWoman;
        String messageText = "";
        int msgGravity = Gravity.NO_GRAVITY;

        public Builder(Context context) {
            super(context);
        }

        public Builder setNoTitle() {
            return (Builder) super.setNoTitle();
        }

        public Builder setTitle(String title) {
            return (Builder) super.setTitle(title);
        }

        public Builder setTitle(int titleId) {
            return (Builder) super.setTitle(titleId);
        }

        public Builder setMessage(String message) {
            this.messageText = message;
            return this;
        }

        public Builder setMessage(int messageId) {
            this.messageText = (String) mContext.getText(messageId);
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            this.msgGravity = gravity;
            return this;
        }

        public Builder setSingleChoiceItme(int selectWitch) {
            selectManOrWoman = selectWitch;
            return this;
        }

        @Deprecated


        public Builder setContentView(View v) {
            return this;
        }

        public Builder setNegativeButton(int textResId, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(textResId, listener);
        }

        public Builder setNegativeButton(String negativeButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(negativeButtonText, listener);
        }

        public Builder setPositiveButton(int positiveButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setPositiveButton(positiveButtonText, listener);
        }

        public Builder setPositiveButton(String positiveButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setPositiveButton(positiveButtonText, listener);
        }

        public Builder setButtonInverse() {
            return (Builder) super.setButtonInverse();
        }

        public Builder setButtonBg(int buttonType, int resId) {
            return (Builder) super.setButtonBg(buttonType, resId);
        }

        public Builder setButtonTextColor(int buttonType, int colorId) {
            return (Builder) super.setButtonTextColor(buttonType, colorId);
        }

        public Builder setCancelable(boolean cancelable) {
            return (Builder) super.setCancelable(cancelable);
        }

        @SuppressWarnings({"deprecation"})
        public SexDialog create() {
            // instantiate the dialog_message with the custom Theme
            setDialogLayoutId(R.layout.dialog_sex);
            SexDialog dialog = (SexDialog) super.create(new SexDialog(mContext, R.style.dialog));
            final RadioButton radioBtn_man = (RadioButton) dialog.findViewById(R.id.radiobtn_men);

            final RadioButton radioBtn_woman = (RadioButton) dialog.findViewById(R.id.radiobtn_women);
            if (selectManOrWoman == 0) {
                radioBtn_man.setChecked(true);
                radioBtn_woman.setChecked(false);
            } else {
                radioBtn_man.setChecked(false);
                radioBtn_woman.setChecked(true);
            }
            radioBtn_man.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioBtn_woman.setChecked(false);
                    sex = 1;
                }
            });

            radioBtn_woman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioBtn_man.setChecked(false);
                    sex = 0;
                }
            });
            return dialog;
        }
    }
}
