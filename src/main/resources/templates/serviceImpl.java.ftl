package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
*  @Author: ${author}
*  @Date: ${date}
*  @Description: ${table.comment!} 服务实现类
*/
@Service
@Slf4j
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

}
