package cs5412.project.distributed_file_system.pojo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cs5412.project.distributed_file_system.pojo.History;

@FacesConverter(forClass = cs5412.project.distributed_file_system.pojo.History.class, value = "history")
public class HistoryConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		History history = null;
		if (submittedValue != null && !submittedValue.trim().equals("")) {
			history = new History();
			history.setHid(Integer.parseInt(submittedValue));
		}
		return history;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null) {
			return "";
		} else {
			return Integer.toString(((History) value).getHid());
		}
	}
}
