package de.bonprix.user.dto1;

import java.io.Serializable;

public class AuthenticationResult implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Result {
	        OK,
	        INVALID_CREDENTIALS
	    }

	    private   Result result;
	    private   String authKey;

//	    public AuthenticationResult(Result result, String authKey) {
//	        super();
//	        this.result = result;
//	        this.authKey = authKey;
//	    }

	    public Result getResult() {
	        return this.result;
	    }

	    public String getAuthKey() {
	        return this.authKey;
	    }

	    @Override
	    public String toString() {
	        return "AuthenticationResult [result=" + this.result + ", authKey=" + this.authKey + "]";
	    }

}
