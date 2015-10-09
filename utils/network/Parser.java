package org.houxg.pixiurss.utils.network;

import com.android.volley.ParseError;

import org.houxg.pixiurss.utils.logger.Log;
import org.json.JSONObject;

/**
 * 请求解析器
 */
public abstract class Parser<T> {
    private static final String TAG = Parser.class.getSimpleName();

    public T parse(JSONObject jsonObject) throws ParseError, FailedError {
        Log.i(TAG, "parsing data=" + jsonObject.toString());
        return parseToModel(jsonObject);
    }

    /**
     * 将Json解析为对应的Model
     * @param rsp
     * @return
     * @throws ParseError
     * @throws FailedError
     */
    protected abstract T parseToModel(JSONObject rsp) throws ParseError, FailedError;
}
