package com.xunfenqi.global;

/**
 * 
 * @ClassName: CallBackManager
 * @Description: 回调管理器
 * @author Xuebo Li
 * @date 2015-9-14 下午3:41:03
 * 
 */

public class CallBackManager {

	private static CallBackManager instance;
	// 主页选项卡回调
	private SwitchMainRadioCallback switchMainRadioCallback;
	// 项目详情上拉回调
	private ScollViewPullUpCallback scollViewPullUpCallback;
	// 充值记录pop回调
	private RechargeRecordClickCallback rechargeRecordClickCallback;
	// 资金明细pop回调
	private MoneyDetailClickCallback moneyDetailClickCallback;
	// 我的佣金pop回调
	private MyFriendClickCallback myFriendClickCallback;
	// 提现记录pop回调
	private EnchashmentRecordClickCallback enchashmentRecordClickCallback;
	// 标的列表tab回调
	private SwitchProductListTabCallback switchProductListTabCallback;
	// 消息 查看全部消息回调
	private CheckAllMessageCallback checkAllMessageCallback;
	//礼券
	private CouponClickCallback couponClickCallback;
	//转让成功回调
	
	private ZhuanRangSuccessCallback zhuanRangSuccessCallback;

	public ZhuanRangSuccessCallback getZhuanRangSuccessCallback() {
		return zhuanRangSuccessCallback;
	}

	public void setZhuanRangSuccessCallback(
			ZhuanRangSuccessCallback zhuanRangSuccessCallback) {
		this.zhuanRangSuccessCallback = zhuanRangSuccessCallback;
	}

	private CallBackManager() {
	}

	// 单例
	public static CallBackManager getInstance() {
		if (instance == null) {
			instance = new CallBackManager();
		}
		return instance;
	}

	public CheckAllMessageCallback getCheckAllMessageCallback() {
		return checkAllMessageCallback;
	}

	public void setCheckAllMessageCallback(
			CheckAllMessageCallback checkAllMessageCallback) {
		this.checkAllMessageCallback = checkAllMessageCallback;
	}

	public EnchashmentRecordClickCallback getEnchashmentRecordClickCallback() {
		return enchashmentRecordClickCallback;
	}

	public void setEnchashmentRecordClickCallback(
			EnchashmentRecordClickCallback enchashmentRecordClickCallback) {
		this.enchashmentRecordClickCallback = enchashmentRecordClickCallback;
	}

	public RechargeRecordClickCallback getRechargeRecordClickCallback() {
		return rechargeRecordClickCallback;
	}

	public void setRechargeRecordClickCallback(
			RechargeRecordClickCallback rechargeRecordClickCallback) {
		this.rechargeRecordClickCallback = rechargeRecordClickCallback;
	}

	public MoneyDetailClickCallback getMoneyDetailClickCallback() {
		return moneyDetailClickCallback;
	}
	
	public void setMoneyDetailClickCallback(
			MoneyDetailClickCallback moneyDetailClickCallback) {
		this.moneyDetailClickCallback = moneyDetailClickCallback;
	}

	public MyFriendClickCallback getMyFriendClickCallback() {
		return myFriendClickCallback;
	}

	public void setMyFriendClickCallback(MyFriendClickCallback myFriendClickCallback) {
		this.myFriendClickCallback = myFriendClickCallback;
	}

	
	public ScollViewPullUpCallback getScollViewPullUpCallback() {
		return scollViewPullUpCallback;
	}

	public void setOnSwitchMainRadioCallback(
			SwitchMainRadioCallback switchMainRadioCallback) {
		this.switchMainRadioCallback = switchMainRadioCallback;
	}

	public SwitchProductListTabCallback getSwitchProductListTabCallback() {
		return switchProductListTabCallback;
	}

	public void setSwitchProductListTabCallback(
			SwitchProductListTabCallback switchProductListTabCallback) {
		this.switchProductListTabCallback = switchProductListTabCallback;
	}
	
	public CouponClickCallback getCouponClickCallback() {
		return couponClickCallback;
	}

	public void setCouponClickCallback(CouponClickCallback couponClickCallback) {
		this.couponClickCallback = couponClickCallback;
	}

	// 主页选项卡回调方法
	public void sendSwitchRadio(int position) {
		if (switchMainRadioCallback != null) {

			switchMainRadioCallback.switchRadio(position);
		}
	}

	// 主页选项卡回调方法
	public void checkAllMsg() {
		if (checkAllMessageCallback != null) {
			checkAllMessageCallback.checkAll();
		}
	}

	// 标的列表选项卡回调方法
	public void sendSwitchTab(int position) {
		if (switchProductListTabCallback != null) {

			switchProductListTabCallback.switchTab(position);
		}
	}

	public void setOnScollViewPullUpCallback(
			ScollViewPullUpCallback scollViewPullUpCallback) {
		this.scollViewPullUpCallback = scollViewPullUpCallback;
	}

	public void interceptEvent(boolean isintercept) {
		if (scollViewPullUpCallback != null) {

			scollViewPullUpCallback.isIntercept();
		}
	}

}