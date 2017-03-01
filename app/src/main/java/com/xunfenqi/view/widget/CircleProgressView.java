/**
 * Project Name:LianLianTest
 * File Name:CirleProgressView.java
 * Package Name:
 * Date:2015-8-26下午5:44:32
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.view.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xunfenqi.R;
import com.xunfenqi.utils.UIUtils;

/**
 * 
 * @ClassName: CircleProgressView
 * @Description: 自定义进度view,(动态进度条)
 * @author Xuebo Li
 * @date 2015-9-30 下午3:16:00
 * 
 */
public class CircleProgressView extends View {

	private Paint mPaintBackground; // 绘制背景圆环的画笔
	private Paint mPaintProgress; // 绘制背景进度的画笔
	private Paint mPaintText; // 绘制背景字体的画笔
	private int bgColor = Color.WHITE; // 背景圆环的颜色
	private int textColor = Color.parseColor("#3da4fe"); // 字体的颜色
	private int progressColor = Color.parseColor("#3da4fe"); // 进度条的颜色
	private float mStrokeWidth = 5;// 背景圆环的宽度
	private float mRadius = 30; // 背景圆环的半径
	private RectF rectPro;// 进度条的绘制外接矩形
	private int mProgress = 0; // 当前进度
	private int mMaxProgress = 100; // 最大进度
	private int mWidth, mHeight;
	private onProgressListener mOnProgressListener;

	private int mAProgress = 0;

	public int getmAProgress() {
		return mAProgress;
	}

	public void setmAProgress(int mAProgress) {
		this.mAProgress = mAProgress;
		invalidate();
	}

	public void setOnProgressListener(onProgressListener mOnProgressListener) {
		this.mOnProgressListener = mOnProgressListener;
	}

	/**
	 * 回调接口
	 * 
	 */
	public interface onProgressListener {
		/**
		 * 回调函数 当进度条满时调用此方法
		 */
		public void onEnd();

	}

	public CircleProgressView(Context context) {
		this(context, null);
	}

	public CircleProgressView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleProgressView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		if (attrs != null) {
			TypedArray ta = context.obtainStyledAttributes(attrs,
					R.styleable.CircleProgress);
			int count = ta.getIndexCount();
			for (int i = 0; i < count; i++) {
				int attr = ta.getIndex(i);
				switch (attr) {
				case R.styleable.CircleProgress_radius:
					mRadius = ta.getDimension(
							R.styleable.CircleProgress_radius, mRadius);
					break;
				case R.styleable.CircleProgress_strokeWidth:
					mStrokeWidth = ta.getDimension(
							R.styleable.CircleProgress_strokeWidth,
							mStrokeWidth);
					break;
				case R.styleable.CircleProgress_bgColor:
					bgColor = ta.getColor(R.styleable.CircleProgress_bgColor,
							bgColor);
					break;
				case R.styleable.CircleProgress_progressColor:
					progressColor = ta.getColor(
							R.styleable.CircleProgress_progressColor,
							progressColor);
					break;
				case R.styleable.CircleProgress_android_textColor:
					textColor = ta.getColor(
							R.styleable.CircleProgress_android_textColor,
							textColor);
					break;
				}
			}
			ta.recycle();
		}

		initPaint();
	}

	private void initPaint() {
		mPaintBackground = new Paint();
		mPaintBackground.setColor(bgColor);
		// 设置抗锯齿
		mPaintBackground.setAntiAlias(true);
		// 设置防抖动
		mPaintBackground.setDither(true);
		// 设置样式为环形
		mPaintBackground.setStyle(Style.STROKE);
		// 设置环形的宽度
		mPaintBackground.setStrokeWidth(mStrokeWidth);

		mPaintProgress = new Paint();

		mPaintProgress.setColor(Color.parseColor("#3da4fe"));

		// 设置抗锯齿
		mPaintProgress.setAntiAlias(true);
		// 设置防抖动
		mPaintProgress.setDither(true);
		// 设置样式为环形
		mPaintProgress.setStyle(Style.STROKE);
		// 设置环形的宽度
		mPaintProgress.setStrokeWidth(mStrokeWidth);

		// 设置线的类型,边是圆的
		mPaintProgress.setStrokeCap(Paint.Cap.ROUND);

		mPaintText = new Paint();
		mPaintText.setColor(textColor);
		// 设置抗锯齿
		mPaintText.setAntiAlias(true);

		mPaintText.setTextAlign(Paint.Align.CENTER);
		mPaintText.setTextSize(UIUtils.dip2px(14));

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		mWidth = getRealSize(widthMeasureSpec);
		mHeight = getRealSize(heightMeasureSpec);
		setMeasuredDimension(mWidth, mHeight);
	}

	private void initRect() {
		if (rectPro == null) {
			rectPro = new RectF();
			int viewSize = (int) (mRadius * 2);
			int left = (mWidth - viewSize) / 2;
			int top = (mHeight - viewSize) / 2;
			int right = left + viewSize;
			int bottom = top + viewSize;

			rectPro.set(left, top, right, bottom);
		}
	}

	private int getRealSize(int measureSpec) {
		int result = -1;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) { // 这两种模式需要自己计算
			result = (int) (mRadius * 2 + mStrokeWidth * 2);
		} else {
			result = size;
		}
		return result;
	}

	/**
	 * 设置进度
	 * 
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.mProgress = progress;
		invalidate();
	}

	public int getProgress() {
		return mProgress;
	}

	public void setMaxProgress(int mMaxProgress) {
		this.mMaxProgress = mMaxProgress;
		invalidate();
	}

	public int getMaxProgress() {
		return mMaxProgress;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		double angle = mProgress * 360 / (mMaxProgress * 1.0f); // 圆弧角度
		initRect();
		// 绘制背景圆环
		canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaintBackground);
		// 绘制进度条

		canvas.drawArc(rectPro, -90, (float) angle, false, mPaintProgress);
		// 绘制字体
		canvas.drawText( mAProgress + "%", mWidth / 2, mHeight / 2 + 8,
				mPaintText);

		if (mAProgress == mMaxProgress) {

			mProgress = 100;
			invalidate();
		} else if (mProgress < mAProgress) {
			mProgress += 1;
			invalidate();
		}

		// 当进度到达最大值时 调用此函数
		if (mOnProgressListener != null) {
			if (mProgress == mMaxProgress) {
				mOnProgressListener.onEnd();
			}
		}

	}

}
