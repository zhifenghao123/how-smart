package how.smart.sql.helper.controller;

import how.smart.sql.helper.service.SqlService;
import how.smart.sql.helper.vo.BaseResponse;
import how.smart.sql.helper.vo.SqlExecuteReqVo;
import how.smart.sql.helper.vo.SqlExecuteRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SqlProcessController class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
@Controller
@RequestMapping("sql/manage")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @ResponseBody
    @RequestMapping(value = "executeSql")
    BaseResponse executeSql(@RequestBody SqlExecuteReqVo reqVo){
        SqlExecuteRespVo respVo = sqlService.executeSql(reqVo.getDatabaseType(), reqVo.getHost(),
                reqVo.getPort(), reqVo.getUserName(), reqVo.getPassword(), reqVo.getDatabase(), reqVo.getSql());
        return BaseResponse.newSuccResponse(respVo);
    }
}
