/*
 * Copyright (C) 2018 Jian Yang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.likaixuan.recognitionsystem.particles.particle_ring;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.likaixuan.recognitionsystem.utils.UIUtils;

import java.util.Random;

/**
 * 说明：爆破粒子，每个移动与渐变的小块
 */
class ParticleModel {
    // 随机数，随机出位置和大小
    static Random random = new Random();
    // 默认小球宽高
    static final int PART_WH = UIUtils.dp2px(2);
    static final float r = UIUtils.dp2px(0.82);

    //center x of circle
    float cx;
    //center y of circle
    float cy;
    private final float x, y;
    // 半径
    float radius;
    // 颜色
    int color;
    // 透明度
    float alpha;
    // 整体边界
    Rect mBound;

    ParticleModel(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽
        this.mBound = bound;
        this.color = color;
        this.alpha = 1f;
        this.radius = r;
        this.cx = bound.left + PART_WH * column;
        this.cy = bound.top + PART_WH * row;
        x = cx;
        y = cy;
    }

    // 每一步动画都得重新计算出自己的状态值
    void advance(float factor) {

         //控制粒子运动的幅度
        if (cx - x > -3 && cx - x < 3) {
            cx = cx + random.nextFloat() - 0.5f;
            if (cx - x < -3)
                cx++;
            if (cx - x > 3)
                cx--;
        }
        if (cy - y > -5 && cy - y < 5) {
            cy = cy + random.nextFloat() - 0.5f;
            if (cy - y < -5)
                cy++;
            if (cy - y > 5)
                cy--;
        }
        radius = r;
        alpha = 1;
    }
}
