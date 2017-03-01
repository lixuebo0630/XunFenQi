package com.xunfenqi.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xunfenqi.R;

import java.util.ArrayList;

/**
 * 
 * @ClassName: SegmentButton
 * @Description: 分段控件
 * @author Xuebo Li
 * @date 2015-9-1 上午10:23:17
 * 
 */
public class SegmentButton2 extends LinearLayout implements View.OnClickListener {
	public OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(int position, Button button) {

		}
	};

	public void setOnCheckedChangeListener(
			OnCheckedChangeListener onCheckedChangeListener) {
		this.onCheckedChangeListener = onCheckedChangeListener;
	}

	private Context context;
	private ArrayList<Button> buttonList;
	private String[] contentStr;
	private int position = 0;

	private int textPressColor;
	private int textNormalColor;
	private int buttonCount;
	private float textSize;
	private String buttonContent;

	public SegmentButton2(Context context) {
		super(context);
		this.context = context;
	}

	public SegmentButton2(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.SegmentButton);
		textPressColor = typedArray.getColor(
				R.styleable.SegmentButton_textPressColor, Color.parseColor("#FF7632"));
		textNormalColor = typedArray.getColor(
				R.styleable.SegmentButton_textNormalColor,
				Color.parseColor("#262626"));
		textSize = typedArray.getFloat(R.styleable.SegmentButton_textSize, 16);
		buttonContent = typedArray
				.getString(R.styleable.SegmentButton_buttonContent);
		if (buttonContent != null && (!"".equals(buttonContent))) {
			contentStr = buttonContent.split(";");
			if (contentStr != null) {
				buttonCount = contentStr.length;
			} else {
				buttonCount = 0;
			}
		} else {
			buttonCount = 0;
		}
		setView();
	}

	private void setView() {
		buttonList = new ArrayList<Button>();
		if (buttonCount == 1) {
			Button button = new Button(context);
			button.setBackgroundDrawable(context.getResources().getDrawable(
					R.drawable.segment_sigle));
			button.setTextSize(textSize);
			button.setTextColor(textPressColor);
			button.setGravity(Gravity.CENTER);
			button.setPadding(0, 0, 0, 0);
			button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			button.setTag(0);
			this.addView(button);
			button.setOnClickListener(this);
			buttonList.add(button);
		} else {
			for (int i = 0; i < buttonCount; i++) {
				Button button = new Button(context);
				if (i == 0) {
					button.setBackgroundDrawable(context.getResources()
							.getDrawable(R.drawable.product_list_tab_selected_up));
					button.setTextColor(textPressColor);
				} else if (i == buttonCount - 1) {
					button.setBackgroundDrawable(context.getResources()
							.getDrawable(R.drawable.product_list_tab_unselected));
					button.setTextColor(textNormalColor);
				} else {
					button.setBackgroundDrawable(context.getResources()
							.getDrawable(R.drawable.product_list_tab_unselected));
					button.setTextColor(textNormalColor);
				}
				button.setTextSize(textSize);
				button.setGravity(Gravity.CENTER);
				button.setPadding(0, 0, 0, 0);
				button.setLayoutParams(new LayoutParams(0,
						LayoutParams.MATCH_PARENT, 1));
				button.setTag(i);

				this.addView(button);
				button.setOnClickListener(this);
				buttonList.add(button);
			}
		}

		for (int i = 0; i < buttonCount; i++) {
			buttonList.get(i).setText(contentStr[i]);
		}
	}

	public int getPosition() {
		return position;
	}

	@Override
	public void onClick(View v) {
		
		for (int i = 0; i < buttonCount; i++) {
			if (i == 0) {
				if (((Integer) v.getTag()) == i) {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_selected_up));
					buttonList.get(i).setTextColor(textPressColor);
					position = i;
					onCheckedChangeListener.onCheckedChanged(i,
							buttonList.get(i));
				} else {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_unselected));
					buttonList.get(i).setTextColor(textNormalColor);
				}
			} else if (i == buttonCount - 1) {
				if (((Integer) v.getTag()) == i) {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_selected_up));
					buttonList.get(i).setTextColor(textPressColor);
					position = i;
					onCheckedChangeListener.onCheckedChanged(i,
							buttonList.get(i));
				} else {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_unselected));
					buttonList.get(i).setTextColor(textNormalColor);
				}
			} else {
				if (((Integer) v.getTag()) == i) {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_selected_up));
					buttonList.get(i).setTextColor(textPressColor);
					position = i;
					onCheckedChangeListener.onCheckedChanged(i,
							buttonList.get(i));
				} else {
					buttonList.get(i).setBackgroundDrawable(
							context.getResources().getDrawable(
									R.drawable.product_list_tab_unselected));
					buttonList.get(i).setTextColor(textNormalColor);
				}
			}
		}
	}

	public Button getSegmentButton(int position) {
		return buttonList.get(position);
	}

	public interface OnCheckedChangeListener {
		public void onCheckedChanged(int position, Button button);
	}
}
