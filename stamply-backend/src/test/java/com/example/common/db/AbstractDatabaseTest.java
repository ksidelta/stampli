package com.example.common.db;

import com.example.infrastructure.db.DbTestUtil;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public abstract class AbstractDatabaseTest {
    static final Logger logger = Logger.getLogger(AbstractDatabaseTest.class.getCanonicalName());

    @Autowired
    protected DbTestUtil dbTestUtil;

    @AfterEach
    protected void removeAll() {
        dbTestUtil.removeAll();
    }
}
