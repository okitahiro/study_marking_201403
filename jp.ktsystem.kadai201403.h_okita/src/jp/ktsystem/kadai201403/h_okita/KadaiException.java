package jp.ktsystem.kadai201403.h_okita;

/**
 * 課題関連の例外
 *
 * @author h_okita
 * @since 2014/04/03
 */
public class KadaiException extends Exception{
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;

	public KadaiException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
}
