package cs5412.project.distributed_file_system.prettyfaces_teset;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@Named
@Scope("request")
@URLMapping(id = "helloworld", pattern = "/hello/#{helloWorldBean.url_variable}", viewId = "/views/prettyFaceTest/helloworld.xhtml")
public class HelloWorldBean {
	private String variable;
	private String url_variable;

	public String getVariable() {
		System.out
				.println("-------------------------------- getVariable called ------------------------------------");
		this.variable = this.url_variable;
		return variable;
	}

	public void setVariable(String variable) {
		System.out
				.println("-------------------------------- setVariable called ------------------------------------");
		this.variable = variable;
	}

	public String getUrl_variable() {
		System.out
				.println("-------------------------------- getUrl_variable called ------------------------------------");
		return url_variable;
	}

	public void setUrl_variable(String url_variable) {
		System.out
				.println("-------------------------------- setUrl_variable called ------------------------------------");
		this.url_variable = url_variable;
	}

}
