package de.bonprix.user.dto1;

import java.io.Serializable;


public class AuthenticationAttempt implements Serializable{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationAttempt(String username, String password, Long clientId) {
		super();
		this.username = username;
		this.password = password;
		this.clientId = clientId;
	}

	private String username;

	    private String password;

	    private Long clientId;

	    public String getUsername() {
	        return this.username;
	    }

	    public void setUsername(final String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return this.password;
	    }

	    public void setPassword(final String password) {
	        this.password = password;
	    }

	    public Long getClientId() {
	        return this.clientId;
	    }

	    public void setClientId(final Long clientId) {
	        this.clientId = clientId;
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
	        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
	        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final AuthenticationAttempt other = (AuthenticationAttempt) obj;
	        if (this.clientId == null) {
	            if (other.clientId != null) {
	                return false;
	            }
	        }
	        else if (!this.clientId.equals(other.clientId)) {
	            return false;
	        }
	        if (this.password == null) {
	            if (other.password != null) {
	                return false;
	            }
	        }
	        else if (!this.password.equals(other.password)) {
	            return false;
	        }
	        if (this.username == null) {
	            if (other.username != null) {
	                return false;
	            }
	        }
	        else if (!this.username.equals(other.username)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "AuthenticationUser [username=" + this.username + ", password=" + this.password + ", clientId=" + this.clientId + "]";
	    }
}
