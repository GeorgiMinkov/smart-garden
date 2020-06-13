/*
 * This file is generated by jOOQ.
 */
package bg.unisofia.fmi.robotcourse.tables;


import bg.unisofia.fmi.robotcourse.Keys;
import bg.unisofia.fmi.robotcourse.WeatherDispatcher;
import bg.unisofia.fmi.robotcourse.tables.records.ConfigurationRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Configuration extends TableImpl<ConfigurationRecord> {

    private static final long serialVersionUID = -154382206;

    /**
     * The reference instance of <code>weather-dispatcher.CONFIGURATION</code>
     */
    public static final Configuration CONFIGURATION = new Configuration();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ConfigurationRecord> getRecordType() {
        return ConfigurationRecord.class;
    }

    /**
     * The column <code>weather-dispatcher.CONFIGURATION.ID</code>.
     */
    public final TableField<ConfigurationRecord, Integer> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>weather-dispatcher.CONFIGURATION.PARAM_NAME</code>.
     */
    public final TableField<ConfigurationRecord, String> PARAM_NAME = createField(DSL.name("PARAM_NAME"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>weather-dispatcher.CONFIGURATION.PARAM_VALUE</code>.
     */
    public final TableField<ConfigurationRecord, String> PARAM_VALUE = createField(DSL.name("PARAM_VALUE"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * Create a <code>weather-dispatcher.CONFIGURATION</code> table reference
     */
    public Configuration() {
        this(DSL.name("CONFIGURATION"), null);
    }

    /**
     * Create an aliased <code>weather-dispatcher.CONFIGURATION</code> table reference
     */
    public Configuration(String alias) {
        this(DSL.name(alias), CONFIGURATION);
    }

    /**
     * Create an aliased <code>weather-dispatcher.CONFIGURATION</code> table reference
     */
    public Configuration(Name alias) {
        this(alias, CONFIGURATION);
    }

    private Configuration(Name alias, Table<ConfigurationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Configuration(Name alias, Table<ConfigurationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Configuration(Table<O> child, ForeignKey<O, ConfigurationRecord> key) {
        super(child, key, CONFIGURATION);
    }

    @Override
    public Schema getSchema() {
        return WeatherDispatcher.WEATHER_DISPATCHER;
    }

    @Override
    public Identity<ConfigurationRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CONFIGURATION;
    }

    @Override
    public UniqueKey<ConfigurationRecord> getPrimaryKey() {
        return Keys.CONFIGURATION_PK;
    }

    @Override
    public List<UniqueKey<ConfigurationRecord>> getKeys() {
        return Arrays.<UniqueKey<ConfigurationRecord>>asList(Keys.CONFIGURATION_PK);
    }

    @Override
    public Configuration as(String alias) {
        return new Configuration(DSL.name(alias), this);
    }

    @Override
    public Configuration as(Name alias) {
        return new Configuration(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Configuration rename(String name) {
        return new Configuration(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Configuration rename(Name name) {
        return new Configuration(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
