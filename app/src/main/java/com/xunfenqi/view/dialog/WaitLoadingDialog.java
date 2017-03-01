package com.xunfenqi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.xunfenqi.R;


public class WaitLoadingDialog extends Dialog
{
	private LayoutInflater inflater;
	private View contentView;

	public WaitLoadingDialog(Context context, int theme)
	{
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
	}

	protected WaitLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
	}

	public WaitLoadingDialog(Context context)
	{
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		inflater = LayoutInflater.from(context);
		contentView = inflater.inflate(R.layout.wait_dialog_content_view, null);
		setContentView(contentView);
		setCanceledOnTouchOutside(false);
	}

}
