package com.quxue.common.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quxue.common.R;


/**
 * Created by houxg on 2015/7/9.
 */
public class ProgressDialog extends MessageDialog {

    public ProgressDialog(Context context, int themeId) {
        super(context, themeId);
    }

    public void setProgress(int progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.dialog_progress);
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
    }

    public void setMax(int max) {
        ProgressBar progress = (ProgressBar) findViewById(R.id.dialog_progress);
        if (progress != null) {
            progress.setMax(max);
        }
    }

    public void setProgressText(String text) {
        TextView textView = (TextView) findViewById(R.id.dialog_text_progress);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public static class Builder extends MessageDialog.Builder {

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
            return (Builder) super.setMessage(message);
        }

        public Builder setMessage(int messageId) {
            return (Builder) super.setMessage(messageId);
        }

        public Builder setMessageGravity(int gravity) {
            return (Builder) super.setMessageGravity(gravity);
        }

        @Deprecated
        public Builder setContentView(View v) {
            return this;
        }

        public Builder setNegativeButton(int textResId, OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(textResId, listener);
        }

        public Builder setNegativeButton(String negativeButtonText, OnButtonClickListener listener) {
            return (Builder) super.setNegativeButton(negativeButtonText, listener);
        }

        public Builder setPositiveButton(int positiveButtonText, OnButtonClickListener listener) {
            return (Builder) super.setPositiveButton(positiveButtonText, listener);
        }

        public Builder setPositiveButton(String positiveButtonText, OnButtonClickListener listener) {
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
        public ProgressDialog create() {
            // instantiate the dialog_message with the custom Theme
            setDialogLayoutId(R.layout.dialog_progress);
            ProgressDialog dialog = (ProgressDialog) super.create(new ProgressDialog(mContext, R.style.dialog));
            TextView message = (TextView) dialog.findViewById(R.id.dialog_message);
            if (messageText == null || message.equals("")) {
                message.setVisibility(View.GONE);
            } else {
                message.setText(messageText);
                message.setGravity(msgGravity);
            }
            return dialog;
        }
    }
}
