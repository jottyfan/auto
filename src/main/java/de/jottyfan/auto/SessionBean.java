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
	 * start a session if valid is true
	 * 
	 * @param valids
	 * @return true if new session was started, false otherwise
	 */
	public boolean startSession(boolean valid) {
		uuid = valid ? UUID.randomUUID() : null;
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
