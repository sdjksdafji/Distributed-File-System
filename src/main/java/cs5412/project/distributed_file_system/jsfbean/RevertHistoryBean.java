package cs5412.project.distributed_file_system.jsfbean;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.HistoryDAO;
import cs5412.project.distributed_file_system.pojo.History;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("revertHistoryBean")
@URLMapping(id = "revertHistory", pattern = "/history", viewId = "/views/FileBrowser/RevertHistory.xhtml")
public class RevertHistoryBean {
	private History selectedHistory; // only hid is valid
	private List<History> histories;
	int userId;

	@Inject
	private UserAccountService userAccountService;

	@Inject
	HistoryDAO historyDao;

	@URLAction
	public void init() {
		checkLoginCookie();
		readHistoryListForUser();
	}

	private void readHistoryListForUser() {
		if (this.userId >= 0) {
			this.histories = this.historyDao.getLatestNHistoryForUser(
					this.userId, 10);
		}
	}

	private void checkLoginCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.userId = this.userAccountService.getUidFromCookie(request);
	}

	public void revertActionListener(ActionEvent actionEvent) {
		if (this.selectedHistory != null) {
			this.selectedHistory = this.historyDao
					.getHistoryByHid(this.selectedHistory.getHid());
			this.historyDao.revertHistory(this.selectedHistory);
		}
	}

	public History getSelectedHistory() {
		return selectedHistory;
	}

	public void setSelectedHistory(History selectedHistory) {
		this.selectedHistory = selectedHistory;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

}
