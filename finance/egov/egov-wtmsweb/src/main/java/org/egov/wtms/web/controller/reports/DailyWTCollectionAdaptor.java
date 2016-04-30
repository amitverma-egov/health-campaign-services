/**
 * eGov suite of products aim to improve the internal efficiency,transparency,
   accountability and the service delivery of the government  organizations.

    Copyright (C) <2015>  eGovernments Foundation

    The updated version of eGov suite of products as by eGovernments Foundation
    is available at http://www.egovernments.org

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see http://www.gnu.org/licenses/ or
    http://www.gnu.org/licenses/gpl.html .

    In addition to the terms of the GPL license to be adhered to in using this
    program, the following additional terms are to be complied with:

        1) All versions of this program, verbatim or modified must carry this
           Legal Notice.

        2) Any misrepresentation of the origin of the material is prohibited. It
           is required that all modified versions of this material be marked in
           reasonable ways as different from the original version.

        3) This license does not grant any rights to any user of the program
           with regards to rights under trademark law for use of the trade names
           or trademarks of eGovernments Foundation.

  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.wtms.web.controller.reports;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.egov.wtms.application.service.DailyWTCollectionReport;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DailyWTCollectionAdaptor implements JsonSerializer<DailyWTCollectionReport> {

    @Override
    public JsonElement serialize(final DailyWTCollectionReport dailyWTCollectionReport, final Type typeOfSrc,
            final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        final SimpleDateFormat receiptDateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        jsonObject.addProperty("receiptNo", dailyWTCollectionReport.getReceiptNumber());
        jsonObject.addProperty("receiptDate", receiptDateFormatter.format(dailyWTCollectionReport.getReceiptDate()));
        jsonObject.addProperty("consumerCode", dailyWTCollectionReport.getConsumerCode());
        jsonObject.addProperty("consumerName", dailyWTCollectionReport.getConsumerName());
        jsonObject.addProperty("doorNumber", dailyWTCollectionReport.getDoorNumber());
        jsonObject.addProperty("wardName", dailyWTCollectionReport.getWardName());
        jsonObject.addProperty("paidAt", dailyWTCollectionReport.getPaidAt());
        jsonObject.addProperty("connectionType", dailyWTCollectionReport.getConnectionType());
        jsonObject.addProperty("fromInstallment", dailyWTCollectionReport.getFromInstallment());
        jsonObject.addProperty("toInstallment", dailyWTCollectionReport.getToInstallment());
        jsonObject.addProperty("status", dailyWTCollectionReport.getStatus());
        jsonObject.addProperty("paidAt", dailyWTCollectionReport.getPaidAt());
        jsonObject.addProperty("paymentMode", dailyWTCollectionReport.getPaymentMode());
        jsonObject.addProperty("connectionType", dailyWTCollectionReport.getConnectionType());
        jsonObject.addProperty("currentTotal", dailyWTCollectionReport.getCurrentTotal());
        jsonObject.addProperty("arrearTotal", dailyWTCollectionReport.getArrearTotal());
        jsonObject.addProperty("total", dailyWTCollectionReport.getTotal());
        jsonObject.addProperty("cancellationDetails", dailyWTCollectionReport.getCancellationDetails());
        return jsonObject;
    }

}
