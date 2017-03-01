package com.xunfenqi.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @ClassName: AbProgressDialogFragment
 * @Description: 弹出的进度框
 * @author Xuebo Li
 * @date 2015-8-14 下午3:04:12
 * 
 */
public class AbProgressDialogFragment extends DialogFragment {

	int mIndeterminateDrawable;
	String mMessage;
	static View mContentView;

	/**
	 * Create a new instance of AbProgressDialogFragment.
	 */
	public static AbProgressDialogFragment newInstance(
			int indeterminateDrawable, String message) {
		AbProgressDialogFragment f = new AbProgressDialogFragment();
		Bundle args = new Bundle();
		args.putInt("indeterminateDrawable", indeterminateDrawable);
		args.putString("message", message);
		f.setArguments(args);

		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIndeterminateDrawable = getArguments().getInt("indeterminateDrawable");
		mMessage = getArguments().getString("message");

		ProgressDialog mProgressDialog = new ProgressDialog(getActivity(),
				AlertDialog.THEME_HOLO_LIGHT);
		if (mIndeterminateDrawable > 0) {
			mProgressDialog.setIndeterminateDrawable(getActivity()
					.getResources().getDrawable(mIndeterminateDrawable));
		}

		if (mMessage != null) {
			mProgressDialog.setMessage(mMessage);
		}

		return mProgressDialog;
	}

}
