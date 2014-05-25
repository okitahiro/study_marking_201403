package jp.ktsystem.kadai201403.h_okita;

import java.util.List;

/**
 * 月ごとの勤怠データのモデル
 * @author okita
 * @since 2014/04/01
 */
public class WorkTimeOfMonthModel{
	
	// 年月
	private String month;
	// 勤怠データモデルリスト
	private List<WorkTimeModel> modelList;
	
	/**
	 * コンストラクタ
	 * @param month 年月
	 * @param modelList 勤怠データモデルリスト
	 * @since 2014/04/01
	 */
	public WorkTimeOfMonthModel(String month, List<WorkTimeModel> modelList){
		this.month = month;
		this.modelList = modelList;
	}
	
	/**
	 * 年月を取得する
	 * 
	 * @return 年月
	 * @since 2014/04/04
	 */
	public String getMonth() {
		return this.month;
	}

	/**
	 * 勤怠データモデルリストを取得する
	 * 
	 * @return 勤怠データモデルリスト
	 * @since 2014/04/04
	 */
	public List<WorkTimeModel> getModelList() {
		return this.modelList;
	}
}
