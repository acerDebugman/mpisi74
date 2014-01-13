package entity;

import java.io.Serializable;

public class USERINFO implements Serializable{
    private static final long serialVersionUID = 1L;
    private int USERID;
    private String BADGENUMBER;
    private String SSN;
    private String NAME;
    private String GENDER;
    private String TITLE;
    private String PAGER;
    private String BIRTHDAY;
    private String HIREDDAY;
    private String STREET;
    private String CITY;
    private String STATE;
    private String ZIP;
    private String OPHONE;
    private String FPHONE;
    private int VERIFICATIONMETHOD;
    private int DEFAULTDEPTID;
    private int SECURITYFLAGS;
    private int ATT;
    private int INLATE;
    private int OUTEARLY;
    private int OVERTIME;
    private int SEP;
    private int HOLIDAY;
    private String MINZU;
    private String PASSWORD;
    private int LUNCHDURATION;
    private String MVERIFYPASS;
    private int PRIVILEGE;
    private int INHERITDEPTSCH;
    private int INHERITDEPTSCHCLASS;
    private int AUTOSCHPLAN;
    private int MINAUTOSCHINTERVAL;
    private int REGISTEROT;
    private int INHERITDEPTRULE;
    private int EMPRIVILEGE;
    private String CARDNO;

    /**
     * @return the _USERID
     */
    public int getUSERID() {
        return USERID;
    }
    /**
     * @param _USERID the _USERID to set
     */
    public void setUSERID(int _USERID) {
        USERID = _USERID;
    }

    /**
     * @return the _BADGENUMBER
     */
    public String getBADGENUMBER() {
        return BADGENUMBER;
    }
    /**
     * @param _BADGENUMBER the _BADGENUMBER to set
     */
    public void setBADGENUMBER(String _BADGENUMBER) {
        BADGENUMBER = _BADGENUMBER;
    }

    /**
     * @return the _SSN
     */
    public String getSSN() {
        return SSN;
    }
    /**
     * @param _SSN the _SSN to set
     */
    public void setSSN(String _SSN) {
        SSN = _SSN;
    }

    /**
     * @return the _NAME
     */
    public String getNAME() {
        return NAME;
    }
    /**
     * @param _NAME the _NAME to set
     */
    public void setNAME(String _NAME) {
        NAME = _NAME;
    }

    /**
     * @return the _GENDER
     */
    public String getGENDER() {
        return GENDER;
    }
    /**
     * @param _GENDER the _GENDER to set
     */
    public void setGENDER(String _GENDER) {
        GENDER = _GENDER;
    }

    /**
     * @return the _TITLE
     */
    public String getTITLE() {
        return TITLE;
    }
    /**
     * @param _TITLE the _TITLE to set
     */
    public void setTITLE(String _TITLE) {
        TITLE = _TITLE;
    }

    /**
     * @return the _PAGER
     */
    public String getPAGER() {
        return PAGER;
    }
    /**
     * @param _PAGER the _PAGER to set
     */
    public void setPAGER(String _PAGER) {
        PAGER = _PAGER;
    }

    /**
     * @return the _BIRTHDAY
     */
    public String getBIRTHDAY() {
        return BIRTHDAY;
    }
    /**
     * @param _BIRTHDAY the _BIRTHDAY to set
     */
    public void setBIRTHDAY(String _BIRTHDAY) {
        BIRTHDAY = _BIRTHDAY;
    }

    /**
     * @return the _HIREDDAY
     */
    public String getHIREDDAY() {
        return HIREDDAY;
    }
    /**
     * @param _HIREDDAY the _HIREDDAY to set
     */
    public void setHIREDDAY(String _HIREDDAY) {
        HIREDDAY = _HIREDDAY;
    }

    /**
     * @return the _STREET
     */
    public String getSTREET() {
        return STREET;
    }
    /**
     * @param _STREET the _STREET to set
     */
    public void setSTREET(String _STREET) {
        STREET = _STREET;
    }

    /**
     * @return the _CITY
     */
    public String getCITY() {
        return CITY;
    }
    /**
     * @param _CITY the _CITY to set
     */
    public void setCITY(String _CITY) {
        CITY = _CITY;
    }

    /**
     * @return the _STATE
     */
    public String getSTATE() {
        return STATE;
    }
    /**
     * @param _STATE the _STATE to set
     */
    public void setSTATE(String _STATE) {
        STATE = _STATE;
    }

