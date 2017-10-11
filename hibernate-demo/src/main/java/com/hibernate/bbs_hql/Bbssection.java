package com.hibernate.bbs_hql;

import java.math.BigDecimal;


/**
 * AbstractBbssection entity provides the base persistence definition of the Bbssection entity. @author MyEclipse Persistence Tools
 */

public class Bbssection  implements java.io.Serializable {


     private BigDecimal sid;
     private Bbsuser bbsuser;
     private String sname;
     private String sstatement;
     private BigDecimal sclickcount;
     private BigDecimal stopiccount;


    // Constructors

    /** default constructor */
    public Bbssection() {
    }

    
    /** full constructor */
    public Bbssection(Bbsuser bbsuser, String sname, String sstatement, BigDecimal sclickcount, BigDecimal stopiccount) {
        this.bbsuser = bbsuser;
        this.sname = sname;
        this.sstatement = sstatement;
        this.sclickcount = sclickcount;
        this.stopiccount = stopiccount;
    }

   
    // Property accessors

    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }

    public Bbsuser getBbsuser() {
        return this.bbsuser;
    }
    
    public void setBbsuser(Bbsuser bbsuser) {
        this.bbsuser = bbsuser;
    }

    public String getSname() {
        return this.sname;
    }
    
    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSstatement() {
        return this.sstatement;
    }
    
    public void setSstatement(String sstatement) {
        this.sstatement = sstatement;
    }

    public BigDecimal getSclickcount() {
        return this.sclickcount;
    }
    
    public void setSclickcount(BigDecimal sclickcount) {
        this.sclickcount = sclickcount;
    }

    public BigDecimal getStopiccount() {
        return this.stopiccount;
    }
    
    public void setStopiccount(BigDecimal stopiccount) {
        this.stopiccount = stopiccount;
    }


}