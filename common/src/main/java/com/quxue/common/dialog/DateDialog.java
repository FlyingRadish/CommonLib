package com.quxue.common.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;

import com.quxue.common.R;

import java.util.Calendar;

/**
 * 日期对话框
 * Created by Administrator on 2015/7/10.
 */
public class DateDialog extends BaseDialog {

    static Calendar calendar;

    public DateDialog(Context context, int themeId) {
        super(context, themeId);
    }

    public Calendar getDate() {
        return calendar;
    }

    public static class Builder extends BaseDialog.Builder {
        String date;
        int year;
        int month;
        int day;

        String messageText = "";
        int msgGravity = Gravity.NO_GRAVITY;

        public Builder(Context context) {
            super(context);
            calendar = Calendar.getInstance();
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

        public Builder setDate(String date) {
            this.date = date;
            year = Integer.valueOf(date.substring(0, 4));
            month = Integer.valueOf(date.substring(5, 7));
            day = Integer.valueOf(date.substring(8, 10));
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
        public DateDialog create() {
            // instantiate the dialog_message with the custom Theme
            setDialogLayoutId(R.layout.dialog_date);
            DateDialog dialog = (DateDialog) super.create(new DateDialog(mContext, R.style.dialog));
            final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.picker_date);
            datePicker.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DATE, dayOfMonth);
                }
            });
            return dialog;
        }
    }

}