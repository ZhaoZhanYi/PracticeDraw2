package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(new CornerPathEffect(20));
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(new DiscretePathEffect(10, 10));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(new DashPathEffect(new float[] {20, 5}, 10));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        Path path1 = new Path();
        path1.rLineTo(20, 0);
        path1.rLineTo(10, 30);
        path1.close();
        paint.setPathEffect(new PathDashPathEffect(path1, 50, 20, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(this.path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        DashPathEffect effect1 = new DashPathEffect(new float[]{20, 5}, 10);
        DiscretePathEffect effect2 = new DiscretePathEffect(20, 10);
        SumPathEffect sumPathEffect = new SumPathEffect(effect1, effect2);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        DashPathEffect effect3 = new DashPathEffect(new float[]{20, 5}, 10);
        DiscretePathEffect effect4 = new DiscretePathEffect(20, 10);
        ComposePathEffect composePathEffect = new ComposePathEffect(effect3, effect4);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();
    }
}
