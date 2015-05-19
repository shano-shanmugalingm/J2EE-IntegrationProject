package com.senthur.service.queue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

/**
 * This class should be used to obtain the message from the JMS queue.
 * 
 * @author Senthur Shanmugalingm
 * */
@Named
@Stateless
public class DataConsumer extends AbstractQueue {

	private MessageConsumer consumer;

	@PostConstruct
	private void initialize() {
		try {
			consumer = session.createConsumer(queue);
			connection.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will return the message from the head of the underlying JMS Queue. If no message could be obtained then the return value would be null. All
	 * classes consuming this method should handle null.
	 * 
	 * @return <code>{@link Integer}</code>
	 * @throws <code>{@link JMSException}</code>
	 * 
	 * */
	public Integer getMessage() throws JMSException {
		Integer result = null;
		Message message = consumer.receiveNoWait();
		if (message != null) {
			TextMessage textMessage = (TextMessage) message;
			result = Integer.valueOf(textMessage.getText());
		}
		return result;
	}

	@PreDestroy
	private void destroy() {
		try {
			consumer.close();
			connection.stop();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
