package how.smart.sql.helper.util;

import how.smart.sql.helper.enums.DataBaseTypeEnum;
import how.smart.sql.helper.vo.JdbcExecuteResult;
import how.smart.sql.helper.vo.JdbcExecuteResultColumnInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JdbcUtil class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
public class JdbcUtil {
    private static final String DB_URL = "jdbc:%s://%s:%d/%s";

    public static JdbcExecuteResult executeQuery(DataBaseTypeEnum dataBaseType, String ip, int port, String userName, String password, String dbName, String sql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        JdbcExecuteResult jdbcExecuteResult = new JdbcExecuteResult();

        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            // 注册JDBC驱动
            Class.forName(dataBaseType.getJdbcDriverName());

            String url = String.format(DB_URL, dataBaseType.getJdbcSubProtocol(), ip, port, dbName);
            // 打开链接
            System.out.println("连接数据库...");
            System.out.printf("DriverManager.getConnection. url:%s, userName:%s, password:%s%n", url, userName, password);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("数据库连接成功！");

            // 执行查询
            System.out.println("执行SQL查询...");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // 处理结果集
            while (rs.next()) {
                Map<String, Object> rowData = new LinkedHashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                if (rs.isFirst()) {
                    // 利用第一条记录分析列信息
                    List<JdbcExecuteResultColumnInfo> columnMetaData = new ArrayList<>();

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);

                        if (rs.isFirst()) {
                            // 处理第一条记录的逻辑
                            String columnClassName = metaData.getColumnClassName(i);

                            JdbcExecuteResultColumnInfo jdbcExecuteResultColumnInfo = new JdbcExecuteResultColumnInfo();
                            jdbcExecuteResultColumnInfo.setColumnName(columnName);
                            jdbcExecuteResultColumnInfo.setColumnDataType(columnClassName);

                            columnMetaData.add(jdbcExecuteResultColumnInfo);
                        }

                        rowData.put(columnName, columnValue);
                    }

                    jdbcExecuteResult.setColumnMetaData(columnMetaData);
                } else {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        rowData.put(columnName, columnValue);
                    }
                }
                resultList.add(rowData);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 完成后关闭资源
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        jdbcExecuteResult.setData(resultList);
        return jdbcExecuteResult;
    }
}
