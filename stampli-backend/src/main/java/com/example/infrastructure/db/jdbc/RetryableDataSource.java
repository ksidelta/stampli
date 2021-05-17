package com.example.infrastructure.db.jdbc;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class RetryableDataSource implements DataSource {
    protected final DataSource parent;

    public RetryableDataSource(DataSource parent) {
        this.parent = parent;
    }

    @Override
    @Retryable(include = SQLException.class, maxAttempts = 60, backoff = @Backoff(1000))
    public Connection getConnection() throws SQLException {
        return parent.getConnection();
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return parent.getConnection(s, s1);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return parent.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {
        parent.setLogWriter(printWriter);
    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {
        parent.setLoginTimeout(i);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return parent.getLoginTimeout();
    }

    @Override
    public ConnectionBuilder createConnectionBuilder() throws SQLException {
        return parent.createConnectionBuilder();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return parent.getParentLogger();
    }

    @Override
    public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
        return parent.createShardingKeyBuilder();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return parent.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return parent.isWrapperFor(aClass);
    }
}
