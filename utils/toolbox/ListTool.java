package org.houxg.pixiurss.utils.toolbox;

import java.util.List;

/**
 * List工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/9/21
 */
public class ListTool {
    /**
     * 合并两个List, 两个
     * @param base
     * @param src
     * @param start
     * @param end
     */
    public static void mergeList(List base, List src, int start, int end) {
        int range = end - start + 1;
        boolean shouldDelete = range >= src.size();
        int index = start;
        int changeEnd = index + (shouldDelete ? src.size() : range);
        int deleteEnd = index + range;
        int itemIndex = 0;
        for (; index < changeEnd; index++) {
            base.set(index, src.get(itemIndex));
            itemIndex++;
        }
        if (shouldDelete) {
            int deleteIndex = index;
            for (; index < deleteEnd; index++) {
                base.remove(deleteIndex);
            }
        } else {
            List<Object> addItems = src.subList(itemIndex, src.size());
            base.addAll(index, addItems);
        }
    }
}
