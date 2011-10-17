package at.ac.tuwien.infosys.aicc11.legacy;

/***
 * Exception thrown by the mocked legacy system
 * @author stefan
 *
 */
public class LegacyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LegacyException(String msg)
	{
		super(msg);
	}
}
