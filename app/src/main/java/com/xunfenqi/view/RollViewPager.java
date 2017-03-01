package com.xunfenqi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xunfenqi.R;
import com.xunfenqi.model.domain.UserIntoIndex;

import java.util.List;

public class RollViewPager extends ViewPager {
	protected static final String tag = null;
	private Context context;
	private List<View> viewList;
	private TextView top_news_title;
	private List<UserIntoIndex.ImageUrl> urlImgList;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private MyAdapter myAdapter;
	private RunnableTask runnableTask;
	private int currentPosition = 3333;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			RollViewPager.this.setCurrentItem(currentPosition);// 处理了滑动
			startRoll();
		};
	};

	class RunnableTask implements Runnable {
		@Override
		public void run() {
			// 滚动viewpager
			currentPosition = (currentPosition + 1) % viewList.size();
			handler.obtainMessage().sendToTarget();
		}
	}

	private int downX;
	private int downY;
	private onPageClick pageClick;

	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 让当前viewpager 对应的父控件不要去拦截事件
			getParent().requestDisallowInterceptTouchEvent(true);
			downX = (int) ev.getX();
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();
			// 刷新
			if (Math.abs(moveY - downY) > Math.abs(moveX - downX)) {
				getParent().requestDisallowInterceptTouchEvent(false);
			} else {
				// 滚动轮播图片
				getParent().requestDisallowInterceptTouchEvent(true);
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	};

	// 从界面移出的时候会调用方法
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		// 移除所有的任务
		handler.removeCallbacksAndMessages(null);
	}

	public RollViewPager(Context context, final List<View> viewList,
			onPageClick pageClick) {// new RollViewPager.onPageClick()
		super(context);
		this.context = context;
		this.viewList = viewList;
		this.pageClick = pageClick;
		initImageLoader(context);
		// bitmapUtils = new BitmapUtils(context);
		runnableTask = new RunnableTask();
		this.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				arg0 %= urlImgList.size();
				if (arg0 < 0) {
					arg0 = urlImgList.size() + arg0;
				}
				for (int i = 0; i < urlImgList.size(); i++) {
					if (i == arg0) {
						viewList.get(arg0).setBackgroundResource(
								R.drawable.dot_focus);
					} else {
						viewList.get(i).setBackgroundResource(
								R.drawable.dot_blur);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	// // 将图片关联说明的文字集合,需要显示的控件传递进来
	// public void initTitleList(TextView top_news_title, List<String>
	// titleList) {
	// if (null != top_news_title && null != titleList && titleList.size() > 0)
	// {
	// top_news_title.setText(titleList.get(0));
	// }
	// this.top_news_title = top_news_title;
	// this.titleList = titleList;
	// }
	// 显示图片的url地址的集合
	public void initImgUrlList(List<UserIntoIndex.ImageUrl> urlImgList) {
		this.urlImgList = urlImgList;
	}

	public interface onPageClick {
		public abstract void onclick(UserIntoIndex.ImageUrl imageUrl);
	}

	public void startRoll() {
		// 滚动viewpager
		if (myAdapter == null) {
			myAdapter = new MyAdapter();
			this.setAdapter(myAdapter);
		} else {
			myAdapter.notifyDataSetChanged();
		}

		handler.postDelayed(runnableTask, 3000);
	}

	class MyAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return 10000;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(context, R.layout.viewpager_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			// 对ViewPager页号求模取出View列表中要显示的项
			if (urlImgList.size() > 0) {
				position %= urlImgList.size();
				if (position < 0) {
					position = urlImgList.size() + position;
				}

				ViewParent vp = view.getParent();
				if (vp != null) {
					// 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
					ViewGroup parent = (ViewGroup) vp;
					parent.removeView(view);
				}
				// bitmapUtils.display(imageView,urlImgList.get(position));
				// 显示图片的配置
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						// .showImageOnLoading(R.drawable.ic_error_page)
						// .showImageOnFail(R.drawable.ic_error_page)
						.cacheInMemory(true).cacheOnDisk(true)
						.bitmapConfig(Bitmap.Config.RGB_565).build();

				// imageLoader.displayImage(imageUrl, mImageView, options);
				imageLoader.displayImage(urlImgList.get(position).getUrl(),
						imageView, options);

				view.setOnTouchListener(new CusTouchListener(urlImgList
						.get(position)));

				((RollViewPager) container).addView(view);

			}

			return view;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((RollViewPager) container).removeView((View) object);
		}
	}

	/**
	 * ImageLoader 图片组件初始化
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove
									// for
									// release
									// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	public class CusTouchListener implements OnTouchListener {
		private int downX;
		private long downTime;

		private UserIntoIndex.ImageUrl imageUrl;

		public CusTouchListener(UserIntoIndex.ImageUrl imageUrl) {
			this.imageUrl = imageUrl;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				handler.removeCallbacksAndMessages(null);// 按住图片的时候移出图片的
				downX = (int) event.getX();
				downTime = System.currentTimeMillis();
				break;

			case MotionEvent.ACTION_UP:
				if (System.currentTimeMillis() - downTime < 500
						&& downX == (int) event.getX()) {
					// 点击事件被触发
					if (pageClick != null) {
						pageClick.onclick(imageUrl);
					}
				}
				startRoll();
				break;
			case MotionEvent.ACTION_CANCEL:
				startRoll();
				break;
			}
			return true;

		}

	}
}
