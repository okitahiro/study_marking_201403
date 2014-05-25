package jp.ktsystem.kadai201403.h_okita;

import java.util.Comparator;

/**
 * WorkTimeModelの日付による比較
 * @author h_okita
 * @since 2014/04/01
 *
 */
public class WorkTimeModelComparator implements Comparator<WorkTimeModel> {

	/**
	 * 比較メソッド
	 */
	public int compare(WorkTimeModel a, WorkTimeModel b) {
		String date1 = a.getDate();
		String date2 = b.getDate();

		return date1.compareTo(date2);
	}
}
