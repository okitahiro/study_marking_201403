package jp.ktsystem.kadai201403.h_okita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 201403 課題Lv2のテストクラス
 * @author h_okita
 * @since 2014/04/08
 */
@RunWith(JUnit4.class)
public class TestKadaiLv2_201403 {

	// カレントフォルダパス
	private static final String CURRENT_FOLDER = System.getProperty("user.dir");
	// 入力フォルダパス
	private static final String INPUT_FOLDER = CURRENT_FOLDER + "\\Lv2TestData\\input\\";
	// 出力フォルダパス
	private static final String OUTPUT_FOLDER = CURRENT_FOLDER + "\\Lv2TestData\\output\\";
	// 比較フォルダパス
	private static final String EXPECTED_FOLDER = CURRENT_FOLDER + "\\Lv2TestData\\expected\\";

	// 正常系テスト
	@Test
	public void test_N001() {
		String testForlderName = "001";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_N002() {
		String testForlderName = "002";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	// 異常系テスト
	@Test
	public void test_E101() {
		String testForlderName = "101";
		assertFail(null, OUTPUT_FOLDER + testForlderName, ErrorCode.INPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E102() {
		String testForlderName = "102";
		assertFail("", OUTPUT_FOLDER + testForlderName, ErrorCode.INPUT_FILE_PATH_ENPTY);
	}

	@Test
	public void test_E103() {
		String testForlderName = "103";
		String testFileName = "dummy.txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INPUT_FILE_NOT_EXIST);
	}

	@Test
	public void test_E104() {
		String testForlderName = "104";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.ERROR_LOADING_INPUT_FILE);
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
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + "?", ErrorCode.ERROE_OUTPUT_FILE);
	}

	@Test
	public void test_E108() {
		String testForlderName = "108";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + "dummy", ErrorCode.ERROE_OUTPUT_FILE);
	}

	@Test
	public void test_E109() {
		String testForlderName = "109";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E110() {
		String testForlderName = "110";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E111() {
		String testForlderName = "111";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E112() {
		String testForlderName = "112";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E113() {
		String testForlderName = "113";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E114() {
		String testForlderName = "114";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E115() {
		String testForlderName = "115";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E116() {
		String testForlderName = "116";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E117() {
		String testForlderName = "117";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E118() {
		String testForlderName = "118";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E119() {
		String testForlderName = "119";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E120() {
		String testForlderName = "120";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E121() {
		String testForlderName = "121";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E122() {
		String testForlderName = "122";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E123() {
		String testForlderName = "123";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E124() {
		String testForlderName = "124";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E125() {
		String testForlderName = "125";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E126() {
		String testForlderName = "126";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E127() {
		String testForlderName = "127";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testFileName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E128() {
		String testForlderName = "128";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E129() {
		String testForlderName = "129";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E130() {
		String testForlderName = "130";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E131() {
		String testForlderName = "131";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E132() {
		String testForlderName = "132";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E133() {
		String testForlderName = "133";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E134() {
		String testForlderName = "134";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E135() {
		String testForlderName = "135";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E136() {
		String testForlderName = "136";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E137() {
		String testForlderName = "137";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E138() {
		String testForlderName = "138";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_CONTROL_CHAR);
	}

	@Test
	public void test_E139() {
		String testForlderName = "139";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.EMPTY_MONTH);
	}

	@Test
	public void test_E140() {
		String testForlderName = "140";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E141() {
		String testForlderName = "141";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E142() {
		String testForlderName = "142";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E143() {
		String testForlderName = "143";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.EMPTY_MONTH);
	}

	@Test
	public void test_E144() {
		String testForlderName = "144";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E145() {
		String testForlderName = "145";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E146() {
		String testForlderName = "146";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E147() {
		String testForlderName = "147";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_STRING);
	}

	@Test
	public void test_E148() {
		String testForlderName = "148";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_STRING);
	}

	@Test
	public void test_E149() {
		String testForlderName = "149";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_MONTH);
	}

	@Test
	public void test_E150() {
		String testForlderName = "150";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E151() {
		String testForlderName = "151";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E152() {
		String testForlderName = "152";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E153() {
		String testForlderName = "153";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E154() {
		String testForlderName = "154";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E155() {
		String testForlderName = "155";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E156() {
		String testForlderName = "156";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E157() {
		String testForlderName = "157";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E158() {
		String testForlderName = "158";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}


	@Test
	public void test_E159() {
		String testForlderName = "159";
		String testFileName = testForlderName + ".txt";
		assertEquals(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, EXPECTED_FOLDER + testForlderName);
	}

	@Test
	public void test_E160() {
		String testForlderName = "160";
		String testFileName = testForlderName + ".txt";
		assertFail(INPUT_FOLDER + testFileName, OUTPUT_FOLDER + testForlderName, ErrorCode.INVALID_COLUMNS_NUM);
	}

	/**
	 * 課題Lv2正常系比較メソッド
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力フォルダパス
	 * @param expectedPath 比較フォルダパス
	 * @since 2014/04/07
	 */
	private void assertEquals(String anInputPath, String anOutputPath, String expectedPath) {
		FileInputStream outFis = null;
		FileInputStream exFis = null;

		try {
			// テストメソッド実行
			Kadai.parseWorkTimeDataLv2(anInputPath, anOutputPath);

			// ファイル内比較
			File outPutFolder = new File(anOutputPath);
			File expectedFolder = new File(expectedPath);

			File[] outFiles = outPutFolder.listFiles();
			File[] exFiles = expectedFolder.listFiles();

			if (outFiles.length != exFiles.length) {
				Assert.fail("ファイル数が一致しません");
			}

			Arrays.sort(outFiles);
			Arrays.sort(exFiles);

			for (int i = 0; i < outFiles.length; i++) {
				byte[] outputByte = new byte[(int) outFiles[i].length()];
				byte[] expectedByte = new byte[(int) exFiles[i].length()];

				outFis = new FileInputStream(outFiles[i]);
				outFis.read(outputByte);

				exFis = new FileInputStream(exFiles[i]);
				exFis.read(expectedByte);

				if (!Arrays.equals(expectedByte, outputByte)) {
					Assert.fail(outFiles[i].getName() + "ファイルが一致しません");
				}
			}

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
	 * 課題Lv2異常系比較メソッド
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath 出力ファイルパス
	 * @param expectedPath 比較ファイルパス
	 * @since 2014/04/07
	 */
	private void assertFail(String anInputPath, String anOutputPath, ErrorCode expected) {
		try {
			// テストメソッド実行
			Kadai.parseWorkTimeDataLv2(anInputPath, anOutputPath);
			Assert.fail();

		} catch (KadaiException e) {
			Assert.assertEquals(expected, e.getErrorCode());
		}
	}

}
