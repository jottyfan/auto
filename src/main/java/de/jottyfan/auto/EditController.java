package de.jottyfan.auto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class EditController {

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{editModel}")
	private EditModel model;

	@ManagedProperty(value = "#{showModel}")
	private ShowModel showModel;

	public String toEdit(MileageViewBean bean) {
		if (model == null) {
			model = new EditModel();
		}
		model.setBean(bean);
		return "/pages/edit.jsf";
	}

	public String doUpdate() {
		boolean result = model.update(facesContext);
		if (result) {
			showModel.loadData(facesContext);
			showModel.setExpandedPanels("table");
			return "/pages/add.jsf";
		} else {
			return "";
		}
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public EditModel getModel() {
		return model;
	}

	public void setModel(EditModel model) {
		this.model = model;
	}

	public ShowModel getShowModel() {
		return showModel;
	}

	public void setShowModel(ShowModel showModel) {
		this.showModel = showModel;
	}
}
