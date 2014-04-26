package cs5412.project.distributed_file_system.dao.jdbc;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.dao.HistoryDAO;
import cs5412.project.distributed_file_system.pojo.History;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class HistoryJdbcDAOTest {

	@Inject
	private HistoryDAO historyDao;

	@Test
	public void testSetDataSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHistoryByHid() {
		int hid = 3;
		History h = historyDao.getHistoryByHid(hid);
		System.out.println(h.getHid() + " " + h.getTs() + " " + h.getUid()
				+ " " + h.getOldFid() + " " + h.getNewFid() + " "
				+ h.getOperationType());
		if (h.getHid() <= 0) {
			fail("get history fail");
		}
	}

	@Test
	public void testCreateHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteHisotry() {
		fail("Not yet implemented");
	}

}
