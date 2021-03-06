/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.auto.db.jooq.tables;


import de.jottyfan.auto.db.jooq.Ddgj1773;
import de.jottyfan.auto.db.jooq.tables.records.VLoginRecord;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class VLogin extends TableImpl<VLoginRecord> {

    private static final long serialVersionUID = 769804442;

    /**
     * The reference instance of <code>ddgj1773.v_login</code>
     */
    public static final VLogin V_LOGIN = new VLogin();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VLoginRecord> getRecordType() {
        return VLoginRecord.class;
    }

    /**
     * The column <code>ddgj1773.v_login.password</code>.
     */
    public final TableField<VLoginRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>ddgj1773.v_login</code> table reference
     */
    public VLogin() {
        this(DSL.name("v_login"), null);
    }

    /**
     * Create an aliased <code>ddgj1773.v_login</code> table reference
     */
    public VLogin(String alias) {
        this(DSL.name(alias), V_LOGIN);
    }

    /**
     * Create an aliased <code>ddgj1773.v_login</code> table reference
     */
    public VLogin(Name alias) {
        this(alias, V_LOGIN);
    }

    private VLogin(Name alias, Table<VLoginRecord> aliased) {
        this(alias, aliased, null);
    }

    private VLogin(Name alias, Table<VLoginRecord> aliased, Field<?>[] parameters) {
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
    public VLogin as(String alias) {
        return new VLogin(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VLogin as(Name alias) {
        return new VLogin(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VLogin rename(String name) {
        return new VLogin(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VLogin rename(Name name) {
        return new VLogin(name, null);
    }
}
