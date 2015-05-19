package com.senthur;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.senthur.dao.BaseDao;
import com.senthur.dao.GreatestCommonDivosorDao;
import com.senthur.dao.NumberSequenceDao;
import com.senthur.dao.impl.AbstractBaseDao;
import com.senthur.dao.impl.GreatestCommonDivosorDaoBean;
import com.senthur.dao.impl.NumberSequenceDaoBean;
import com.senthur.dto.NumberSequence;
import com.senthur.service.delegate.GreatestCommonDivisorService;
import com.senthur.service.delegate.NumberSequenceService;
import com.senthur.service.delegate.impl.GreatestCommonDivisorServiceBean;
import com.senthur.service.delegate.impl.NumberSequenceServiceBean;
import com.senthur.service.queue.AbstractQueue;
import com.senthur.service.queue.AbstractSender;
import com.senthur.service.queue.DataConsumer;
import com.senthur.service.queue.DataPublisher;

@RunWith(Arquillian.class)
public class UnicoServiceIntegrationIT extends AbstractIntegrationTest {

	@Inject
	private NumberSequenceService numberSequenceService;

	@Inject
	private GreatestCommonDivisorService greatestCommonDivisorService;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(AbstractIntegrationTest.class)
				.addClass(NumberSequenceService.class)
				.addClass(NumberSequenceServiceBean.class).addClass(GreatestCommonDivisorService.class)
				.addClass(GreatestCommonDivisorServiceBean.class).addClass(BaseDao.class)
				.addClass(AbstractBaseDao.class).addClass(NumberSequenceDao.class)
				.addClass(NumberSequenceDaoBean.class).addClass(GreatestCommonDivosorDao.class)
				.addClass(GreatestCommonDivosorDaoBean.class).addClass(DataPublisher.class)
				.addClass(DataConsumer.class).addClass(AbstractSender.class).addClass(AbstractQueue.class)
				.addPackage(NumberSequence.class.getPackage())
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void essentialBeansShouldBeDeployed() {
		assertNotNull("Number Sequence Service Bean is not deployed", numberSequenceService);
		assertNotNull("Greatest Common Divisor Service Bean is not deployed", greatestCommonDivisorService);
	}
	
	@Test
	public void getAllAvialableNumberSequencesShouldReturnEmptyList() {
		List<Integer> avialableNumberSequences = numberSequenceService.getAllAvialableNumberSequences();
		assertTrue(avialableNumberSequences.size() == 0);
	}
	
	@Test
	public void getAllAvailableGCDShouldReturnEmptyList() {
		List<Integer> avialableGCD = greatestCommonDivisorService.getAllGreatestCommonDivisors();
		assertTrue(avialableGCD.size() == 0);
	}

	@Test
	public void shouldCalculateValidGreatesCommonDivisor() {
		sendDataShouldReturnTrue();
		calculateGCDShouldReturnValidResult();
	}
	
	@Test
	public void getAllAvialableNumberSequencesShouldReturnPopulatedListWhenCalledAfterCalculateGCD() {
		sendDataShouldReturnTrue();
		calculateGCDShouldReturnValidResult();
		List<Integer> avialableNumberSequences = numberSequenceService.getAllAvialableNumberSequences();
		assertTrue(avialableNumberSequences.size() == 2);
	}
	
	@Test
	public void getAllGreatestCommonDivisorsShouldReturnPopulatedListWhenCalledAfterCalculateGCD() {
		sendDataShouldReturnTrue();
		calculateGCDShouldReturnValidResult();
		List<Integer> avialableGCD = greatestCommonDivisorService.getAllGreatestCommonDivisors();
		assertTrue(avialableGCD.size() == 1);
	}
	
	private void sendDataShouldReturnTrue() {
		assertTrue(numberSequenceService.sendData(10, 30));
	}
	
	private void calculateGCDShouldReturnValidResult() {
		assertTrue(greatestCommonDivisorService.calculateGCD() > 0);
	}

}
