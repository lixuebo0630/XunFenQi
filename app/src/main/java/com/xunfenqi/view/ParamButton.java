package com.xunfenqi.view;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class ParamButton extends AppCompatButton {

	private String mParam;

	public ParamButton(Context context) {
		super(context);
	}

	public ParamButton(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		// TODO Auto-generated constructor stub
	}

	public void setStringParam(String p) {
		mParam = p;
	}

	public String getStringParam() {
		return mParam;
	}
}