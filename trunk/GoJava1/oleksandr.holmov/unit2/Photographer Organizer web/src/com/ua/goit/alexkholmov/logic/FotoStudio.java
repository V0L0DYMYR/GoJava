/**
 * 
 */
package com.ua.goit.alexkholmov.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SASH
 *
 */
public class FotoStudio {
    public final static String TYPE = "S";
    
    private int studioId;
    private String name;
    private String address;
    private String phone;
    private String additionalInfo;
    private ReserveList reserveList;

    public FotoStudio() {

    }
    
    /**
     * @return the studioId
     */
    public int getStudioId() {
        return studioId;
    }

    /**
     * @param studioId the studioId to set
     */
    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the additionalInfo
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * @param additionalInfo the additionalInfo to set
     */
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * @return the reserveList
     */
    public ReserveList getReserveList() {
        return reserveList;
    }

    /**
     * @param reserveList the reserveList to set
     */
    public void setReserveList(ReserveList reserveList) {
        this.reserveList = reserveList;
    }
    
}
