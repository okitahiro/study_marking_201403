package jp.ktsystem.kadai201403.h_okita;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 汎用メソッドクラス
 *
 * @author okita
 * @since 2014/04/01
 */
public class CommonUtil {

	// 文字コード（Tab）
	public static final int T_CODE = 0x09;
	// 文字コード（改行）
	public static final int N_CODE = 0x0A;
	// 文字コード（キャリッジリターン）
	public static final int R_CODE = 0x0D;
	// 文字コード（半角スペース）
	public static final int S_CODE = 0x20;
	// 文字コード（全角スペース）
	public static final int ZS_CODE = (int) '　';

	/**
	 * 文字列のnull、空文字チェック
	 * @param checkStr チェックする文字列
	 * @return 文字列がnull、空文字：true、それ以外：false
	 */
	public static boolean strIsEmpty(String checkStr) {
		return (null == checkStr || 0 == checkStr.length());
	}

	/**
	 * ファイル存在チェック
	 *
	 * @param file
	 *            読み込みファイルパスの抽象クラス
	 * @return チェックOK:true, NG:false
	 */
	public static boolean checkReadFile(File file) {
		return (file.exists() && (file.isFile() && file.canRead()));
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
			if ((byte) 0xEF != b[0] || (byte) 0xBB != b[1] || (byte) 0xBF != b[2]) {
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
			return (T_CODE != c && N_CODE != c && R_CODE != c);
		}

		return (0x7F == c);
	}

	/**
	 * 改行、タブ、スペースかどうかチェックする
	 * @param c チェックする(int)char
	 * @return 検索ヒット:true, なし:false
	 */
	public static boolean checkBlankCode(int c) {
		return (T_CODE == c || N_CODE == c || R_CODE == c || S_CODE == c || ZS_CODE == c);
	}
}
