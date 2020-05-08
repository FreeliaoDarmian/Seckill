package com.example.demo.[(${packageName})];
import lombok.Data;

import java.io.Serializable;

/**
* [(${comment})]-实体
*
* @author liao
* @version v1.0
* @date [(${createDate})]
*/
@Data
public class [(${className})] implements Serializable {
    [# th:each="column : ${columnList}"]
    /** [(${column.comment})]  */
    private [(${column.type})] [(${column.attrName})];

    [/]

    [# th:each="column : ${columnList}"]
    public void set[(${column.attrName})]([(${column.type})] [(${column.attrName})]){
           this.[(${column.attrName})]=[(${column.attrName})];
    }
        [/]

    [# th:each="column : ${columnList}"]
    public [(${column.type})] get[(${column.attrName})](){
          return this.[(${column.attrName})];
    }
    [/]


}
