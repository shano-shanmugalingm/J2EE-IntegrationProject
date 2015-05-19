package com.senthur.service.queue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.QueueSender;
import javax.jms.TextMessage;

/**
 * Abstract representation of Message Sender to an underlying queue
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
public abstract class AbstractSender extends AbstractQueue {

	private QueueSender sender;

	@PostConstruct
	private void initialize() {
		try {
			sender = session.createSender(queue);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends the message to an underlying queue.
	 * 
	 * @param message
	 *            <code>{@link TextMessage}</code> the message to be sent
	 * */
	protected void send(TextMessage message) throws JMSException {
		sender.send(message);
	}

	/**
	 * Creates a message to be sent
	 * 
	 * @param value
	 *            <code>{@link Integer}</code> to be converted to a <code>{@link TextMessage}</code>
	 * 
	 * @return code>{@link TextMessage}</code> the encoded message
	 * */
	protected TextMessage createMessage(Integer value) throws JMSException {
		return session.createTextMessage(value.toString());
	}

	@PreDestroy
	private void destroy() {
		if (sender != null)
			try {
				sender.close();
			} catch (JMSException e) {
			}
	}
}
