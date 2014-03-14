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
		// TODO Auto-generated method stub
		if (!submittedValue.trim().equals("")) {
			File file = new File();
			file.setName(submittedValue);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if (value == null) {
            return "";
        } else {
            return ((File)value).getName();
        }
	}

}
