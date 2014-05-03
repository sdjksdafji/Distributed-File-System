package cs5412.project.distributed_file_system.pojo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cs5412.project.distributed_file_system.pojo.File;

@FacesConverter(forClass = cs5412.project.distributed_file_system.pojo.File.class, value = "file")
public class FileConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		File file = null;
		if (submittedValue != null && !submittedValue.trim().equals("")) {
			file = new File();
			file.setFid(Integer.parseInt(submittedValue));
		}
		return file;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null) {
			return "";
		} else {
			return Integer.toString(((File) value).getFid());
		}
	}

}
