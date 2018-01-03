/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.auto.db.jooq;


import de.jottyfan.auto.db.jooq.tables.TMileage;
import de.jottyfan.auto.db.jooq.tables.records.TMileageRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>ddgj1773</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TMileageRecord, Integer> IDENTITY_T_MILEAGE = Identities0.IDENTITY_T_MILEAGE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<TMileageRecord, Integer> IDENTITY_T_MILEAGE = createIdentity(TMileage.T_MILEAGE, TMileage.T_MILEAGE.PK);
    }
}
