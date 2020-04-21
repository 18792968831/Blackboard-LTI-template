package cn.com.blackboard.lti.receiver.exception;

/**      
 * GenericLTIException
 *
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
public class GenericLTIException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GenericLTIException (Exception ex) {
        super (ex);        
    }
}
