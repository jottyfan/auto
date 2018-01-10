package de.jottyfan.auto;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.jottyfan.auto.db.jooq.enums.EnumFuel;
import de.jottyfan.auto.db.jooq.enums.EnumProvider;

/**
 * 
 * @author jotty
 *
 */
public class AddBean {

	private Integer mileage;
	private String fuel;
	private String location;
	private Float price;
	private Float amount;
	private String provider;
	private Date buydate;
	private String annotation;

	public EnumFuel getFuelEnum() {
		for (EnumFuel e : EnumFuel.values()) {
			if (e.getLiteral().equals(fuel)) {
				return e;
			}
		}
		return null;
	}

	public EnumProvider getProviderEnum() {
		for (EnumProvider e : EnumProvider.values()) {
			if (e.getLiteral().equals(provider)) {
				return e;
			}
		}
		return null;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
