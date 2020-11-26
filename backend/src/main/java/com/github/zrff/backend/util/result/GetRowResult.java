package com.github.zrff.backend.util.result;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRowResult {
    private ResultSet rs;

    public GetRowResult(ResultSet rs){
        this.rs = rs;
    }

    public <T> T map(ResultMapper rm) throws SQLException {
        if(rs != null && rs.next()){
            return rm.map(rs);
        }
        return null;
    }
}
