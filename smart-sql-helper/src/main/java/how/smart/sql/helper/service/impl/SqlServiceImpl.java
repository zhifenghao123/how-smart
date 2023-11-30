package how.smart.sql.helper.service.impl;

import how.smart.sql.helper.enums.DataBaseTypeEnum;
import how.smart.sql.helper.service.SqlService;
import how.smart.sql.helper.util.JdbcUtil;
import how.smart.sql.helper.vo.JdbcExecuteResult;
import how.smart.sql.helper.vo.SqlExecuteRespVo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * SqlServiceImpl class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
@Service
public class SqlServiceImpl implements SqlService {
    @Override
    public SqlExecuteRespVo executeSql(String databaseType, String host, String port, String userName, String password, String database,
                                       String sql){
        DataBaseTypeEnum dataBaseTypeEnum = DataBaseTypeEnum.valueOf(databaseType);

        int intPort = Integer.valueOf(port);
        JdbcExecuteResult result = null;
        try {
            result = JdbcUtil.executeQuery(dataBaseTypeEnum, host, intPort, userName, password, database, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源，这里简化了处理，实际应用中需要根据具体情况进行适当的关闭操作。
        }

        SqlExecuteRespVo sqlExecuteRespVo = new SqlExecuteRespVo();
        sqlExecuteRespVo.setColumnMetaData(result.getColumnMetaData());
        sqlExecuteRespVo.setData(result.getData());
        return sqlExecuteRespVo;
    }


}
