package com.quxue.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.quxue.common.R;


/**
 * Created by houxg on 2015/7/9.
 */
public class EditDialog extends MessageDialog {

    public EditDialog(Context context, int themeId) {
        super(context, themeId);
    }

    public String getEditContent() {
        EditText edit = (EditText) findViewById(R.id.dialog_edit);
        if (edit != null) {
            return edit.getText().toString();
        } else {
            return null;
        }
    }

    public static class Builder extends MessageDialog.Builder {

        String editContent = "";

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

        public Builder setEditContent(@NonNull String text) {
            this.editContent = text;
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
        public EditDialog create() {
            // instantiate the dialog_message with the custom Theme
            setDialogLayoutId(R.layout.dialog_edit);
            EditDialog dialog = (EditDialog) super.create(new EditDialog(mContext, R.style.dialog));
            TextView message = (TextView) dialog.findViewById(R.id.dialog_message);
            if (messageText == null || message.equals("")) {
                message.setVisibility(View.GONE);
            } else {
                message.setText(messageText);
                message.setGravity(msgGravity);
            }
            EditText edit = (EditText) dialog.findViewById(R.id.dialog_edit);
            if (edit != null) {
                edit.setText(editContent);
                edit.setSelection(editContent.length());
            }
            return dialog;
        }
    }
}
