package com.quxue.common.toolbox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by houxg on 2015/6/24.
 */
public class JsonTool {

    /**
     * 合并两个Json对象
     *
     * @param src 源
     * @param dsc 目标
     * @return 合并之后的Json对象
     */
    public static JSONObject merger(JSONObject src, JSONObject dsc) {
        Iterator iterator = src.keys();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            JSONArray jsonArray = src.optJSONArray(key);
            if (jsonArray != null) {
                try {
                    dsc.put(key, jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String val = src.getString(key);
                    dsc.put(key, val);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return dsc;
    }

    /**
     * 整形转换成布尔
     *
     * @param val
     * @return
     */
    public static boolean getBoolFromInt(int val) {
        return val != 0;
    }
}
