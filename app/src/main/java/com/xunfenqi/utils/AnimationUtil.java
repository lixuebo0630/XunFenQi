package com.xunfenqi.utils;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;


/**
 * 
* @ClassName: AnimationUtil 
* @Description: 
* @author Xuebo Li
* @date 2015-8-13 下午8:59:39 
*
 */
public class AnimationUtil {

	/* 特效源码---------listview加载的效果 */
	public static LayoutAnimationController getListAnimTranslate() {
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(500);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(800);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);

		controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
		return controller;
		/*-----------------------------------------*/
	}


}
