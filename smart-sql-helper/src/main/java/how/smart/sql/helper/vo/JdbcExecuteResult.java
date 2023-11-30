package how.smart.sql.helper.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * JdbcExecuteResult class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
@Data
public class JdbcExecuteResult {
    private List<JdbcExecuteResultColumnInfo> columnMetaData;
    private List<Map<String, Object>> data;
}
