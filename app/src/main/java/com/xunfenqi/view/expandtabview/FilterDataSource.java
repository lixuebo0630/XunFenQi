package com.xunfenqi.view.expandtabview;

/**
 * 
 * @ClassName: FilterDataSource
 * @Description:
 * @author Xuebo Li
 * @date 2015-9-7 下午2:37:10
 * 
 */
public class FilterDataSource {

	public static final int FILTER_DATA_TYPE_TIME_LIMIT = 1;// 期限
	public static final int FILTER_DATA_TYPE_EARNINGS = 2;// 收益
	public static final int FILTER_DATA_TYPE_STATE = 3;// 状态

	public static String[] createPriceFilterItems() {
		String[] names = { "不限", "50元以下", "50-100元", "100-150元", "150-200元",
				"200-250元", "250-300元", "300元以上" };
		return names;
	}

	public static String[] createSortFilterItems() {
		String names[] = new String[] { "默认排序", "价格降序", "价格升序", "预订数降序",
				"评论数降序" };
		return names;
	}

	public static String getDataTypeName(int dataType) {
		if (FILTER_DATA_TYPE_TIME_LIMIT == dataType) {
			return "期限";
		} else if (FILTER_DATA_TYPE_EARNINGS == dataType) {
			return "收益";
		} else if (FILTER_DATA_TYPE_STATE == dataType) {
			return "状态";
		}
		return "";
	}
}
