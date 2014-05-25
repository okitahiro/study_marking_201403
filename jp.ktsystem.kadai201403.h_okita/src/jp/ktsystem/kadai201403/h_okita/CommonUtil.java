package jp.ktsystem.kadai201403.h_okita;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汎用メソッドクラス
 *
 * @author okita
 * @since 2014/04/01
 */
public class CommonUtil {

	/**
	 * ファイル存在チェック
	 *
	 * @param file
	 *            読み込みファイルパスの抽象クラス
	 * @return チェックOK:true, NG:false
	 */
	public static boolean checkReadFile(File file) {
		if (file.exists()) {
			// return true;
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * UTF8のBOMをスキップする
	 *
	 * @param is
	 *            InputStream
	 * @return BOMスキップ後のInputStream
	 * @throws IOException
	 */
	public static InputStream skipUTF8BOM(InputStream is) throws IOException {
		if (!is.markSupported()) {
			// マーク機能が無い場合BufferedInputStreamを被せる
			is = new BufferedInputStream(is);
		}

		// 先頭位置にマークをセット
		is.mark(3);
		if (3 <= is.available()) {
			byte[] b = new byte[3];

			// 3byte読み込み
			is.read(b, 0, 3);

			// BOMでない場合はリセット
			if ((byte)0xEF != b[0] || (byte)0xBB != b[1] || (byte)0xBF != b[2]) {
				is.reset();
			}
		}
		return is;
	}

	/**
	 * 制御文字（改行、タブ、スペースを除く）ないかチェックする
	 * @param c チェックする(int)char
	 * @return 検索ヒット:true, なし:false
	 */
	public static boolean checkControlCode(int c) {
		if (0x1f >= c && 0x00 <= c) {
			int t = 0x09; // 水平タブ
			int n = 0x0A; // 改行
			int r = 0x0D; // キャリッジリターン

			if (t == c || n == c || r == c) {
				return false;
			}
			return true;
		}

		if (0x7F == c) {
			return true;
		}

		return false;
	}

	/**
	 * 改行、タブ、スペースかどうかチェックする
	 * @param c チェックする(int)char
	 * @return 検索ヒット:true, なし:false
	 */
	public static boolean checkBlankCode(int c) {
		int t = 0x09; // 水平タブ
		int n = 0x0A; // 改行
		int r = 0x0D; // キャリッジリターン
		int s = 0x20; // 半角スペース
		int zS = (int)'　'; //全角スペース

		if (t == c || n == c || r == c || s == c || zS == c) {
			return true;
		}
		return false;
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
			try{
				for (int i = 0; i< dataList.size(); i++) {
					// 書き込みデータ作成
					WorkTimeModel workTimeModel = dataList.get(i);
					String date = workTimeModel.getDate();
					// 日付が空文字
					if (date == null || date.isEmpty()){
						throw new KadaiException(ErrorCode.EMPTY_DAY);
					}
					// 日付が不正
					if (!checkDate(date, "yyyyMMdd")){
						throw new KadaiException(ErrorCode.INVALID_DAY_STRING);
					}
					int workTime = Integer.parseInt(calcWorkTime(workTimeModel.getStart(), workTimeModel.getEnd()));
					total += workTime;
	
					// データ書き込み
					String line = String.format(
							"{\"date\":\"%s\",\"workTime\":%s,\"total\":%s}", date, workTime, total);
	
					bw.write(line);
					if(i != dataList.size() - 1){
						bw.write(',');
					}
					bw.newLine();
				}
				

				bw.write("]");
				bw.newLine();
			} catch(KadaiException ke){
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
		if (null == time || 0 == time.length()) {
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
