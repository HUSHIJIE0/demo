package com.example.demo.elasticSearch;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author: Michael
 * @Date: Created in 10:27 2018/11/21
 * @Desciption:
 */
@Document(indexName = "testgoods",type = "goods")
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
@Data
public class GoodsInfo implements Serializable {
    private Long id;
    private String name;
    private String description;

    public GoodsInfo(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public GoodsInfo() {

    }

}
