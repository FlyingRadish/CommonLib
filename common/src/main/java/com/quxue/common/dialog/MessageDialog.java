package com.quxue.common.dialog;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.quxue.common.R;


/**
 * Des:
 * Created by houxg on 2015/2/10.
 */
public class MessageDialog extends BaseDialog {


    public MessageDialog(Context context, int themeId) {
        super(context, themeId);
    }

    public static class Builder extends BaseDialog.Builder {

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

        public Builder setTitle(@StringRes int titleId) {
            return (Builder) super.setTitle(titleId);
        }

        public Builder setMessage(String message) {
            this.messageText = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.messageText = (String) mContext.getText(messageId);
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            this.msgGravity = gravity;
            return this;
        }

        @Deprecated
        public Builder setContentView(View v) {
            return this;
        }

        public Builder setNegativeButton(@StringRes int textResId, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(textResId, listener);
        }

        public Builder setNegativeButton(String negativeButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(negativeButtonText, listener);
        }

        public Builder setPositiveButton(@StringRes int positiveButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setPositiveButton(positiveButtonText, listener);
        }

        public Builder setPositiveButton(String positiveButtonText, BaseDialog.OnButtonClickListener listener) {
            return (Builder) super.setPositiveButton(positiveButtonText, listener);
        }

        public Builder setButtonInverse() {
            return (Builder) super.setButtonInverse();
        }

        public Builder setButtonBg(int buttonType, @ColorRes int resId) {
            return (Builder) super.setButtonBg(buttonType, resId);
        }

        public Builder setButtonTextColor(int buttonType, @ColorRes int colorId) {
            return (Builder) super.setButtonTextColor(buttonType, colorId);
        }

        public Builder setCancelable(boolean cancelable) {
            return (Builder) super.setCancelable(cancelable);
        }

        @SuppressWarnings({"deprecation"})
        public MessageDialog create() {
            // instantiate the dialog_message with the custom Theme
            setDialogLayoutId(R.layout.dialog_message);
            MessageDialog dialog = (MessageDialog) super.create(new MessageDialog(mContext, R.style.dialog));
            TextView message = (TextView) dialog.findViewById(R.id.dialog_message);
            message.setText(messageText);
            message.setGravity(msgGravity);
            return dialog;
        }
    }

}