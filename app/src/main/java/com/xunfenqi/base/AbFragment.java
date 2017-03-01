package com.xunfenqi.base;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.utils.AbAnimationUtil;
import com.xunfenqi.utils.AbViewUtil;


/**
 * 
 * @ClassName: AbFragment
 * @Description: Fragment基类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:12:33
 * 
 */
public abstract class AbFragment extends Fragment {

	private int mLoadDrawable = R.drawable.ic_loading;
	private int mRefreshDrawable;
	private int mEmptyDrawable;
	public String mEmptyMessage = "没有更多数据";
	public String mLoadMessage = "";
	public String mRefreshMessage = "请求出错，请重试";
	private int mTextSize = 15;
	private int mTextColor = Color.parseColor("#ABABAB");
	private RelativeLayout rootView = null;
	private View mContentView;
	private LinearLayout mLoadView = null;
	private LinearLayout mRefreshView = null;
	private View mEmptyView = null;

	private TextView mLoadTextView = null;
	private ImageView mLoadImageView = null;
	private TextView mRefreshTextView = null;
	private ImageView mRefreshImageView = null;

	private LayoutInflater inflater;
	private TextView mEmptyTextView = null;
	private ImageView mEmptyImageView = null;
	private View mIndeterminateView = null;
	private int mBackgroundColor = Color.parseColor("#88838B8B");
	private AbFragmentOnLoadListener mAbFragmentOnLoadListener = null;




	/** Fragment当前状态是否可见 */
	protected boolean isVisible;


	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if(getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}


	/**
	 * 可见
	 */
	protected void onVisible() {
		lazyLoad();
	}


	/**
	 * 不可见
	 */
	protected void onInvisible() {


	}


	/**
	 * 延迟加载
	 * 子类必须重写此方法
	 */
	protected abstract void lazyLoad();

