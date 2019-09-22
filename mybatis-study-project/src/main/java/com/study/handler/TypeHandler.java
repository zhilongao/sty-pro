package com.study.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author long
 * @Date 2019/4/5 11:51
 */
// mybatis中varchar映射器
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TypeHandler extends BaseTypeHandler<String> {


    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }

    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        /*System.out.println("will change varchar column");
        String columnVal = rs.getString(columnName);
        return columnVal + "类型映射器";*/
        return rs.getString(columnName);
    }

    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("will change varchar column");
        String columnVal = rs.getString(columnIndex);
        return columnVal + "类型映射器";
    }

    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("will change varchar column");
        String columnVal = cs.getString(columnIndex);
        return columnVal + "类型映射器";
    }
}