    /**
     * @return the _ZIP
     */
    public String getZIP() {
        return ZIP;
    }
    /**
     * @param _ZIP the _ZIP to set
     */
    public void setZIP(String _ZIP) {
        ZIP = _ZIP;
    }

    /**
     * @return the _OPHONE
     */
    public String getOPHONE() {
        return OPHONE;
    }
    /**
     * @param _OPHONE the _OPHONE to set
     */
    public void setOPHONE(String _OPHONE) {
        OPHONE = _OPHONE;
    }

    /**
     * @return the _FPHONE
     */
    public String getFPHONE() {
        return FPHONE;
    }
    /**
     * @param _FPHONE the _FPHONE to set
     */
    public void setFPHONE(String _FPHONE) {
        FPHONE = _FPHONE;
    }

    /**
     * @return the _VERIFICATIONMETHOD
     */
    public int getVERIFICATIONMETHOD() {
        return VERIFICATIONMETHOD;
    }
    /**
     * @param _VERIFICATIONMETHOD the _VERIFICATIONMETHOD to set
     */
    public void setVERIFICATIONMETHOD(int _VERIFICATIONMETHOD) {
        VERIFICATIONMETHOD = _VERIFICATIONMETHOD;
    }

    /**
     * @return the _DEFAULTDEPTID
     */
    public int getDEFAULTDEPTID() {
        return DEFAULTDEPTID;
    }
    /**
     * @param _DEFAULTDEPTID the _DEFAULTDEPTID to set
     */
    public void setDEFAULTDEPTID(int _DEFAULTDEPTID) {
        DEFAULTDEPTID = _DEFAULTDEPTID;
    }

    /**
     * @return the _SECURITYFLAGS
     */
    public int getSECURITYFLAGS() {
        return SECURITYFLAGS;
    }
    /**
     * @param _SECURITYFLAGS the _SECURITYFLAGS to set
     */
    public void setSECURITYFLAGS(int _SECURITYFLAGS) {
        SECURITYFLAGS = _SECURITYFLAGS;
    }

    /**
     * @return the _ATT
     */
    public int getATT() {
        return ATT;
    }
    /**
     * @param _ATT the _ATT to set
     */
    public void setATT(int _ATT) {
        ATT = _ATT;
    }

    /**
     * @return the _INLATE
     */
    public int getINLATE() {
        return INLATE;
    }
    /**
     * @param _INLATE the _INLATE to set
     */
    public void setINLATE(int _INLATE) {
        INLATE = _INLATE;
    }

    /**
     * @return the _OUTEARLY
     */
    public int getOUTEARLY() {
        return OUTEARLY;
    }
    /**
     * @param _OUTEARLY the _OUTEARLY to set
     */
    public void setOUTEARLY(int _OUTEARLY) {
        OUTEARLY = _OUTEARLY;
    }

    /**
     * @return the _OVERTIME
     */
    public int getOVERTIME() {
        return OVERTIME;
    }
    /**
     * @param _OVERTIME the _OVERTIME to set
     */
    public void setOVERTIME(int _OVERTIME) {
        OVERTIME = _OVERTIME;
    }

    /**
     * @return the _SEP
     */
    public int getSEP() {
        return SEP;
    }
    /**
     * @param _SEP the _SEP to set
     */
    public void setSEP(int _SEP) {
        SEP = _SEP;
    }

    /**
     * @return the _HOLIDAY
     */
    public int getHOLIDAY() {
        return HOLIDAY;
    }
    /**
     * @param _HOLIDAY the _HOLIDAY to set
     */
    public void setHOLIDAY(int _HOLIDAY) {
        HOLIDAY = _HOLIDAY;
    }

    /**
     * @return the _MINZU
     */
    public String getMINZU() {
        return MINZU;
    }
    /**
     * @param _MINZU the _MINZU to set
     */
    public void setMINZU(String _MINZU) {
        MINZU = _MINZU;
    }

    /**
     * @return the _PASSWORD
     */
    public String getPASSWORD() {
        return PASSWORD;
    }
    /**
     * @param _PASSWORD the _PASSWORD to set
     */
    public void setPASSWORD(String _PASSWORD) {
        PASSWORD = _PASSWORD;
    }