	/**
	 * 创建
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;

		rootView = new RelativeLayout(this.getActivity());
		rootView.setBackgroundColor(mBackgroundColor);
		mContentView = onCreateContentView(inflater, container,
				savedInstanceState);
		// 设置默认资源
		setResource();
		// 先显示load
		showLoadView();
		return rootView;
	}

	/**
	 * 显示View的方法（需要实现）
	 * 
	 * @return
	 */
	public View onCreateContentView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return null;
	}

	/**
	 * 设置用到的资源（需要实现）
	 */
	public void setResource() {

	}

	/**
	 * 初始化加载View
	 */
	public void initLoadView() {

		mLoadView = new LinearLayout(this.getActivity());
		mLoadView.setGravity(Gravity.CENTER);
		mLoadView.setBackgroundColor(Color.WHITE);
		mLoadView.setOrientation(LinearLayout.VERTICAL);
		mLoadView.setPadding(20, 20, 20, 20);
		mLoadView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		// mLoadView = (LinearLayout) View.inflate(getActivity(),
		// R.layout.layout_progress, null);
		// mLoadView.setGravity(Gravity.CENTER);
		// mLoadTextView = (TextView) mLoadView
		// .findViewById(R.id.ll_progress_title_text);
		// mLoadView.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// mLoadImageView = (ProgressWheel) mLoadView
		// .findViewById(R.id.ll_progress_progressWheel);
		// mLoadImageView.setBarColor(Color.parseColor("#3da4fe"));
		// mLoadImageView.spin();

		mLoadImageView = new ImageView(this.getActivity());
		mLoadImageView.setImageResource(mLoadDrawable);
		mLoadImageView.setScaleType(ScaleType.MATRIX);

		mLoadTextView = new TextView(this.getActivity());
		mLoadTextView.setText(mLoadMessage);
		mLoadTextView.setTextColor(mTextColor);
		mLoadTextView.setTextSize(mTextSize);
		mLoadTextView.setPadding(5, 5, 5, 5);

		mLoadView.addView(mLoadImageView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mLoadView.addView(mLoadTextView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mLoadImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 执行刷新
				load(v);
			}

		});

	}

	/**
	 * 
	 * @Title: initEmptyView
	 * @Description: 初始化空布局
	 * @param:
	 * @return void
	 * @throws
	 */
	public void initEmptyView() {
		if (inflater != null) {

			mEmptyView = inflater.inflate(R.layout.layout_empty_view, null);
			mEmptyView.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			mEmptyTextView = (TextView) mEmptyView.findViewById(R.id.tv_empty_desc);
			mEmptyTextView.setText(mEmptyMessage);
		}

		// if (this != null) {
		// mEmptyView = new LinearLayout(this.getActivity());
		// }
		// mEmptyView.setGravity(Gravity.CENTER);
		// mEmptyView.setOrientation(LinearLayout.VERTICAL);
		// mEmptyView.setPadding(20, 20, 20, 20);
		// mEmptyView.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// mEmptyView.setBackgroundColor(Color.WHITE);
		// mEmptyImageView = new ImageView(this.getActivity());
		// /** **/
		// mEmptyImageView
		// .setImageResource(R.drawable.product_detail_act_empty_page);
		// mEmptyImageView.setScaleType(ScaleType.MATRIX);
		//
		// mEmptyTextView = new TextView(this.getActivity());
		// mEmptyTextView.setText(mEmptyMessage);
		// mEmptyTextView.setTextColor(mTextColor);
		// mEmptyTextView.setTextSize(mTextSize);
		// mEmptyTextView.setPadding(5, 5, 5, 5);
		//
		// mEmptyView.addView(mEmptyImageView, new LinearLayout.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		// mEmptyView.addView(mEmptyTextView, new LinearLayout.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}

	/**
	 * 初始化刷新View
	 */
	public void initRefreshView() {
		if (this != null) {
			mRefreshView = new LinearLayout(getActivity());
			mRefreshView.setGravity(Gravity.CENTER);
			mRefreshView.setOrientation(LinearLayout.VERTICAL);
			mRefreshView.setPadding(20, 20, 20, 20);
			mRefreshView.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			mRefreshView.setBackgroundColor(Color.WHITE);
			mRefreshImageView = new ImageView(this.getActivity());
			mRefreshImageView.setImageResource(R.drawable.ic_error_page);
			mRefreshImageView.setScaleType(ScaleType.MATRIX);

			mRefreshTextView = new TextView(this.getActivity());
			mRefreshTextView.setText(mRefreshMessage);
			mRefreshTextView.setTextColor(mTextColor);
			mRefreshTextView.setTextSize(mTextSize);
			mRefreshTextView.setPadding(5, 5, 5, 5);

			mRefreshView.addView(mRefreshImageView,
					new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			mRefreshView.addView(mRefreshTextView,
					new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			mRefreshTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 执行刷新
					showLoadView();
				}

			});
		}
	}

	/**
	 * 显示加载View
	 */
	public void showLoadView() {
		if (rootView.getChildCount() > 0) {
			if (mLoadView == rootView.getChildAt(0)) {
				return;
			}
		}
		rootView.removeAllViews();
		if (mLoadView == null) {
			initLoadView();
		}
		AbViewUtil.removeSelfFromParent(mLoadView);
		rootView.addView(mLoadView);
		// 执行加载
		load(mLoadImageView);
	}

	/**
	 * 显示刷新View
	 */
	public void showRefreshView() {
		if (rootView.getChildCount() > 0) {
			if (mRefreshView == rootView.getChildAt(0)) {
				loadStop(mRefreshImageView);
				return;
			}
		}

		rootView.removeAllViews();
		if (mRefreshView == null) {
			initRefreshView();
		}
		AbViewUtil.removeSelfFromParent(mRefreshView);
		rootView.addView(mRefreshView);
	}

	public void showEmptyView() {
		if (rootView.getChildCount() > 0) {
			if (mEmptyView == rootView.getChildAt(0)) {
				return;
			}

			rootView.removeAllViews();
			if (mEmptyView == null) {
				initEmptyView();
			}
			AbViewUtil.removeSelfFromParent(mEmptyView);
			rootView.addView(mEmptyView);
		}
	}

	/**
	 * 显示内容View
	 */
	public void showContentView() {
		if (rootView.getChildCount() > 0) {
			if (mContentView == rootView.getChildAt(0)) {
				return;
			}
		}

		rootView.removeAllViews();
		AbViewUtil.removeSelfFromParent(mContentView);
		rootView.addView(mContentView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 显示内容View
	 */
	public void showContentView(View view) {
		rootView.removeAllViews();
		AbViewUtil.removeSelfFromParent(mContentView);
		mContentView = view;
		rootView.addView(mContentView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 加载完成调用
	 */
	public void loadFinish() {
		// 停止动画
		loadStop(mIndeterminateView);
	}

	/**
	 * 加载结束
	 */
	public void loadStop(final View view) {
		if (view == null) {
			return;
		}
		// 停止动画
		view.postDelayed(new Runnable() {

			@Override
			public void run() {
				view.clearAnimation();
			}

		}, 200);
	}

	/**
	 * 加载调用
	 */
	public void load(View v) {
		if (mAbFragmentOnLoadListener != null) {
			mAbFragmentOnLoadListener.onLoad();
		}
		mIndeterminateView = v;
		AbAnimationUtil.playRotateAnimation(mIndeterminateView, 1000,
				Animation.INFINITE, Animation.RESTART);
	}

	/**
	 * 获取内容View
	 * 
	 * @return
	 */
	public View getContentView() {
		return mContentView;
	}

	/**
	 * 获取加载View文字的尺寸
	 * 
	 * @return
	 */
	public int getTextSize() {
		return mTextSize;
	}

	/**
	 * 设置加载View文字的尺寸
	 * 
	 * @return
	 */
	public void setTextSize(int textSize) {
		this.mTextSize = textSize;
	}

	public int getTextColor() {
		return mTextColor;
	}

	public void setTextColor(int textColor) {
		this.mTextColor = textColor;
	}

	public void setLoadMessage(String message) {
		this.mLoadMessage = message;
		if (mLoadTextView != null) {
			mLoadTextView.setText(mLoadMessage);
		}
	}

	public void setEmptyMessage(String message) {
		this.mEmptyMessage = message;
		if (mEmptyTextView != null) {
			mEmptyTextView.setText(mEmptyMessage);
		}
	}

	public void setRefreshMessage(String message) {
		this.mRefreshMessage = message;
		if (mRefreshTextView != null) {
			mRefreshTextView.setText(mRefreshMessage);
		}
	}

	public int getLoadDrawable() {
		return mLoadDrawable;
	}

	public void setLoadDrawable(int resid) {
		this.mLoadDrawable = resid;
		if (mLoadImageView != null) {
			mLoadImageView.setBackgroundResource(resid);
		}
	}

	public int getEmptyDrawable() {
		return mEmptyDrawable;
	}

	public void setEmptyDrawable(int resid) {
		this.mEmptyDrawable = resid;
		if (mEmptyImageView != null) {
			// mEmptyImageView.setBackgroundResource(resid);
		}
	}

	public int getRefreshDrawable() {
		return mRefreshDrawable;
	}

	public void setRefreshDrawable(int resid) {
		this.mRefreshDrawable = resid;
		if (mRefreshImageView != null) {
			mRefreshImageView.setBackgroundResource(resid);
		}
	}

	public int getBackgroundColor() {
		return mBackgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.mBackgroundColor = backgroundColor;
	}

	public AbFragmentOnLoadListener getAbFragmentOnLoadListener() {
		return mAbFragmentOnLoadListener;
	}

	public void setAbFragmentOnLoadListener(
			AbFragmentOnLoadListener abFragmentOnLoadListener) {
		this.mAbFragmentOnLoadListener = abFragmentOnLoadListener;
	}

	/**
	 * 加载事件的接口.
	 */
	public interface AbFragmentOnLoadListener {

		/**
		 * 加载
		 */
		public void onLoad();

	}






}
