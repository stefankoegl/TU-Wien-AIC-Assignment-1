package at.ac.tuwien.infosys.aic11.services;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseServiceImpl 
{
	protected Logger logger; 
	
	protected BaseServiceImpl()
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	protected void entering(String method, Object[] params)
	{
		/* FIXME: we should as INFO */ 
        if (logger.isLoggable(Level.FINER)) 
        {
            logger.entering(this.getClass().getName(), method, params);
        }
	}
	
	protected void exiting(String method, Object result)
	{
		/* FIXME: we should as INFO */
        if (logger.isLoggable(Level.FINER)) 
        {
            logger.exiting(this.getClass().getName(), method, result);
        }
	}
	
	protected void exiting(String method)
	{
		/* FIXME: we should as INFO */
        if (logger.isLoggable(Level.FINER)) 
        {
            logger.exiting(this.getClass().getName(), method);
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
