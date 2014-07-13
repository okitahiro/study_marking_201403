package jp.ktsystem.kadai201403.h_okita;

/**
 * エラーコード
 *
 * @author h_okita
 * @since 2014/04/03
 */
public enum ErrorCode {
	// 1：入力文字列がnull、または空文字
	EMPTY,
	// 2：入力文字列が不正
	INVALID_STRING,
	// 3：入力文字列が出社時刻、退社時刻として不正
	INVALID_TIME,
	// 4：退社時刻が出社時刻以前
	STARTTIME_OVER_ENDTIME,
	// 5：データのカラム数が不正
	INVALID_COLUMNS_NUM,
	// 6：入力ファイルのパスがnullまたは空文字
	INPUT_FILE_PATH_ENPTY,
	// 7：入力ファイルが存在しない
	INPUT_FILE_NOT_EXIST,
	// 8：入力ファイルの読み込みに失敗した
	ERROR_LOADING_INPUT_FILE,
	// 6：出力ファイルのパスがnullまたは空文字
	OUTPUT_FILE_PATH_ENPTY,
	// 10：ファイルの出力に失敗した
	ERROE_OUTPUT_FILE,
	// 11：ファイル内に制御文字（改行、タブ、スペースを除く）が含まれる
	INVALID_CONTROL_CHAR,
	// 12：日付が不正
	INVALID_DAY_STRING,
	// 13：日付がnullまたは空文字
	EMPTY_DAY,
	// 14：出社・退社時間がnullまたは空文字
	EMPTY_TIME,
	// 15：年月がnullまたは空文字
	EMPTY_MONTH,
	// 16：年月が不正
	INVALID_MONTH;
}
