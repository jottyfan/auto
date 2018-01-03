package de.jottyfan.auto;

import static de.jottyfan.auto.db.jooq.Tables.T_MILEAGE;
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
import org.jooq.SQLDialect;
import org.jooq.SelectJoinStep;
import org.jooq.SelectWithTiesStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.auto.db.jooq.enums.EnumFuel;
import de.jottyfan.auto.db.jooq.enums.EnumProvider;
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
	public List<EnumFuel> getFuels() {
		List<EnumFuel> list = new ArrayList<>();
		for (EnumFuel e : EnumFuel.values()) {
			list.add(e);
		}
		return list;
	}

	/**
	 * get all providers
	 * 
	 * @return providers
	 */
	public List<EnumProvider> getProviders() {
		List<EnumProvider> list = new ArrayList<>();
		for (EnumProvider e : EnumProvider.values()) {
			list.add(e);
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
		InsertValuesStep8<TMileageRecord, Integer, BigDecimal, EnumFuel, BigDecimal, EnumProvider, String, Timestamp, String> sql = jooq
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
					bean.getFuelEnum(),
					price,
					bean.getProviderEnum(),
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

}
