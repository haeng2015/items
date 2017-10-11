package com.hibernate.bbs_hql;

import java.math.BigDecimal;
import java.util.Date;


/**
 * AbstractBbsuser entity provides the base persistence definition of the Bbsuser entity. @author MyEclipse Persistence Tools
 */

public class Bbsuser  implements java.io.Serializable {

     private BigDecimal usid;
     private String uname;
     private String upassword;
     private String uemail;
     private Date ubirthday;
     private String usex;
     private String uclass;
     private String ustatement;
     private Date uregdate;
     private String ustate;
     private BigDecimal upoint;


    // Constructors

    /** default constructor */
    public Bbsuser() {
    }

    
    /** full constructor */
    public Bbsuser(String uname, String upassword, String uemail, Date ubirthday, String usex, String uclass, String ustatement, Date uregdate, String ustate, BigDecimal upoint) {
        this.uname = uname;
        this.upassword = upassword;
        this.uemail = uemail;
        this.ubirthday = ubirthday;
        this.usex = usex;
        this.uclass = uclass;
        this.ustatement = ustatement;
        this.uregdate = uregdate;
        this.ustate = ustate;
        this.upoint = upoint;
    }


    public BigDecimal getUsid() {
        return this.usid;
    }
    
    public void setUsid(BigDecimal usid) {
        this.usid = usid;
    }

    public String getUname() {
        return this.uname;
    }
    
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return this.upassword;
    }
    
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUemail() {
        return this.uemail;
    }
    
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public Date getUbirthday() {
        return this.ubirthday;
    }
    
    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUsex() {
        return this.usex;
    }
    
    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUclass() {
        return this.uclass;
    }
    
    public void setUclass(String uclass) {
        this.uclass = uclass;
    }

    public String getUstatement() {
        return this.ustatement;
    }
    
    public void setUstatement(String ustatement) {
        this.ustatement = ustatement;
    }

    public Date getUregdate() {
        return this.uregdate;
    }
    
    public void setUregdate(Date uregdate) {
        this.uregdate = uregdate;
    }

    public String getUstate() {
        return this.ustate;
    }
    
    public void setUstate(String ustate) {
        this.ustate = ustate;
    }

    public BigDecimal getUpoint() {
        return this.upoint;
    }
    
    public void setUpoint(BigDecimal upoint) {
        this.upoint = upoint;
    }

    
}