package at.ac.tuwien.infosys.aic11.server;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
	System.out.println("WS-S pwCallback starting to handle");
	WSPasswordCallback passwordCallback = (WSPasswordCallback) callbacks[0];
	System.out.println("WS-S pwCallback: " + passwordCallback);
	String ident = passwordCallback.getIdentifier();
	System.out.println("WS-S Identifier: " + ident);
	
	if (ident.equals("aic11"))
	    passwordCallback.setPassword("supersecret");
    }

}
