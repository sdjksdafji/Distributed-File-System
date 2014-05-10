package cs5412.project.distributed_file_system.jsfbean;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@Named
@Scope("request")
@URLBeanName("loginBean")
@URLMapping(id = "login", pattern = "/signin", viewId = "/views/UserAccount/SignIn.xhtml")
public class LoginBean {

}
