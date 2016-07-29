package ua.rud.testingsystem.dao.jdbc;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

abstract class AbstractJdbc {
    final DataSource dataSource;
    final Logger logger;

    AbstractJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        this.logger = Logger.getLogger(this.getClass());
        this.logger.setLevel(Level.INFO);
    }
}
