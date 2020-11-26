package com.github.zrff.backend.util.result;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultMapper {
    <T> T map(ResultSet rs) throws SQLException;
}