    /**
     * @return the _LUNCHDURATION
     */
    public int getLUNCHDURATION() {
        return LUNCHDURATION;
    }
    /**
     * @param _LUNCHDURATION the _LUNCHDURATION to set
     */
    public void setLUNCHDURATION(int _LUNCHDURATION) {
        LUNCHDURATION = _LUNCHDURATION;
    }

    /**
     * @return the _MVERIFYPASS
     */
    public String getMVERIFYPASS() {
        return MVERIFYPASS;
    }
    /**
     * @param _MVERIFYPASS the _MVERIFYPASS to set
     */
    public void setMVERIFYPASS(String _MVERIFYPASS) {
        MVERIFYPASS = _MVERIFYPASS;
    }

    /**
     * @return the _PRIVILEGE
     */
    public int getPRIVILEGE() {
        return PRIVILEGE;
    }
    /**
     * @param _PRIVILEGE the _PRIVILEGE to set
     */
    public void setPRIVILEGE(int _PRIVILEGE) {
        PRIVILEGE = _PRIVILEGE;
    }

    /**
     * @return the _INHERITDEPTSCH
     */
    public int getINHERITDEPTSCH() {
        return INHERITDEPTSCH;
    }
    /**
     * @param _INHERITDEPTSCH the _INHERITDEPTSCH to set
     */
    public void setINHERITDEPTSCH(int _INHERITDEPTSCH) {
        INHERITDEPTSCH = _INHERITDEPTSCH;
    }

    /**
     * @return the _INHERITDEPTSCHCLASS
     */
    public int getINHERITDEPTSCHCLASS() {
        return INHERITDEPTSCHCLASS;
    }
    /**
     * @param _INHERITDEPTSCHCLASS the _INHERITDEPTSCHCLASS to set
     */
    public void setINHERITDEPTSCHCLASS(int _INHERITDEPTSCHCLASS) {
        INHERITDEPTSCHCLASS = _INHERITDEPTSCHCLASS;
    }

    /**
     * @return the _AUTOSCHPLAN
     */
    public int getAUTOSCHPLAN() {
        return AUTOSCHPLAN;
    }
    /**
     * @param _AUTOSCHPLAN the _AUTOSCHPLAN to set
     */
    public void setAUTOSCHPLAN(int _AUTOSCHPLAN) {
        AUTOSCHPLAN = _AUTOSCHPLAN;
    }

    /**
     * @return the _MINAUTOSCHINTERVAL
     */
    public int getMINAUTOSCHINTERVAL() {
        return MINAUTOSCHINTERVAL;
    }
    /**
     * @param _MINAUTOSCHINTERVAL the _MINAUTOSCHINTERVAL to set
     */
    public void setMINAUTOSCHINTERVAL(int _MINAUTOSCHINTERVAL) {
        MINAUTOSCHINTERVAL = _MINAUTOSCHINTERVAL;
    }

    /**
     * @return the _REGISTEROT
     */
    public int getREGISTEROT() {
        return REGISTEROT;
    }
    /**
     * @param _REGISTEROT the _REGISTEROT to set
     */
    public void setREGISTEROT(int _REGISTEROT) {
        REGISTEROT = _REGISTEROT;
    }

    /**
     * @return the _INHERITDEPTRULE
     */
    public int getINHERITDEPTRULE() {
        return INHERITDEPTRULE;
    }
    /**
     * @param _INHERITDEPTRULE the _INHERITDEPTRULE to set
     */
    public void setINHERITDEPTRULE(int _INHERITDEPTRULE) {
        INHERITDEPTRULE = _INHERITDEPTRULE;
    }

    /**
     * @return the _EMPRIVILEGE
     */
    public int getEMPRIVILEGE() {
        return EMPRIVILEGE;
    }
    /**
     * @param _EMPRIVILEGE the _EMPRIVILEGE to set
     */
    public void setEMPRIVILEGE(int _EMPRIVILEGE) {
        EMPRIVILEGE = _EMPRIVILEGE;
    }

    /**
     * @return the _CARDNO
     */
    public String getCARDNO() {
        return CARDNO;
    }
    /**
     * @param _CARDNO the _CARDNO to set
     */
    public void setCARDNO(String _CARDNO) {
        CARDNO = _CARDNO;
    }


}

