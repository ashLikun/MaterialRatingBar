/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.ashlikun.materialratingbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.view.Gravity;

import com.ashlikun.materialratingbar.internal.ThemeUtils;

public class MaterialRatingDrawable extends LayerDrawable {
    private boolean isNeedTint;//是否需要设置渲染，一般如果使用者在XML里面设置了background这边就为false

    public MaterialRatingDrawable(Context context, int backgroundResId, int secondaryProgressResId, int progressResid, boolean isNeedTint) {
        super(new Drawable[]{
                createLayerDrawable(backgroundResId, false, isNeedTint, context),
                createClippedLayerDrawable(secondaryProgressResId, true, isNeedTint,
                        context),
                createClippedLayerDrawable(progressResid, true, isNeedTint, context)
        });

        setId(0, android.R.id.background);
        setId(1, android.R.id.secondaryProgress);
        setId(2, android.R.id.progress);
    }

    public MaterialRatingDrawable(Context context, int backgroundResId, int secondaryProgressResId, int progressResid) {
        this(context, backgroundResId, secondaryProgressResId, progressResid, true);
    }

    private static Drawable createLayerDrawable(int tileResId, boolean tintAsActivatedElseNormal, boolean isNeedTint,
                                                Context context) {

        TileDrawable drawable = new TileDrawable(AppCompatResources.getDrawable(context,
                tileResId));
        if (isNeedTint) {
            int tintColor = ThemeUtils.getColorFromAttrRes(tintAsActivatedElseNormal ?
                    R.attr.colorControlActivated : R.attr.colorControlNormal, context);
            ((TintableDrawable) drawable).setTint(tintColor);
        }
        return drawable;
    }

    @SuppressLint("RtlHardcoded")
    private static Drawable createClippedLayerDrawable(int tileResId,
                                                       boolean tintAsActivatedElseNormal,
                                                       boolean isNeedTint,
                                                       Context context) {
        return new ClipDrawableCompat(createLayerDrawable(tileResId, tintAsActivatedElseNormal, isNeedTint,
                context), Gravity.LEFT, ClipDrawableCompat.HORIZONTAL);
    }

    public float getTileRatio() {
        Drawable drawable = getTileDrawableByLayerId(android.R.id.progress).getDrawable();
        return (float) drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
    }

    public void setStarCount(int count) {
        getTileDrawableByLayerId(android.R.id.background).setTileCount(count);
        getTileDrawableByLayerId(android.R.id.secondaryProgress).setTileCount(count);
        getTileDrawableByLayerId(android.R.id.progress).setTileCount(count);
    }

    @SuppressLint("NewApi")
    private TileDrawable getTileDrawableByLayerId(int id) {
        Drawable layerDrawable = findDrawableByLayerId(id);
        switch (id) {
            case android.R.id.background:
                return (TileDrawable) layerDrawable;
            case android.R.id.secondaryProgress:
            case android.R.id.progress: {
                ClipDrawableCompat clipDrawable = (ClipDrawableCompat) layerDrawable;
                return (TileDrawable) clipDrawable.getDrawable();
            }
            default:
                // Should never reach here.
                throw new RuntimeException();
        }
    }
}
