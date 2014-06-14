package jp.ktsystem.kadai201403.h_okita;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2014/03の勉強会課題
 * @author okita
 * @since 2014/04/01
 */
public class Kadai {

	// 文字コード
	private static final String CHAR_SET = "UTF-8";
	// Lv1カラムパターン
	private static final Pattern DATA_PAT = Pattern.compile("^[\\s　]*\"(.*?)\"[\\s　]*:[\\s　]*((\"(.*?)\")|(null))[\\s　]*$", Pattern.DOTALL);
	//Lv2年月取得用フォーマット
	private static final Pattern MONTH_PAT_LV2 = Pattern.compile("^[\\s　]*((\"(.*?)\")|(null))[\\s　]*$", Pattern.DOTALL);
	// Lv2日付取得用フォーマット
	private static final Pattern FORMAT_PAT_LV2_OF_DATE = Pattern.compile("^[\\s　]*((\"(.*?)\")|(null))[\\s　]*:[\\s　]*\\{(.*)\\}[\\s　]*$",
			Pattern.DOTALL);
	// dateキー
	private static final String DATE_KEY = "date";
	// startキー
	private static final String START_KEY = "start";
	// endキー
	private static final String END_KEY = "end";

	/**
	 * 課題Lv1
	 * ファイルから勤怠データを読み込み、 勤怠時間を求め、ファイルに書き出す
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力ファイルパス
	 * @throws KadaiException
	 */
	public static void parseWorkTimeData(String anInputPath, String anOutputPath) throws KadaiException {
		// 入力ファイルパスチェック
		if (CommonUtil.strIsEmpty(anInputPath)) {
			throw new KadaiException(ErrorCode.INPUT_FILE_PATH_ENPTY);
		}

		// 出力ファイルパスチェック
		if (CommonUtil.strIsEmpty(anOutputPath)) {
			throw new KadaiException(ErrorCode.OUTPUT_FILE_PATH_ENPTY);
		}

		File inputFile = new File(anInputPath);

		// 入力ファイルの存在チェック
		if (!CommonUtil.checkReadFile(inputFile)) {
			throw new KadaiException(ErrorCode.INPUT_FILE_NOT_EXIST);
		}

		BufferedReader br = null;
		// データを保持するモデルリスト
		List<WorkTimeModel> modelList = new ArrayList<WorkTimeModel>();

		// 入力ファイルの読み込み
		try {
			// BOMスキップ後のBufferedReader
			br = new BufferedReader(new InputStreamReader(CommonUtil.skipUTF8BOM(new FileInputStream(inputFile)), CHAR_SET));

			// チェック文字
			int checkC = '[';

			int c = getNextCheckChar(br);
			while (-1 != c) {
				//チェック文字ではなかった場合
				if (checkC != c) {
					// 読み込み文字のエラー
					throw new KadaiException(ErrorCode.INVALID_STRING);
				}

				// チェック文字列変更
				switch (checkC) {
				case '[':
					checkC = '{';
					break;
				case '{':
					//データ部（｛～｝）の文字列取得
					String dataStr = getStringData(br, '}');

					//データ取得処理
					modelList.add(getDataOfLv1(dataStr));

					c = getNextCheckChar(br);
					if (',' == c) {
						// 次データがある場合
						checkC = '{';
					} else if (']' == c) {
						// データのまとまり終了の場合
						c = getNextCheckChar(br);
						if (-1 != c)
						{
							// 読み込み文字のエラー
							throw new KadaiException(ErrorCode.INVALID_STRING);
						}
						continue;
					} else {
						// 読み込み文字のエラー
						throw new KadaiException(ErrorCode.INVALID_STRING);
					}
					break;
				default:
					throw new KadaiException(ErrorCode.INVALID_STRING);
				}

				c = getNextCheckChar(br);
			}
		} catch (IOException e) {
			// 入力ファイルの読み込みエラー
			throw new KadaiException(ErrorCode.ERROR_LOADING_INPUT_FILE);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// ファイルへ書き出し
		try {
			fileOutPut(anOutputPath, modelList, CHAR_SET);
		} catch (KadaiException ke) {
			throw ke;
		} catch (Exception e) {
			// ファイルの出力エラー
			throw new KadaiException(ErrorCode.ERROE_OUTPUT_FILE);
		}
	}

	/**
	 * フォーマット形式から必要なデータを抽出する。
	 *
	 * @param str
	 *            フォーマット形式の文字列
	 */
	private static WorkTimeModel getDataOfLv1(String str) throws KadaiException {

		List<String> strList = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		// 原文読み込みフラグ
		boolean originalFlag = false;

		// ","区切りでSplit
		for (int i = 0; i < str.length(); i++) {
			if ('"' == str.charAt(i)) {
				originalFlag = !originalFlag;
			}

			if (!originalFlag && ',' == str.charAt(i)) {
				strList.add(sb.toString());
				sb = new StringBuilder();
				continue;
			}

			sb.append(str.charAt(i));
		}
		strList.add(sb.toString());

		// 日付
		String date = null;
		// 出社時間
		String start = null;
		// 退社時間
		String end = null;
		int i = 0;
		for (; i < strList.size(); i++) {
			Matcher m = DATA_PAT.matcher(strList.get(i));

			if (!m.find())
			{
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}

			String key = m.group(1);

			if (key.equals(DATE_KEY)) {
				// 日付取得
				date = m.group(4) == null ? null : m.group(4);
			} else if (key.equals(START_KEY)) {
				// 出社時間取得
				start = m.group(4) == null ? null : m.group(4);
			} else if (key.equals(END_KEY)) {
				// 退社時間取得
				end = m.group(4) == null ? null : m.group(4);
			} else {
				// keyの不正
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}
		}

		// データの過不足チェック
		if (3 != i) {
			throw new KadaiException(ErrorCode.INVALID_COLUMNS_NUM);
		}

		WorkTimeModel model = new WorkTimeModel(date, start, end);
		return model;
	}

	/**
	 * 課題Lv2
	 * ファイルから勤怠データを読み込み、 勤怠時間を求め、ファイルに書き出す
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力フォルダパス
	 * @throws KadaiException
	 */
	public static void parseWorkTimeDataLv2(String anInputPath, String anOutputPath) throws KadaiException {
		// 入力ファイルパスチェック
		if (CommonUtil.strIsEmpty(anInputPath)) {
			throw new KadaiException(ErrorCode.INPUT_FILE_PATH_ENPTY);
		}

		// 出力ファイルパスチェック
		if (CommonUtil.strIsEmpty(anOutputPath)) {
			throw new KadaiException(ErrorCode.OUTPUT_FILE_PATH_ENPTY);
		}

		File inputFile = new File(anInputPath);

		// 入力ファイルの存在チェック
		if (!CommonUtil.checkReadFile(inputFile)) {
			throw new KadaiException(ErrorCode.INPUT_FILE_NOT_EXIST);
		}

		BufferedReader br = null;
		// データを保持するモデルリスト
		List<WorkTimeOfMonthModel> monthModelList = new ArrayList<WorkTimeOfMonthModel>();

		// 入力ファイルの読み込み
		try {
			// BOMスキップ後のBufferedReader
			br = new BufferedReader(new InputStreamReader(CommonUtil.skipUTF8BOM(new FileInputStream(inputFile)), CHAR_SET));

			// 年月
			String month = null;

			// チェック文字
			int checkC = '{';

			int c = getNextCheckChar(br);
			while (-1 != c) {
				//チェック文字ではなかった場合
				if (checkC != c) {
					// 読み込み文字のエラー
					throw new KadaiException(ErrorCode.INVALID_STRING);
				}

				switch (checkC) {
				case '{':
					// 月データ取得処理
					month = getMonthOfLv2(getStringData(br, ':'));
					checkC = '[';
					break;
				case '[':
					//データ部（[～]）の文字列取得
					String dataStr = getStringData(br, ']');
					// [～]のデータ処理
					WorkTimeOfMonthModel model = new WorkTimeOfMonthModel(month, getDataOfMonthOfLv2(dataStr));
					monthModelList.add(model);

					c = getNextCheckChar(br);
					if (',' == c) {
						// 次データがある場合
						// 月データ取得処理
						month = getMonthOfLv2(getStringData(br, ':'));
						checkC = '[';
					} else if ('}' == c) {
						// データのまとまり終了の場合
						if (-1 != getNextCheckChar(br))
						{
							// 読み込み文字のエラー
							throw new KadaiException(ErrorCode.INVALID_STRING);
						}
					} else {
						// 読み込み文字のエラー
						throw new KadaiException(ErrorCode.INVALID_STRING);
					}
					break;
				}

				c = getNextCheckChar(br);
			}
		} catch (IOException e) {
			// 入力ファイルの読み込みエラー
			throw new KadaiException(ErrorCode.ERROR_LOADING_INPUT_FILE);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// ファイルへ書き出し
		try {
			fileOutPtOfLv2(monthModelList, anOutputPath);
		} catch (KadaiException ke) {
			throw ke;
		} catch (Exception e) {
			// ファイルの出力エラー
			throw new KadaiException(ErrorCode.ERROE_OUTPUT_FILE);
		}
	}

	/**
	 * 年月データ取得
	 * @param str データ文字列
	 * @return 年月
	 * @throws KadaiException
	 */
	private static String getMonthOfLv2(String str) throws KadaiException {
		Matcher m = MONTH_PAT_LV2.matcher(str);
		if (m.find()) {
			String month = m.group(3) == null ? null : m.group(3);
			return month;
		} else {
			throw new KadaiException(ErrorCode.INVALID_STRING);
		}
	}

	/**
	 * 文字列より一カ月の勤怠データを取得する
	 * @param str
	 * @return
	 * @throws KadaiException
	 */
	private static List<WorkTimeModel> getDataOfMonthOfLv2(String str) throws KadaiException {

		if (CommonUtil.strIsEmpty(str)) {
			throw new KadaiException(ErrorCode.INVALID_COLUMNS_NUM);
		}

		StringBuilder allData = new StringBuilder(str);

		// はじめの「｛」を削除
		for (int i = 0; i < allData.length(); i++)
		{
			if (CommonUtil.checkBlankCode((int) allData.charAt(i))) {
				continue;
			}

			// {～}の形式になってない場合
			if ('{' != allData.charAt(i)) {
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}

			allData = allData.delete(0, i + 1);
			break;
		}

		// 終わりの「｝」を削除
		for (int i = allData.length() - 1; i >= 0; i--)
		{
			if (CommonUtil.checkBlankCode((int) allData.charAt(i))) {
				continue;
			}

			// {～}の形式になってない場合
			if ('}' != allData.charAt(i)) {
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}

			allData = allData.delete(i, allData.length());
			break;
		}

		StringBuilder sb = new StringBuilder();
		// 勤怠データモデルリスト
		List<WorkTimeModel> modelList = new ArrayList<WorkTimeModel>();

		// '}'があったかどうかのフラグ
		boolean flag = false;
		for (int i = 0; i < allData.length(); i++) {
			char c = allData.charAt(i);

			if ('}' == c) {
				flag = true;

				// 1日分データ取得処理
				sb.append((char) c);
				modelList.add(getDataOfDayOfLv2(sb.toString()));
				sb = new StringBuilder();

				if (allData.length() - 1 != i && ',' == allData.charAt(i + 1)) {
					i++;

					// フォーマット不正
					if (i == allData.length() - 1) {
						throw new KadaiException(ErrorCode.INVALID_STRING);
					}
				}
			} else {
				sb.append((char) c);
			}
		}

		// '}'があったかどうかチェック
		if (!flag) {
			throw new KadaiException(ErrorCode.INVALID_STRING);
		}
		// 最後の文字列が空白だけかチェック
		for (int i = 0; i < sb.length(); i++)
		{
			if (!CommonUtil.checkBlankCode(sb.charAt(i))) {
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}
		}

		// 日付によるソート
		Collections.sort(modelList, new WorkTimeModelComparator());
		return modelList;
	}

	/**
	 * 文字列より1日の勤怠データを取得する（Lv2）
	 * @param str
	 * @return
	 * @throws KadaiException
	 */
	private static WorkTimeModel getDataOfDayOfLv2(String dataStr) throws KadaiException {
		// フォーマットチェック
		Matcher m = FORMAT_PAT_LV2_OF_DATE.matcher(dataStr);

		// 勤怠情報モデル
		WorkTimeModel model = null;
		if (!m.find()) {
			throw new KadaiException(ErrorCode.INVALID_STRING);
		}

		// 日付
		String date = m.group(3) == null ? null : m.group(3);

		String str = m.group(5);

		List<String> strList = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		// 原文読み込みフラグ
		boolean originalFlag = false;

		// ","区切りでSplit
		for (int i = 0; i < str.length(); i++) {
			if ('"' == str.charAt(i)) {
				originalFlag = !originalFlag;
			}

			if (!originalFlag && ',' == str.charAt(i)) {
				strList.add(sb.toString());
				sb = new StringBuilder();
				continue;
			}

			sb.append(str.charAt(i));
		}
		strList.add(sb.toString());

		// 出社時間
		String start = null;
		// 退社時間
		String end = null;
		int i = 0;
		for (; i < strList.size(); i++) {
			m = DATA_PAT.matcher(strList.get(i));

			if (!m.find())
			{
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}

			String key = m.group(1);

			if (key.equals(START_KEY)) {
				// 出社時間取得
				start = m.group(4) == null ? null : m.group(4);
			} else if (key.equals(END_KEY)) {
				// 退社時間取得
				end = m.group(4) == null ? null : m.group(4);
			} else {
				// keyの不正
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}
		}

		// データの過不足チェック
		if (2 != i) {
			throw new KadaiException(ErrorCode.INVALID_COLUMNS_NUM);
		}

		model = new WorkTimeModel(date, start, end);

		return model;
	}

	/**
	 * Lv2のファイル書き出し処理
	 * @param monthModelList 月の勤怠情報データモデル
	 * @param outUpFolderPath 出力フォルダパス
	 * @throws IOException
	 * @throws KadaiException
	 */
	private static void fileOutPtOfLv2(List<WorkTimeOfMonthModel> monthModelList, String outUpFolderPath) throws IOException, KadaiException {
		for (WorkTimeOfMonthModel model : monthModelList) {
			if (model.getMonth() == null || model.getMonth().isEmpty()) {
				throw new KadaiException(ErrorCode.EMPTY_MONTH);
			}

			if (!checkDate(model.getMonth(), "yyyyMM")) {
				throw new KadaiException(ErrorCode.INVALID_MONTH);
			}

			// ファイル名指定
			StringBuilder sb = new StringBuilder(outUpFolderPath);
			sb.append("\\").append(model.getMonth()).append(".txt");

			// ファイル書き出し
			fileOutPut(sb.toString(), model.getModelList(), CHAR_SET);
		}
	}

	/**
	 * 文字を読み込み、空白文字以外を返す
	 * @param br BufferedReader
	 * @return 読み込み文字
	 * @throws KadaiException
	 * @throws IOException
	 */
	private static int getNextCheckChar(BufferedReader br) throws KadaiException, IOException {
		// 読み込み文字
		int c = br.read();
		while (-1 != c) {
			// 空白コードチェック
			if (!CommonUtil.checkBlankCode(c)) {
				// 制御コードチェック
				if (CommonUtil.checkControlCode(c)) {
					throw new KadaiException(ErrorCode.INVALID_CONTROL_CHAR);
				}
				return c;
			}
			c = br.read();
		}

		return c;
	}

	/**
	 * チェック文字列が見つかるまでの文字列を返す
	 * @param br BufferedReader
	 * @param checkC チェック文字列
	 * @return データ部分の文字列
	 * @throws KadaiException
	 * @throws IOException
	 */
	private static String getStringData(BufferedReader br, int checkC) throws KadaiException, IOException {
		StringBuilder sb = new StringBuilder();

		int c = br.read();
		while (checkC != c) {
			// チェック文字が見つからないとき
			if (-1 == c)
			{
				throw new KadaiException(ErrorCode.INVALID_STRING);
			}
			// 制御コードチェック
			if (CommonUtil.checkControlCode(c)) {
				throw new KadaiException(ErrorCode.INVALID_CONTROL_CHAR);
			}

			sb.append((char) c);
			c = br.read();
		}

		return sb.toString();
	}

	/**
	 * 勤怠時間をファイルに出力する
	 *
	 * @param anOutputPath
	 *            出力ファイルパス
	 * @param dataList
	 *            出力データリスト
	 * @param charSet
	 *            文字コード
	 * @throws 入出力の例外
	 * @since 2014/04/01
	 */
	public static void fileOutPut(String anOutputPath,
			List<WorkTimeModel> dataList, String charSet) throws IOException,
			KadaiException {
		// データが0件の場合、処理をやめる
		if (0 == dataList.size()) {
			return;
		}

		BufferedWriter bw = null;
		try {
			File file = new File(anOutputPath);
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), charSet));

			bw.write("[");
			bw.newLine();

			// 合計時間
			int total = 0;
			try {
				for (int i = 0; i < dataList.size(); i++) {
					// 書き込みデータ作成
					WorkTimeModel workTimeModel = dataList.get(i);
					String date = workTimeModel.getDate();
					// 日付が空文字
					if (CommonUtil.strIsEmpty(date)) {
						throw new KadaiException(ErrorCode.EMPTY_DAY);
					}
					// 日付が不正
					if (!checkDate(date, "yyyyMMdd")) {
						throw new KadaiException(ErrorCode.INVALID_DAY_STRING);
					}
					int workTime = Integer.parseInt(calcWorkTime(workTimeModel.getStart(), workTimeModel.getEnd()));
					total += workTime;

					// データ書き込み
					String line = String.format(
							"{\"date\":\"%s\",\"workTime\":%s,\"total\":%s}", date, workTime, total);

					bw.write(line);
					if (i != dataList.size() - 1) {
						bw.write(',');
					}
					bw.newLine();
				}

				bw.write("]");
				bw.newLine();
			} catch (KadaiException ke) {
				// KadaiExceptionの書き込み
				bw.write(ke.getErrorCode().toString());
			}
		} finally {
			bw.close();
		}
	}

	/**
	 * 日付の妥当性をチェックする
	 * @param date 日付
	 * @param format 日付のフォーマット形式
	 * @return OK:true, NG:false
	 */
	public static boolean checkDate(String date, String format) {
		// 日付の書式
		DateFormat df = new SimpleDateFormat(format);

		df.setLenient(false);

		try {
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	// 以下、2013年11月の課題--------------------------------------------------
	// パターン定義
	// パターン定義
	private static final Pattern DATE_PATTERN = Pattern.compile("^[0-9]*$");
	// 業務開始時間
	private static final int START_TIME_OF_WORK = 9 * 60;
	// 出社時刻の最大
	private static final int START_TIME_MAX = 23 * 60 + 59;
	// 退社時刻の最大
	private static final int END_TIME_MAX = 32 * 60 + 59;
	// 昼休みの開始時刻
	private static final int LUNCHS_REST_START_TIME = 12 * 60;
	// 昼休みの終了時刻
	private static final int LUNCHS_REST_END_TIME = 13 * 60;
	// 業後休みの開始時刻
	private static final int EVENING_REST_START_TIME = 18 * 60;
	// 業後休みの終了時刻
	private static final int EVENING_REST_END_TIME = 18 * 60 + 30;

	/**
	 * 出社時刻と退社時刻から勤務時間を求める
	 *
	 * @param aStartTime
	 *            出社時間
	 * @param aEndTime
	 *            退社時間
	 * @return 勤務時間
	 * @throws KadaiException
	 */
	public static String calcWorkTime(String aStartTime, String aEndTime)
			throws KadaiException {
		// 出社時間を分換算で取得
		int sTime = getMinTimeDate(aStartTime);
		if (0 > sTime || START_TIME_MAX < sTime) {
			throw new KadaiException(ErrorCode.INVALID_TIME);
		}
		sTime = START_TIME_OF_WORK > sTime ? START_TIME_OF_WORK : sTime;

		// 退社時間を分換算で取得
		int eTime = getMinTimeDate(aEndTime);
		if (0 > eTime || END_TIME_MAX < eTime) {
			throw new KadaiException(ErrorCode.INVALID_TIME);
		}

		if (eTime < sTime) {
			throw new KadaiException(ErrorCode.STARTTIME_OVER_ENDTIME);
		}

		// 休憩時間のモデル作成
		List<Recess> recessList = new ArrayList<Recess>();
		// 昼休み
		Recess recess = new Recess(LUNCHS_REST_START_TIME, LUNCHS_REST_END_TIME);
		recessList.add(recess);
		// 業後休み
		recess = new Recess(EVENING_REST_START_TIME, EVENING_REST_END_TIME);
		recessList.add(recess);

		// 勤務時間
		int officeTime = eTime - sTime;
		// 休憩時間の時間を引く
		for (Recess rec : recessList) {
			officeTime -= getRestTime(sTime, eTime, rec);
		}

		return Integer.toString(officeTime);
	}

	/**
	 * String型の時間を分換算する
	 *
	 * @param time
	 *            Stringの時間
	 * @return 分換算の時間
	 * @throws KadaiException
	 */
	private static int getMinTimeDate(String time) throws KadaiException {
		// null・空文字チェック
		if (CommonUtil.strIsEmpty(time)) {
			throw new KadaiException(ErrorCode.EMPTY_TIME);
		}

		// 数字かどうかチェック
		Matcher m = DATE_PATTERN.matcher(time);
		if (!m.find()) {
			throw new KadaiException(ErrorCode.INVALID_STRING);
		}
		// 4桁かどうかチェック
		if (4 != time.length()) {
			throw new KadaiException(ErrorCode.INVALID_TIME);
		}

		// 分
		int min = Integer.parseInt(time.substring(2, 4));
		// 時
		int hour = Integer.parseInt(time.substring(0, 2));

		if (60 <= min) {
			throw new KadaiException(ErrorCode.INVALID_TIME);
		}

		// 分換算した時間
		int minTime = hour * 60 + min;

		return minTime;
	}

	/**
	 * 休憩時間分の時間を分単位で取得する
	 *
	 * @param startTime
	 *            出社時間（分換算された時間）
	 * @param endTime
	 *            退社時間（分換算された時間）
	 * @param recess
	 *            休み時間のモデル
	 * @return 分単位の休憩時間の時間
	 */
	private static int getRestTime(int startTime, int endTime, Recess recess) {
		int sTime = startTime;
		if (recess.startTime > startTime) {
			sTime = recess.startTime;
		}
		int eTime = endTime;
		if (recess.endTime < endTime) {
			eTime = recess.endTime;
		}
		int returnTime = eTime - sTime;

		return 0 >= returnTime ? 0 : returnTime;
	}

	/**
	 * 休憩時間のモデル
	 *
	 * @author h_okita
	 * @since 2014/01/21
	 */
	private static class Recess extends Kadai {
		/**
		 * コンストラクタ
		 *
		 * @param startTime
		 *            休憩時間の開始時刻（分）
		 * @param endTime
		 *            休憩時間の終了時刻（分）
		 */
		public Recess(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		// 休憩時間の開始時刻（分）
		private int startTime;
		// 休憩時間の終了時刻（分）
		private int endTime;
	}
	// ----------------------------------------------------------------------------------------
}
