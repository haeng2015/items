package com.hibernate.bbs_hql;

import java.math.BigDecimal;


/**
 * AbstractBbsreply entity provides the base persistence definition of the Bbsreply entity. @author MyEclipse Persistence Tools
 */

public class Bbsreply  implements java.io.Serializable {


     private BigDecimal rid;
     private Bbstopic bbstopic;
     private Bbssection bbssection;
     private Bbsuser bbsuser;
     private String remoticon;
     private String rtopic;
     private String rcontents;
     private String rtime;


    // Constructors

    /** default constructor */
    public Bbsreply() {
    }

    
    /** full constructor */
    public Bbsreply(Bbstopic bbstopic, Bbssection bbssection, Bbsuser bbsuser, String remoticon, String rtopic, String rcontents, String rtime) {
        this.bbstopic = bbstopic;
        this.bbssection = bbssection;
        this.bbsuser = bbsuser;
        this.remoticon = remoticon;
        this.rtopic = rtopic;
        this.rcontents = rcontents;
        this.rtime = rtime;
    }

   
    // Property accessors

    public BigDecimal getRid() {
        return this.rid;
    }
    
    public void setRid(BigDecimal rid) {
        this.rid = rid;
    }

    public Bbstopic getBbstopic() {
        return this.bbstopic;
    }
    
    public void setBbstopic(Bbstopic bbstopic) {
        this.bbstopic = bbstopic;
    }

    public Bbssection getBbssection() {
        return this.bbssection;
    }
    
    public void setBbssection(Bbssection bbssection) {
        this.bbssection = bbssection;
    }

    public Bbsuser getBbsuser() {
        return this.bbsuser;
    }
    
    public void setBbsuser(Bbsuser bbsuser) {
        this.bbsuser = bbsuser;
    }

    public String getRemoticon() {
        return this.remoticon;
    }
    
    public void setRemoticon(String remoticon) {
        this.remoticon = remoticon;
    }

    public String getRtopic() {
        return this.rtopic;
    }
    
    public void setRtopic(String rtopic) {
        this.rtopic = rtopic;
    }

    public String getRcontents() {
        return this.rcontents;
    }
    
    public void setRcontents(String rcontents) {
        this.rcontents = rcontents;
    }

    public String getRtime() {
        return this.rtime;
    }
    
    public void setRtime(String rtime) {
        this.rtime = rtime;
    }
   
}