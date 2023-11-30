package how.smart.sql.helper.vo;

import lombok.Data;

/**
 * JdbcExecuteResultColumnInfo class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
@Data
public class JdbcExecuteResultColumnInfo {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列数据类型
     */
    private String columnDataType;
}
