package jp.ktsystem.kadai201403.h_okita;

/**
 * 勤怠データモデル
 * 
 * @author okita
 * @since 2014/04/01
 */
public class WorkTimeModel {

	// 日付
	private String dateStr;

	// 出社時間
	private String start;

	// 退社時間
	private String end;

	/**
	 * コンストラクタ
	 * 
	 * @param date
	 *            日付
	 * @param start
	 *            出社時間
	 * @param end
	 *            退社時間
	 * @since 2014/04/01
	 */
	public WorkTimeModel(String date, String start, String end) {
		this.dateStr = date;
		this.start = start;
		this.end = end;
	}

	/**
	 * 日付を取得する
	 * 
	 * @return 日付
	 * @since 2014/04/01
	 */
	public String getDateStr() {
		return this.dateStr;
	}

	/**
	 * 出社時間を取得する
	 * 
	 * @return 出社時間
	 * @since 2014/04/01
	 */
	public String getStart() {
		return this.start;
	}

	/**
	 * 退社時間を取得する
	 * 
	 * @return 退社時間
	 * @since 2014/04/01
	 */
	public String getEnd() {
		return this.end;
	}

}
