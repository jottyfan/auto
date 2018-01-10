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
public class AddController {

	@ManagedProperty(value="#{addModel}")
	private AddModel addModel;

	@ManagedProperty(value="#{showModel}")
	private ShowModel showModel;
	
	@ManagedProperty(value="#{facesContext}")
	private FacesContext facesContext;
	
	public String doStartSession() {
		boolean newSession = addModel.startSession(facesContext);
		return newSession ? toAdd() : "/pages/login.jsf";
	}
	
	public String doInvalidateSession() {
		addModel.invalidateSession(facesContext);
		return "/pages/login.jsf";
	}
	
	public String toAdd() {
		showModel.loadData(facesContext);
		return "/pages/add.jsf";
	}
	
	public String doAdd() {
		addModel.add(facesContext);
		return toAdd();
	}
	
	public AddModel getAddModel() {
		return addModel;
	}

	public void setAddModel(AddModel addModel) {
		this.addModel = addModel;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public ShowModel getShowModel() {
		return showModel;
	}

	public void setShowModel(ShowModel showModel) {
		this.showModel = showModel;
	}	
}
