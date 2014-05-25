package jp.ktsystem.kadai201403.h_okita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 201403 課題Lv1のテストクラス
 * @author h_okita
 * @since 2014/04/08
 */
@RunWith(JUnit4.class)
public class TestKadaiLv1_201403 {

	// カレントフォルダパス
	private static final String CURRENT_FOLDER = System.getProperty("user.dir");
	// 入力フォルダパス
	private static final String INPUT_FOLDER = CURRENT_FOLDER + "\\Lv1TestData\\input\\";
	// 出力フォルダパス
	private static final String OUTPUT_FOLDER = CURRENT_FOLDER + "\\Lv1TestData\\output\\";
	// 比較フォルダパス
	private static final String EXPECTED_FOLDER = CURRENT_FOLDER + "\\Lv1TestData\\expected\\";

	// 正常系テスト
	@Test
	public void test_N001() {
		String testFileName = "001.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N002() {
		String testFileName = "002.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N003() {
		String testFileName = "003.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N004() {
		String testFileName = "004.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N005() {
		String testFileName = "005.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N006() {
		String testFileName = "006.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N007() {
		String testFileName = "007.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N008() {
		String testFileName = "008.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N009() {
		String testFileName = "009.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N010() {
		String testFileName = "010.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N011() {
		String testFileName = "011.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_N012() {
		String testFileName = "012.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	// 異常系テスト
	@Test
	public void test_E101() {
		assertFail(null, OUTPUT_FOLDER + "101.txt", ErrorCode.INPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E102() {
		assertFail("", OUTPUT_FOLDER + "102.txt", ErrorCode.INPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E103() {
		String testFileName = "dummy.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + "103.txt", ErrorCode.INPUT_FILE_NOT_EXIST);
	}

	@Test
	public void test_E104() {
		String testFileName = "104.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.ERROR_LOADING_INPUT_FILE);
	}

	@Test
	public void test_E105() {
		String testFileName = "105.txt";
		assertFail(INPUT_FOLDER + testFileName, null, ErrorCode.OUTPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E106() {
		String testFileName = "106.txt";
		assertFail(INPUT_FOLDER + testFileName, "", ErrorCode.OUTPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E107() {
		String testFileName = "107.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + "?.txt", ErrorCode.ERROE_OUTPUT_FILE);
	}

	@Test
	public void test_E108() {
		String testFileName = "108.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E109() {
		String testFileName = "109.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E110() {
		String testFileName = "110.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E111() {
		String testFileName = "111.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E112() {
		String testFileName = "112.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E113() {
		String testFileName = "113.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E114() {
		String testFileName = "114.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E115() {
		String testFileName = "115.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E116() {
		String testFileName = "116.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E117() {
		String testFileName = "117.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E118() {
		String testFileName = "118.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E119() {
		String testFileName = "119.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E120() {
		String testFileName = "120.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E121() {
		String testFileName = "121.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E122() {
		String testFileName = "122.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E123() {
		String testFileName = "123.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E124() {
		String testFileName = "124.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E125() {
		String testFileName = "125.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E126() {
		String testFileName = "126.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E127() {
		String testFileName = "127.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E128() {
		String testFileName = "128.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E129() {
		String testFileName = "129.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E130() {
		String testFileName = "130.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E131() {
		String testFileName = "131.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E132() {
		String testFileName = "132.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E133() {
		String testFileName = "133.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E134() {
		String testFileName = "134.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E135() {
		String testFileName = "135.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E136() {
		String testFileName = "136.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E137() {
		String testFileName = "137.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E138() {
		String testFileName = "138.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E139() {
		String testFileName = "139.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E140() {
		String testFileName = "140.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E141() {
		String testFileName = "141.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E142() {
		String testFileName = "142.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E143() {
		String testFileName = "143.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E144() {
		String testFileName = "144.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_STRING);
	}

	@Test
	public void test_E145() {
		String testFileName = "145.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_STRING);
	}

	@Test
	public void test_E146() {
		String testFileName = "146.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E147() {
		String testFileName = "147.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E148() {
		String testFileName = "148.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E149() {
		String testFileName = "149.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E150() {
		String testFileName = "150.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E151() {
		String testFileName = "151.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E152() {
		String testFileName = "152.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E153() {
		String testFileName = "153.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E154() {
		String testFileName = "154.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E155() {
		String testFileName = "155.txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, EXPECTED_FOLDER + testFileName);
	}

	@Test
	public void test_E156() {
		String testFileName = "156.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_COLUMNS_NUM);
	}







	/**
	 * 課題Lv1正常系比較メソッド
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力ファイルパス
	 * @param expectedPath 比較ファイルパス
	 * @since 2014/04/07
	 */
	private void assertEquals(String anInputPath, String anOutputPath, String expectedPath) {
		FileInputStream outFis = null;
		FileInputStream exFis = null;

		try {
			// テストメソッド実行
			Kadai.parseWorkTimeData(anInputPath, anOutputPath);

			// ファイル内比較
			File outPutFile = new File(anOutputPath);
			File expectedFile = new File(expectedPath);

			byte[] outputByte = new byte[(int) outPutFile.length()];
			byte[] expectedByte = new byte[(int) expectedFile.length()];

			outFis = new FileInputStream(outPutFile);
			outFis.read(outputByte);

			exFis = new FileInputStream(expectedFile);
			exFis.read(expectedByte);

			Assert.assertArrayEquals(expectedByte, outputByte);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				outFis.close();
				exFis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 課題Lv1異常系比較メソッド
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力ファイルパス
	 * @param expectedPath 比較ファイルパス
	 * @since 2014/04/07
	 */
	private void assertFail(String anInputPath, String anOutputPath, ErrorCode expected) {
		try {
			// テストメソッド実行
			Kadai.parseWorkTimeData(anInputPath, anOutputPath);
			Assert.fail();

		} catch (KadaiException e) {
			Assert.assertEquals(expected, e.getErrorCode());
		}
	}

}
