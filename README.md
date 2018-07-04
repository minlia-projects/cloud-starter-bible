# 数据访问自动化  

同时启用Mybatis与Data Jpa

目录结构：  

entity: 实体模型文件

mybatis相关：  
dao: Mapper接口文件  
resources/mapper/: Mapper Xml文件  

data jpa相关：
repository: Spring Data Jpa Repository接口文件

注意：data 模块已集成了mybatis 与 spring data jpa，所以不要改变以上包名约定


特点： 

可以手工编写mybatis mapper xml 文件
已集成2种DAO查询数据库的方式并返回已分页后的数据，返回结构如下：

```
{
    "payload": {
        "page": 1,
        "size": 10,
        "totalPages": 2,
        "totalElements": 11,
        "items": [
            {
                "id": 412,
                "createdDate": "2018-07-02 20:28:08",
                "lastModifiedDate": "2018-07-02 20:28:08",
                "code": "njKQSrvAbLnM",
                "label": "l5Bzi7V3E1KMEjL1H1JK"
            }
        ]
    },
    "timestamp": 1530619989292,
    "requestId": "26545113664589824",
    "code": 1,
    "status": 1,
    "message": "OK"
}
```


模型层使用示例：
```
@Data
@Entity
@Table(name = TABLE_NAME)
@TableName(TABLE_NAME)
public class Role extends AbstractEntity {

    private static final String TABLE_NAME="system_role";

    /**
     * 忽略掉集合类型
     */
    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "权限集合")
    private List<Permission> permissions;
}

```



与单独的JPA使用时不同的是需要独立出中间对象  
新增一个Mapping后缀，定义为实体中间表

```
@Data
@Entity
@Table(name = TABLE_NAME)
@TableName(TABLE_NAME)
public class RolesPermissionMapping extends AbstractEntity {

    private static final String TABLE_NAME="roles_permission_map";

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    private String permissionId;
}
```


结合mybatis与mybatisplus框架提供的强大功能，提供数据访问自动化的能力

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.minlia.cloud.starter/cloud-starter-data/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.minlia.cloud.starter/cloud-starter-data/) 
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt) 
[![Build Status](https://travis-ci.org/minlia-projects/cloud-starter-data.svg?branch=master)](https://travis-ci.org/minlia-projects/cloud-starter-data)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/minlia-projects/cloud-starter-data.svg?columns=all)](https://waffle.io/minlia-projects/cloud-starter-data)

## 集成到自已的项目时添加依赖项    

```pom
<dependency>
  <groupId>com.minlia.cloud.starter</groupId>
  <artifactId>cloud-starter-data</artifactId>
  <version>2.0.0.RELEASE</version>
</dependency>
```
## 后端Endpoint层代码

```
  @PostMapping(value = "/api/status/post")
  @ApiOperation(value = "状态", notes = "测试提交", httpMethod = "POST")
  public StatefulBody postStatus(@RequestBody WithIdBody body) {
    Assertion.is(true, ApiCode.ACCOUNT_DISABLED);
    return SuccessResponseBody.builder().build();
  }
```

## 返回报文示例

```
{
  "payload":{
    "id":33333333333,
    "balance":228866.00
  }
  "code": 1,//业务返回码
  "message": "OK",//业务返回释义
  "requestId": "cZCu5aAftUn2ivn2rcKb2YUhb6N7ijP420180402212405106502",//当前请求编号
  "status": 200,//请求的http状态码
  "timestamp": 1522675445311//当前请求时间戳
}
```

