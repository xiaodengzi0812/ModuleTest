package com.dengzi.dzframework.skin.attr;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 属性解析
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinAttrSupport {

    public static List<SkinAttr> getSkinAttr(Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrList = new ArrayList<>();
        int attrCount = attrs.getAttributeCount();
        for (int index = 0; index < attrCount; index++) {
            String attrName = attrs.getAttributeName(index);
            String attrValue = attrs.getAttributeValue(index);

            Log.e("dengzi", "attrName：" + attrName + "， attrValue：" + attrValue);
            // attrName：textColor， attrValue：@2131558476

            // 只获取我们关心的属性
            SkinType skinType = getSkinTypeByName(attrName);
            if (skinType != null) {
                String resName = getResNameByValue(context, attrValue);
                if (!TextUtils.isEmpty(resName)) {
                    SkinAttr skinAttr = new SkinAttr(resName, skinType);
                    skinAttrList.add(skinAttr);
                }
            }
        }
        return skinAttrList;
    }

    /**
     * 通过属性值来获取属性值对应的资源名称
     * attrValue：@2131558476
     *
     * @param attrValue
     * @return
     */
    private static String getResNameByValue(Context context, String attrValue) {
        if (attrValue.startsWith("@")) {
            attrValue = attrValue.substring(1);
            int resId = Integer.parseInt(attrValue);
            return context.getResources().getResourceName(resId);
        }
        return null;
    }

    /**
     * 通过属性名来获取SkinType
     * attrName：textColor
     *
     * @param attrName
     * @return
     */
    private static SkinType getSkinTypeByName(String attrName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if (skinType.getResName().equals(attrName)) {
                return skinType;
            }
        }
        return null;
    }

}
