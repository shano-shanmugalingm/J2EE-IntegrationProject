package com.senthur;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.Before;

public abstract class AbstractIntegrationTest {

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;

	@Resource(lookup = "java:/ConnectionFactory")
	private QueueConnectionFactory connectionFactory;

	@Resource(lookup = "java:jboss/queues/UnicoQueue")
	protected Queue queue;
	
	@Before
	public void init() throws NotSupportedException, SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, JMSException {
		initializeDB();
		initializeQueue();
	}
	
	private void initializeDB() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		utx.begin();
		em.createQuery("delete from NumberSequence").executeUpdate();
		em.createQuery("delete from GreatestCommonDivisor").executeUpdate();
		utx.commit();
	}

	private void initializeQueue() throws JMSException {
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(queue);
		Message message = null;
		do {
			message = consumer.receiveNoWait();
			if (message != null)
				message.acknowledge();
		} while (message != null);

		consumer.close();
		connection.close();
	}
	
	
}
