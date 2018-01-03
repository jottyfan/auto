package de.jottyfan.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import de.jottyfan.auto.db.jooq.enums.EnumFuel;
import de.jottyfan.auto.db.jooq.enums.EnumProvider;

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
	private List<EnumFuel> fuels;
	private List<EnumProvider> providers;

	private AddBean addBean;

	private String key;

	public boolean startSession(FacesContext facesContext) {
		if (sessionBean == null) {
			sessionBean = new SessionBean();
		}
		boolean newSession = sessionBean.startSession(key);
		if (!newSession) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sitzung ungültig",
					"Die Sitzung konnte nicht gestartet werden."));
		} else {
			Gateway gw = new Gateway(facesContext);
			locations = gw.getLocations();
			fuels = gw.getFuels();
			providers = gw.getProviders();
			addBean = new AddBean();
			// setting defaults
			addBean.setFuel(EnumFuel.E10.getLiteral());
			addBean.setLocation("Dresden");
			addBean.setProvider(EnumProvider.Kaufland.getLiteral());
			addBean.setBuydate(new Date());
		}
		return newSession;
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
		for (EnumFuel fuel : fuels) {
			buf.append(fuel.getLiteral()).append(",");
		}
		return buf.toString();
	}

	public String getProviders() {
		StringBuilder buf = new StringBuilder();
		for (EnumProvider prov : providers) {
			buf.append(prov.getLiteral()).append(",");
		}
		return buf.toString();
	}

	public AddBean getAddBean() {
		return addBean;
	}
}
