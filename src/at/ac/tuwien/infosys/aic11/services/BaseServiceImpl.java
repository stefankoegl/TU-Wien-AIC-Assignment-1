package at.ac.tuwien.infosys.aic11.services;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseServiceImpl 
{
	protected Logger logger; 
	
	protected BaseServiceImpl()
	{
		
	}
	
	protected void entering(String method, Object[] params)
	{
        if (logger.isLoggable(Level.INFO)) 
        {
            //logger.entering(this.getClass().getName(), method, params);
        	String msg = "Entering Method " + method + " Params: ";
        	for (Object obj : params) {
        		msg += obj.toString() + "; ";
        	}
        	logger.info(msg);
        }
	}
	
	protected void exiting(String method, Object result)
	{
        if (logger.isLoggable(Level.INFO)) 
        {
            //logger.exiting(this.getClass().getName(), method, result);
        	logger.info("Exiting Method " + method + " Result: " + result.toString());
        }
	}
	
	protected void exiting(String method)
	{
        if (logger.isLoggable(Level.INFO)) 
        {
            //logger.exiting(this.getClass().getName(), method);
        	logger.info("Exiting Method " + method);
        }
	}	
	
	protected void logExceptionCatch(String method, Throwable exc)
	{
		/* FIXME: better exception string */
		logger.severe(method + " caught exception: " + exc.toString());
	}
	
	protected void logExceptionThrow(String method, Throwable exc)
	{
		/* FIXME: better exception string */
		logger.severe(method + " threw exception: " + exc.toString());
	}
}
