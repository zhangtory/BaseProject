package ${package.Mapper};

import org.apache.ibatis.annotations.Mapper;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
* @Author: ${author}
* @Date: ${date}
* @Description: ${table.comment!} Mapper 接口
*/
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}

