package com.senthur.service.queue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;

/**
 * Abstract representation of JMS Queue functionality
 * @author Senthur Shanmugalingm
 * */
public abstract class AbstractQueue {

	@Resource(lookup = "java:/ConnectionFactory")
	private QueueConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:jboss/queues/UnicoQueue")
	protected Queue queue;
	
	protected QueueConnection connection;
	
	protected QueueSession session;
	
	@PostConstruct
	private void initialize() {
		try {
			connection = connectionFactory.createQueueConnection();
			session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	private void destroy() {
		try {
			connection.stop();
			session.close();
			connection.close();
		} catch (JMSException e) {
		}
	}
}
