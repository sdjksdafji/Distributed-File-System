package cs5412.project.distributed_file_system.jsfbean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.pojo.User;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("registerBean")
@URLMapping(id = "register", pattern = "/signup", viewId = "/views/UserAccount/SignUp.xhtml")
public class RegisterBean {
	private String email;
	private String username;
	private String password;

	@Inject
	private UserAccountService userAccountService;

	public String register() {
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setUnhashedPassword(password);
		if (userAccountService.register(user)) {
			return "pretty:login";
		} else {
			return null;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
