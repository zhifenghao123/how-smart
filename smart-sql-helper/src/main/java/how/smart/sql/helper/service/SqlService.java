package how.smart.sql.helper.service;

import how.smart.sql.helper.vo.SqlExecuteRespVo;

/**
 * SqlService class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
public interface SqlService {
    /**
     * executeSql
     *
     *
     * @param databaseType
     * @param host host
 * @param port port
 * @param userName userName
 * @param password password
 * @param database database
 * @param sql sql
     * @return:
     * @author: haozhifeng
     */
    SqlExecuteRespVo executeSql(String databaseType, String host, String port, String userName, String password, String database, String sql);
}
