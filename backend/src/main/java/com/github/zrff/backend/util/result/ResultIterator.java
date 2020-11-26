package com.github.zrff.backend.util.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultIterator {

    private ResultSet rs;

    public ResultIterator(ResultSet rs){
        this.rs = rs;
    }

    public <T> List<T> map(ResultMapper rm) throws SQLException {
        List<T> res = new ArrayList<>();

        while (rs != null && rs.next()){
            T row = rm.map(rs);
            res.add(row);
        }
        return res;
    }

}
