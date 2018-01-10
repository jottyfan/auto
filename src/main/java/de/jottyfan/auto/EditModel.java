package de.jottyfan.auto;

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
public class EditModel {
	private MileageViewBean bean;

	public MileageViewBean getBean() {
		return bean;
	}

	public void setBean(MileageViewBean bean) {
		this.bean = bean;
	}

	/**
	 * update bean in db
	 * 
	 * @param facesContext
	 * @return true for successful update, false otherwise
	 */
	public boolean update(FacesContext facesContext) {
		try {
			if (!new Gateway(facesContext).update(bean)) {
				throw new DataAccessException("keine Daten zum Aktualisieren gefunden");
			}
			return true;
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "update error", e.getMessage()));
			return false;
		}
	}
}
