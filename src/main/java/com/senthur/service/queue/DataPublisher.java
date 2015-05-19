package com.senthur.service.queue;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jms.JMSException;

/**
 * Use this class to publish message to the underlying JMS Queue.
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
@Named
@Stateless
public class DataPublisher extends AbstractSender {

	/**
	 * Publishes the message to the under lying JMS queue
	 * 
	 * @param param1
	 *            <code>int</code> the first parameter to be published.
	 * @param param2
	 *            <code>int</code> the second parameter to be published.
	 * 
	 * @return <code>boolean</code> true if the publishing was successful, false otherwise.
	 * */
	public boolean publishMessage(int param1, int param2) {
		boolean result = true;
		try {
			send(createMessage(param1));
			send(createMessage(param2));
		} catch (JMSException e) {
			result = false;
		}
		return result;
	}

}
