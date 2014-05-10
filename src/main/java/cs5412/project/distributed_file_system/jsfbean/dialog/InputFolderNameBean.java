package cs5412.project.distributed_file_system.jsfbean.dialog;

import java.io.Serializable;

import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class InputFolderNameBean implements Serializable {
	private String name;

	public void submit() {
		RequestContext.getCurrentInstance().closeDialog(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
