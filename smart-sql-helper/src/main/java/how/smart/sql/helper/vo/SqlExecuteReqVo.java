package how.smart.sql.helper.vo;

import lombok.Data;

/**
 * SqlExecuteReqVo class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
@Data
public class SqlExecuteReqVo {
    private String databaseType;
    private String host;
    private String port;
    private String userName;
    private String password;
    private String database;

    private String sql;
}
