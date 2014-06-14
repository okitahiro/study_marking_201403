package jp.ktsystem.kadai201403.h_okita;

/**
 * 業務時間のフィル書き込み用情報を収納するモデルクラス
 * @author okita
 * @since 2014/04/03
 */
public class OutPutDataModel {
		// 日付
		private String date;

		// 出社時間
		private int workTime;

		// 退社時間
		private int total;

		/**
		 * コンストラクタ
		 *
		 * @param date
		 *            日付
		 * @param workTime
		 *            業務時間
		 * @param total
		 *            合計時間
		 * @since 2014/04/07
		 */
		public OutPutDataModel(String date, int workTime, int total) {
			this.date = date;
			this.workTime = workTime;
			this.total = total;
		}

		/**
		 * 日付を取得する
		 *
		 * @return 日付
		 * @since 2014/04/07
		 */
		public String getDate() {
			return this.date;
		}

		/**
		 * 業務時間を文字列で取得する
		 *
		 * @return 業務時間
		 * @since 2014/04/07
		 */
		public String getWorkingTime() {
			return Integer.toString(this.workTime);
		}

		/**
		 * 合計時間を文字列取得する
		 *
		 * @return 合計時間
		 * @since 2014/04/07
		 */
		public String getTotal() {
			return Integer.toString(this.total);
		}

}
