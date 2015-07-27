package com.quxue.common.customView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by houxg on 2015/7/7.
 */
public class CounterTextView extends TextView {

    private static final int COUNTING = 1;
    private static final int NORMAL = 2;
    private static final int RESET_TO_COUNT = 3;

    String formater = "%d";
    Counterhandler handler;
    Counter counter;

    int normalBgId;
    String normalText;
    int normalTextColor;

    int countingBgId;
    int countingTextColor;

    public CounterTextView(Context context) {
        super(context);
        handler = new Counterhandler();
    }

    public CounterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handler = new Counterhandler();
    }

    public void reset(int timeInSec) {
        cancel();
        handler.obtainMessage(RESET_TO_COUNT, timeInSec, 0).sendToTarget();
        counter = new Counter(timeInSec);
        new Thread(counter).start();
    }

    private void cancel() {
        if (counter != null) {
            counter.cancel();
        }
    }

    public void setNormal() {
        cancel();
        handler.obtainMessage(NORMAL).sendToTarget();
    }

    public void setParams(int normalBgId, String normalText, int normalTextColor, int coutingBgId, String countingFormater, int coutingTextColor) {
        this.normalBgId = normalBgId;
        this.normalText = normalText;
        this.normalTextColor = normalTextColor;
        this.countingBgId = coutingBgId;
        this.formater = countingFormater;
        this.countingTextColor = coutingTextColor;
    }

    public boolean isCounting() {
        return counter != null && !counter.isCancel;
    }

    //TODO:leak
    class Counterhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String text;
            switch (msg.what) {
                case COUNTING:
                    text = String.format(formater, msg.arg1);
                    setText(text);
                    break;
                case NORMAL:
                    setText(normalText);
                    setTextColor(normalTextColor);
                    setBackgroundResource(normalBgId);
                    break;
                case RESET_TO_COUNT:
                    text = String.format(formater, msg.arg1);
                    setText(text);
                    setTextColor(countingTextColor);
                    setBackgroundResource(countingBgId);
                    break;
            }
        }
    }

    class Counter implements Runnable {

        int time;
        boolean isCancel = false;

        public Counter(int time) {
            this.time = time;
        }

        public void cancel() {
            isCancel = true;
        }

        @Override
        public void run() {
            while (--time > 0) {
                if (isCancel || handler == null) {
                    return;
                }
                handler.obtainMessage(COUNTING, time, 0).sendToTarget();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.obtainMessage(NORMAL).sendToTarget();
            isCancel = true;
        }
    }

}
