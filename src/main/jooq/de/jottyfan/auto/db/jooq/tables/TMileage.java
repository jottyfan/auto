/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.auto.db.jooq.tables;


import de.jottyfan.auto.db.jooq.Ddgj1773;
import de.jottyfan.auto.db.jooq.Keys;
import de.jottyfan.auto.db.jooq.enums.EnumFuel;
import de.jottyfan.auto.db.jooq.enums.EnumProvider;
import de.jottyfan.auto.db.jooq.tables.records.TMileageRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TMileage extends TableImpl<TMileageRecord> {

    private static final long serialVersionUID = -105913910;

    /**
     * The reference instance of <code>ddgj1773.t_mileage</code>
     */
    public static final TMileage T_MILEAGE = new TMileage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TMileageRecord> getRecordType() {
        return TMileageRecord.class;
    }

    /**
     * The column <code>ddgj1773.t_mileage.pk</code>.
     */
    public final TableField<TMileageRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('ddgj1773.t_mileage_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>ddgj1773.t_mileage.mileage</code>.
     */
    public final TableField<TMileageRecord, Integer> MILEAGE = createField("mileage", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ddgj1773.t_mileage.fuel</code>.
     */
    public final TableField<TMileageRecord, EnumFuel> FUEL = createField("fuel", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.auto.db.jooq.enums.EnumFuel.class), this, "");

    /**
     * The column <code>ddgj1773.t_mileage.location</code>.
     */
    public final TableField<TMileageRecord, String> LOCATION = createField("location", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>ddgj1773.t_mileage.price</code>. in €
     */
    public final TableField<TMileageRecord, BigDecimal> PRICE = createField("price", org.jooq.impl.SQLDataType.NUMERIC(5, 2), this, "in €");

    /**
     * The column <code>ddgj1773.t_mileage.amount</code>. in l
     */
    public final TableField<TMileageRecord, BigDecimal> AMOUNT = createField("amount", org.jooq.impl.SQLDataType.NUMERIC(5, 2), this, "in l");

    /**
     * The column <code>ddgj1773.t_mileage.provider</code>.
     */
    public final TableField<TMileageRecord, EnumProvider> PROVIDER = createField("provider", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.auto.db.jooq.enums.EnumProvider.class), this, "");

    /**
     * The column <code>ddgj1773.t_mileage.buydate</code>.
     */
    public final TableField<TMileageRecord, Timestamp> BUYDATE = createField("buydate", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>ddgj1773.t_mileage.annotation</code>.
     */
    public final TableField<TMileageRecord, String> ANNOTATION = createField("annotation", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>ddgj1773.t_mileage</code> table reference
     */
    public TMileage() {
        this(DSL.name("t_mileage"), null);
    }

    /**
     * Create an aliased <code>ddgj1773.t_mileage</code> table reference
     */
    public TMileage(String alias) {
        this(DSL.name(alias), T_MILEAGE);
    }

    /**
     * Create an aliased <code>ddgj1773.t_mileage</code> table reference
     */
    public TMileage(Name alias) {
        this(alias, T_MILEAGE);
    }

    private TMileage(Name alias, Table<TMileageRecord> aliased) {
        this(alias, aliased, null);
    }

    private TMileage(Name alias, Table<TMileageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Ddgj1773.DDGJ1773;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TMileageRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_MILEAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TMileage as(String alias) {
        return new TMileage(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TMileage as(Name alias) {
        return new TMileage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TMileage rename(String name) {
        return new TMileage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TMileage rename(Name name) {
        return new TMileage(name, null);
    }
}
