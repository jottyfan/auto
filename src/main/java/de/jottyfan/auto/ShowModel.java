package de.jottyfan.auto;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class ShowModel {
	
	private String expandedPanels;
	
	private List<MileageViewBean> data;
	
	/**
	 * load data from database
	 * 
	 * @param facesContext
	 */
	public void loadData(FacesContext facesContext) {
		try {
		data = new Gateway(facesContext).getData();
		}catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datenbankfehler", e.getMessage()));
		}
	}

	public List<MileageViewBean> getData() {
		return data;
	}

	public String getExpandedPanels() {
		return expandedPanels;
	}

	public void setExpandedPanels(String expandedPanels) {
		this.expandedPanels = expandedPanels;
	}
}
