package com.hibernate.bbs_hql;

import java.math.BigDecimal;


/**
 * AbstractBbstopic entity provides the base persistence definition of the Bbstopic entity. @author MyEclipse Persistence Tools
 */

public class Bbstopic  implements java.io.Serializable {

     private BigDecimal tid;
     private Bbsuser bbsuser;
     private Bbssection bbssection;
     private BigDecimal treplycount;
     private String temotion;
     private String ttopic;
     private String tcontents;
     private String ttime;
     private BigDecimal tclickcount;
     private String tflag;
     private String tlastclickt;

    /** default constructor */
    public Bbstopic() {
    }

    
    /** full constructor */
    public Bbstopic(Bbsuser bbsuser, Bbssection bbssection, BigDecimal treplycount, String temotion, String ttopic, String tcontents, String ttime, BigDecimal tclickcount, String tflag, String tlastclickt) {
        this.bbsuser = bbsuser;
        this.bbssection = bbssection;
        this.treplycount = treplycount;
        this.temotion = temotion;
        this.ttopic = ttopic;
        this.tcontents = tcontents;
        this.ttime = ttime;
        this.tclickcount = tclickcount;
        this.tflag = tflag;
        this.tlastclickt = tlastclickt;
    }

   
    // Property accessors

    public BigDecimal getTid() {
        return this.tid;
    }
    
    public void setTid(BigDecimal tid) {
        this.tid = tid;
    }

    public Bbsuser getBbsuser() {
        return this.bbsuser;
    }
    
    public void setBbsuser(Bbsuser bbsuser) {
        this.bbsuser = bbsuser;
    }

    public Bbssection getBbssection() {
        return this.bbssection;
    }
    
    public void setBbssection(Bbssection bbssection) {
        this.bbssection = bbssection;
    }

    public BigDecimal getTreplycount() {
        return this.treplycount;
    }
    
    public void setTreplycount(BigDecimal treplycount) {
        this.treplycount = treplycount;
    }

    public String getTemotion() {
        return this.temotion;
    }
    
    public void setTemotion(String temotion) {
        this.temotion = temotion;
    }

    public String getTtopic() {
        return this.ttopic;
    }
    
    public void setTtopic(String ttopic) {
        this.ttopic = ttopic;
    }

    public String getTcontents() {
        return this.tcontents;
    }
    
    public void setTcontents(String tcontents) {
        this.tcontents = tcontents;
    }

    public String getTtime() {
        return this.ttime;
    }
    
    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public BigDecimal getTclickcount() {
        return this.tclickcount;
    }
    
    public void setTclickcount(BigDecimal tclickcount) {
        this.tclickcount = tclickcount;
    }

    public String getTflag() {
        return this.tflag;
    }
    
    public void setTflag(String tflag) {
        this.tflag = tflag;
    }

    public String getTlastclickt() {
        return this.tlastclickt;
    }
    
    public void setTlastclickt(String tlastclickt) {
        this.tlastclickt = tlastclickt;
    }

}