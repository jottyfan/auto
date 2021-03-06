package de.jottyfan.auto;

import static de.jottyfan.auto.db.jooq.Tables.T_MILEAGE;
import static de.jottyfan.auto.db.jooq.Tables.V_LOGIN;
import static de.jottyfan.auto.db.jooq.Tables.V_MILEAGE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertValuesStep8;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSeekStep1;
import org.jooq.SelectWithTiesStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.auto.db.jooq.tables.records.TMileageRecord;

/**
 * 
 * @author jotty
 *
 */
public class Gateway {

	private static final Logger LOGGER = LogManager.getLogger(Gateway.class);

	private DSLContext jooq;

	public Gateway(FacesContext facesContext) throws DataAccessException {
		try {
			String url = "jdbc:postgresql:auto";
			Class.forName("org.postgresql.Driver");
			jooq = DSL.using(DriverManager.getConnection(url), SQLDialect.POSTGRES_9_5);
		} catch (SQLException | ClassNotFoundException e) {
			facesContext.addMessage("no database connection", new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"could not establish database connection", e.getMessage()));
			throw new DataAccessException(e.getMessage());
		}
	}

	/**
	 * get all locations from db
	 * 
	 * @return found locations
	 */
	public List<String> getLocations() {
		SelectJoinStep<Record1<String>> sql = jooq
		// @formatter:off
			.selectDistinct(T_MILEAGE.LOCATION)
			.from(T_MILEAGE);
		// @formatter:on
		LOGGER.debug(sql.toString());
		List<String> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			list.add(r.get(T_MILEAGE.LOCATION));
		}
		return list;
	}

	/**
	 * get all fuels
	 * 
	 * @return fuels
	 */
	public List<String> getFuels() {
		SelectSeekStep1<Record1<String>, String> sql = jooq
		// @formatter:off
		  .selectDistinct(T_MILEAGE.FUEL)
		  .from(T_MILEAGE)
		  .orderBy(T_MILEAGE.FUEL);
		// @formatter:on
		LOGGER.debug(sql.toString());
		List<String> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			list.add(r.get(T_MILEAGE.FUEL));
		}
		return list;
	}

	/**
	 * get all providers
	 * 
	 * @return providers
	 */
	public List<String> getProviders() {
		SelectSeekStep1<Record1<String>, String> sql = jooq
		// @formatter:off
		  .selectDistinct(T_MILEAGE.PROVIDER)
		  .from(T_MILEAGE)
		  .orderBy(T_MILEAGE.PROVIDER);
		// @formatter:on
		LOGGER.debug(sql.toString());
		List<String> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			list.add(r.get(T_MILEAGE.PROVIDER));
		}
		return list;
	}

	/**
	 * add bean to db
	 * 
	 * @param bean
	 * @return
	 * @throws DataAccessException
	 */
	public boolean add(AddBean bean) throws DataAccessException {
		BigDecimal amount = bean.getAmount() == null ? null
				: new BigDecimal(bean.getAmount()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal price = bean.getPrice() == null ? null
				: new BigDecimal(bean.getPrice()).setScale(2, RoundingMode.HALF_UP);
		Timestamp buydate = bean.getBuydate() == null ? null : new Timestamp(bean.getBuydate().getTime());
		InsertValuesStep8<TMileageRecord, Integer, BigDecimal, String, BigDecimal, String, String, Timestamp, String> sql = jooq
		// @formatter:off
			.insertInto(T_MILEAGE,
						T_MILEAGE.MILEAGE,
						T_MILEAGE.AMOUNT,
						T_MILEAGE.FUEL,
						T_MILEAGE.PRICE,
						T_MILEAGE.PROVIDER,
						T_MILEAGE.LOCATION,
						T_MILEAGE.BUYDATE,
						T_MILEAGE.ANNOTATION)
			.values(bean.getMileage(),
					amount,
					bean.getFuel(),
					price,
					bean.getProvider(),
					bean.getLocation(),
					buydate,
					bean.getAnnotation());
		// @formatter:on
		LOGGER.debug(sql.toString());
		return sql.execute() > 0;
	}

	/**
	 * get dataset with maximum pk
	 * 
	 * @return String
	 */
	public String getLastDataset() {
		SelectWithTiesStep<Record> sql = jooq
		// @formatter:off
			.select(V_MILEAGE.fields())
			.from(V_MILEAGE)
			.orderBy(V_MILEAGE.PK.desc())
			.limit(1);
		// @formatter:on
		LOGGER.debug(sql.toString());
		StringBuilder buf = new StringBuilder();
		Record r = sql.fetchOne();
		for (Field field : V_MILEAGE.fields()) {
			buf.append(field.getName()).append(": ").append(r.get(field)).append(" | ");
		}
		return buf.toString();
	}

	/**
	 * check if login is true
	 * 
	 * @param key
	 *            to be used
	 * @return true for a valid login, false otherwise
	 * @throws DataAccessException
	 */
	public boolean checkLogin(String key) throws DataAccessException {
		SelectConditionStep<Record1<Integer>> sql = jooq
		// @formatter:off
			.selectCount()
			.from(V_LOGIN)
			.where(V_LOGIN.PASSWORD.eq(key));
		// @formatter:on
		return sql.fetchOne().get(0).equals(new Integer(1));
	}

	/**
	 * load all data from view
	 * 
	 * @return list of data or empty list
	 * @throws DataAccessException
	 */
	public List<MileageViewBean> getData() throws DataAccessException {
		SelectJoinStep<Record10<Integer, Integer, BigDecimal, String, Timestamp, String, String, BigDecimal, String, BigDecimal>> sql = jooq
		// @formatter:off
			.select(V_MILEAGE.PK,
					V_MILEAGE.MILEAGE,
					V_MILEAGE.AMOUNT,
					V_MILEAGE.ANNOTATION,
					V_MILEAGE.BUYDATE,
					V_MILEAGE.FUEL,
					V_MILEAGE.LOCATION,
					V_MILEAGE.PRICE,
					V_MILEAGE.PROVIDER,
					V_MILEAGE.EURO_2fL)
			.from(V_MILEAGE);
		// @formatter:on
		LOGGER.debug(sql.toString());
		List<MileageViewBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			MileageViewBean bean = new MileageViewBean();
			bean.setPk(r.get(V_MILEAGE.PK));
			bean.setMileage(r.get(V_MILEAGE.MILEAGE));
			bean.setAmount(r.get(V_MILEAGE.AMOUNT));
			bean.setAnnotation(r.get(V_MILEAGE.ANNOTATION));
			bean.setBuydate(r.get(V_MILEAGE.BUYDATE));
			bean.setFuel(r.get(V_MILEAGE.FUEL));
			bean.setLocation(r.get(V_MILEAGE.LOCATION));
			bean.setPrice(r.get(V_MILEAGE.PRICE));
			bean.setProvider(r.get(V_MILEAGE.PROVIDER));
			bean.setEuroproliter(r.get(V_MILEAGE.EURO_2fL));
			list.add(bean);
		}
		return list;
	}

	/**
	 * update bean in db
	 * 
	 * @param bean
	 * @throws DataAccessException
	 */
	public boolean update(MileageViewBean bean) throws DataAccessException {
		BigDecimal amount = bean.getAmount() == null ? null : bean.getAmount().setScale(2, RoundingMode.HALF_UP);
		BigDecimal price = bean.getPrice() == null ? null : bean.getPrice().setScale(2, RoundingMode.HALF_UP);
		Timestamp buydate = bean.getBuydate() == null ? null : new Timestamp(bean.getBuydate().getTime());
		UpdateConditionStep<TMileageRecord> sql = jooq
		// @formatter:off
			.update(T_MILEAGE)
			.set(T_MILEAGE.MILEAGE, bean.getMileage())
			.set(T_MILEAGE.AMOUNT,amount)
		    .set(T_MILEAGE.FUEL,bean.getFuel())
		    .set(T_MILEAGE.PRICE,price)
		    .set(T_MILEAGE.PROVIDER,bean.getProvider())
		    .set(T_MILEAGE.LOCATION,bean.getLocation())
		    .set(T_MILEAGE.BUYDATE,buydate)
		    .set(T_MILEAGE.ANNOTATION,bean.getAnnotation())
			.where(T_MILEAGE.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug(sql.toString());
		return sql.execute() > 0;
	}
}
