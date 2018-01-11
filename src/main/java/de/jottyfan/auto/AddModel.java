package de.jottyfan.auto;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class AddModel {

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	private List<String> locations;
	private List<String> fuels;
	private List<String> providers;

	private AddBean addBean;

	private String key;

	public boolean startSession(FacesContext facesContext) {
		if (sessionBean == null) {
			sessionBean = new SessionBean();
		}
		try {
			Gateway gw = new Gateway(facesContext);
			boolean newSession = sessionBean.startSession(gw.checkLogin(key));
			if (!newSession) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sitzung ungültig",
						"Die Sitzung konnte nicht gestartet werden."));
			} else {
				locations = gw.getLocations();
				fuels = gw.getFuels();
				providers = gw.getProviders();
				resetAddBean();
			}
			return newSession;
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sitzung kaputt", e.getMessage()));
			return false;
		}
	}
	
	private void resetAddBean() {
		addBean = new AddBean();
		// setting defaults
		addBean.setFuel("E10");
		addBean.setLocation("Dresden");
		addBean.setProvider("Kaufland");
		addBean.setBuydate(new Date());
	}

	public void invalidateSession(FacesContext facesContext) {
		sessionBean.invalidate();
	}

	public boolean add(FacesContext facesContext) {
		try {
			Gateway gw = new Gateway(facesContext);
			if (gw.add(addBean)) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "neuer Datensatz", gw.getLastDataset()));
				resetAddBean();
				return true;
			} else {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Daten wurden nicht übertragen."));
				return false;
			}
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", e.getMessage()));
			return false;
		}
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public String getLocations() {
		StringBuilder buf = new StringBuilder();
		for (String loc : locations) {
			buf.append(loc).append(",");
		}
		return buf.toString();
	}

	public String getFuels() {
		StringBuilder buf = new StringBuilder();
		for (String fuel : fuels) {
			buf.append(fuel).append(",");
		}
		return buf.toString();
	}

	public String getProviders() {
		StringBuilder buf = new StringBuilder();
		for (String prov : providers) {
			buf.append(prov).append(",");
		}
		return buf.toString();
	}

	public AddBean getAddBean() {
		return addBean;
	}
}
