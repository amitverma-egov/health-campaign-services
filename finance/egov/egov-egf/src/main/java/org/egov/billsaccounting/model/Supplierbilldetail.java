/*******************************************************************************
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 * 	1) All versions of this program, verbatim or modified must carry this
 * 	   Legal Notice.
 *
 * 	2) Any misrepresentation of the origin of the material is prohibited. It
 * 	   is required that all modified versions of this material be marked in
 * 	   reasonable ways as different from the original version.
 *
 * 	3) This license does not grant any rights to any user of the program
 * 	   with regards to rights under trademark law for use of the trade names
 * 	   or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 ******************************************************************************/
package org.egov.billsaccounting.model;

// Generated Mar 6, 2008 11:33:35 AM by Hibernate Tools 3.2.0.b9

import org.egov.commons.CVoucherHeader;
import org.egov.commons.Relation;
import org.egov.model.bills.EgBillregister;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Supplierbilldetail generated by hbm2java
 */
public class Supplierbilldetail implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 6084391812735500138L;

    private Integer id;

    private CVoucherHeader voucherheader;

    private Relation relation;

    private EgBillregister egBillregister;

    private Date billdate;

    private String billnumber;

    private BigDecimal otherrecoveries = new BigDecimal(0);

    private String mrnnumber;

    private Date mrndate;

    private BigDecimal billamount = new BigDecimal(0);

    private BigDecimal passedamount = new BigDecimal(0);

    private String approvedby;

    private BigDecimal payableaccount;

    private String narration;

    private Integer worksdetailid;

    private BigDecimal tdsamount = new BigDecimal(0);

    private Boolean tdspaidtoit;

    private BigDecimal paidamount = new BigDecimal(0);

    private BigDecimal advadjamt = new BigDecimal(0);

    private Boolean isreversed;

    private BigDecimal assetid;

    private BigDecimal capRev = new BigDecimal(0);

    private BigDecimal mrnid;

    private Date paybydate;

    public Supplierbilldetail()
    {
    }

    public Supplierbilldetail(final Integer id, final CVoucherHeader voucherheader,
            final Relation relation, final BigDecimal billamount, final BigDecimal passedamount,
            final Integer worksdetailid)
    {
        this.id = id;
        this.voucherheader = voucherheader;
        this.relation = relation;
        this.billamount = billamount;
        this.passedamount = passedamount;
        this.worksdetailid = worksdetailid;
    }

    public Supplierbilldetail(final Integer id, final CVoucherHeader voucherheader,
            final Relation relation, final EgBillregister egBillregister, final Date billdate,
            final String billnumber, final BigDecimal otherrecoveries, final String mrnnumber,
            final Date mrndate, final BigDecimal billamount, final BigDecimal passedamount,
            final String approvedby, final BigDecimal payableaccount, final String narration,
            final Integer worksdetailid, final BigDecimal tdsamount,
            final Boolean tdspaidtoit, final BigDecimal paidamount, final BigDecimal advadjamt,
            final Boolean isreversed, final BigDecimal assetid, final BigDecimal capRev,
            final BigDecimal mrnid, final Date paybydate)
    {
        this.id = id;
        this.voucherheader = voucherheader;
        this.relation = relation;
        this.egBillregister = egBillregister;
        this.billdate = billdate;
        this.billnumber = billnumber;
        this.otherrecoveries = otherrecoveries;
        this.mrnnumber = mrnnumber;
        this.mrndate = mrndate;
        this.billamount = billamount;
        this.passedamount = passedamount;
        this.approvedby = approvedby;
        this.payableaccount = payableaccount;
        this.narration = narration;
        this.worksdetailid = worksdetailid;
        this.tdsamount = tdsamount;
        this.tdspaidtoit = tdspaidtoit;
        this.paidamount = paidamount;
        this.advadjamt = advadjamt;
        this.isreversed = isreversed;
        this.assetid = assetid;
        this.capRev = capRev;
        this.mrnid = mrnid;
        this.paybydate = paybydate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public CVoucherHeader getVoucherheader()
    {
        return voucherheader;
    }

    public void setVoucherheader(final CVoucherHeader voucherheader)
    {
        this.voucherheader = voucherheader;
    }

    public Relation getRelation()
    {
        return relation;
    }

    public void setRelation(final Relation relation)
    {
        this.relation = relation;
    }

    public EgBillregister getEgBillregister()
    {
        return egBillregister;
    }

    public void setEgBillregister(final EgBillregister egBillregister)
    {
        this.egBillregister = egBillregister;
    }

    public Date getBilldate()
    {
        return billdate;
    }

    public void setBilldate(final Date billdate)
    {
        this.billdate = billdate;
    }

    public String getBillnumber()
    {
        return billnumber;
    }

    public void setBillnumber(final String billnumber)
    {
        this.billnumber = billnumber;
    }

    public BigDecimal getOtherrecoveries()
    {
        return otherrecoveries;
    }

    public void setOtherrecoveries(final BigDecimal otherrecoveries)
    {
        this.otherrecoveries = otherrecoveries;
    }

    public String getMrnnumber()
    {
        return mrnnumber;
    }

    public void setMrnnumber(final String mrnnumber)
    {
        this.mrnnumber = mrnnumber;
    }

    public Date getMrndate()
    {
        return mrndate;
    }

    public void setMrndate(final Date mrndate)
    {
        this.mrndate = mrndate;
    }

    public BigDecimal getBillamount()
    {
        return billamount;
    }

    public void setBillamount(final BigDecimal billamount)
    {
        this.billamount = billamount;
    }

    public BigDecimal getPassedamount()
    {
        return passedamount;
    }

    public void setPassedamount(final BigDecimal passedamount)
    {
        this.passedamount = passedamount;
    }

    public String getApprovedby()
    {
        return approvedby;
    }

    public void setApprovedby(final String approvedby)
    {
        this.approvedby = approvedby;
    }

    public BigDecimal getPayableaccount()
    {
        return payableaccount;
    }

    public void setPayableaccount(final BigDecimal payableaccount)
    {
        this.payableaccount = payableaccount;
    }

    public String getNarration()
    {
        return narration;
    }

    public void setNarration(final String narration)
    {
        this.narration = narration;
    }

    public Integer getWorksdetailid()
    {
        return worksdetailid;
    }

    public void setWorksdetailid(final Integer worksdetailid)
    {
        this.worksdetailid = worksdetailid;
    }

    public BigDecimal getTdsamount()
    {
        return tdsamount;
    }

    public void setTdsamount(final BigDecimal tdsamount)
    {
        this.tdsamount = tdsamount;
    }

    public Boolean getTdspaidtoit()
    {
        return tdspaidtoit;
    }

    public void setTdspaidtoit(final Boolean tdspaidtoit)
    {
        this.tdspaidtoit = tdspaidtoit;
    }

    public BigDecimal getPaidamount()
    {
        return paidamount;
    }

    public void setPaidamount(final BigDecimal paidamount)
    {
        this.paidamount = paidamount;
    }

    public BigDecimal getAdvadjamt()
    {
        return advadjamt;
    }

    public void setAdvadjamt(final BigDecimal advadjamt)
    {
        this.advadjamt = advadjamt;
    }

    public Boolean getIsreversed()
    {
        return isreversed;
    }

    public void setIsreversed(final Boolean isreversed)
    {
        this.isreversed = isreversed;
    }

    public BigDecimal getAssetid()
    {
        return assetid;
    }

    public void setAssetid(final BigDecimal assetid)
    {
        this.assetid = assetid;
    }

    public BigDecimal getCapRev()
    {
        return capRev;
    }

    public void setCapRev(final BigDecimal capRev)
    {
        this.capRev = capRev;
    }

    public BigDecimal getMrnid()
    {
        return mrnid;
    }

    public void setMrnid(final BigDecimal mrnid)
    {
        this.mrnid = mrnid;
    }

    public Date getPaybydate()
    {
        return paybydate;
    }

    public void setPaybydate(final Date paybydate)
    {
        this.paybydate = paybydate;
    }

}
