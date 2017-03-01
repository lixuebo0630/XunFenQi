package com.xunfenqi.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @ClassName: AbAlertDialogFragment
 * @Description: 弹出框
 * @author Xuebo Li
 * @date 2015-8-14 下午2:51:15
 * 
 */
public class AbAlertDialogFragment2 extends DialogFragment {

	int mIcon;
	String mTitle;
	String mMessage;
	static View mContentView;
	static AbDialogOnClickListener2 mOnClickListener;

	/**
	 * Create a new instance of AbDialogFragment.
	 */
	public static AbAlertDialogFragment2 newInstance(int icon, String title,
			String message, View view, AbDialogOnClickListener2 onClickListener) {
		AbAlertDialogFragment2 f = new AbAlertDialogFragment2();
		mOnClickListener = onClickListener;
		mContentView = view;

		Bundle args = new Bundle();
		args.putInt("icon", icon);
		args.putString("title", title);
		args.putString("message", message);
		f.setArguments(args);

		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIcon = getArguments().getInt("icon");
		mTitle = getArguments().getString("title");
		mMessage = getArguments().getString("message");

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
				AlertDialog.THEME_HOLO_LIGHT);
		if (mIcon > 0) {
			builder.setIcon(mIcon);
		}

		if (mTitle != null) {
			builder.setTitle(mTitle);
		}

		if (mMessage != null) {
			builder.setMessage(mMessage);

		}
		if (mContentView != null) {
			builder.setView(mContentView);
		}

		if (mOnClickListener != null) {

			builder.setNegativeButton("知道了",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							if (mOnClickListener != null) {
								mOnClickListener.onNegativeClick();
							}
						}
					});
		}

		return builder.create();
	}

	/**
	 * Dialog事件的接口.
	 */
	public interface AbDialogOnClickListener2 {

		public void onNegativeClick();
	}

}
