package ${package.Controller};

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
*  @Author: ${author}
*  @Date: ${date}
*  @Description: ${table.comment!} 控制层
*/
@RestController
@RequestMapping("${table.entityPath}")
@Api(tags = "${table.comment!}")
public class ${table.controllerName} {


}

