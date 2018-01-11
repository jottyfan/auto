package de.jottyfan.auto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jotty
 *
 */
public class MileageViewBean {
	private Integer pk;
	private BigDecimal amount;
	private String annotation;
	private Date buydate;
	private String fuel;
	private String location;
	private Integer mileage;
	private BigDecimal price;
	private String provider;
	private BigDecimal europroliter;

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
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

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public BigDecimal getEuroproliter() {
		return europroliter;
	}

	public void setEuroproliter(BigDecimal europroliter) {
		this.europroliter = europroliter;
	}
}
