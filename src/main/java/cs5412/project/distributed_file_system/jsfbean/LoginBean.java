package cs5412.project.distributed_file_system.jsfbean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.pojo.User;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("loginBean")
@URLMapping(id = "login", pattern = "/signin", viewId = "/views/UserAccount/SignIn.xhtml")
public class LoginBean {
	private String username;
	private String password;

	@Inject
	private UserAccountService userAccountService;

	public String signIn() {
		User user = new User();
		user.setUsername(username);
		user.setUnhashedPassword(password);
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		if (userAccountService.login(user, response)) {
			return "pretty:selectBranch";
		} else {
			return null;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
