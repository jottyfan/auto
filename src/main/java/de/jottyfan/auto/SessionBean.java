package de.jottyfan.auto;

import java.util.UUID;

/**
 * 
 * @author jotty
 *
 */
public class SessionBean {

	private UUID uuid;

	/**
	 * start a session key is valid
	 * 
	 * @param s
	 * @return true if new session was started, false otherwise
	 */
	public boolean startSession(String key) {
		if (key.equals("secret")) {
			uuid = UUID.randomUUID();
		} else {
			uuid = null;
		}
		return uuid != null;
	}

	/**
	 * invalidate session
	 */
	public void invalidate() {
		uuid = null;
	}

	/**
	 * check if session is valid
	 * 
	 * @return true if session is valid, false otherwise
	 */
	public boolean isValid() {
		return uuid != null;
	}

	public UUID getUuid() {
		return uuid;
	}
}
