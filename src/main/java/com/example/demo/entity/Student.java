package com.example.demo.entity;

import java.io.Serializable;

/**
* -实体
*
* @author liao
* @version v1.0
* @date 2020-04-13
*/
public class Student implements Serializable {
    
    /**   */
    private String sId;

    /**   */
    private String sName;

    /**   */
    private String sBirth;

    /**   */
    private String sSex;

    

    
    public void setsId(String sId){
           this.sId=sId;
    }
    public void setsName(String sName){
           this.sName=sName;
    }
    public void setsBirth(String sBirth){
           this.sBirth=sBirth;
    }
    public void setsSex(String sSex){
           this.sSex=sSex;
    }
        

    
    public String getsId(){
          return this.sId;
    }
    public String getsName(){
          return this.sName;
    }
    public String getsBirth(){
          return this.sBirth;
    }
    public String getsSex(){
          return this.sSex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sBirth='" + sBirth + '\'' +
                ", sSex='" + sSex + '\'' +
                '}';
    }
}
