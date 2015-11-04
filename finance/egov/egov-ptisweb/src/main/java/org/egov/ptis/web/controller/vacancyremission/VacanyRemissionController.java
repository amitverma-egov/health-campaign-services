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
package org.egov.ptis.web.controller.vacancyremission;

import static org.egov.ptis.constants.PropertyTaxConstants.ARR_COLL_STR;
import static org.egov.ptis.constants.PropertyTaxConstants.ARR_DMD_STR;
import static org.egov.ptis.constants.PropertyTaxConstants.CURR_COLL_STR;
import static org.egov.ptis.constants.PropertyTaxConstants.CURR_DMD_STR;
import static org.egov.ptis.constants.PropertyTaxConstants.TARGET_TAX_DUES;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.eis.web.controller.workflow.GenericWorkFlowController;
import org.egov.infra.utils.DateUtils;
import org.egov.ptis.client.util.PropertyTaxUtil;
import org.egov.ptis.domain.dao.property.BasicPropertyDAO;
import org.egov.ptis.domain.entity.property.BasicProperty;
import org.egov.ptis.domain.entity.property.BasicPropertyImpl;
import org.egov.ptis.domain.entity.property.VacancyRemission;
import org.egov.ptis.domain.service.property.PropertyService;
import org.egov.ptis.domain.service.property.VacancyRemissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/vacancyremission")
public class VacanyRemissionController extends GenericWorkFlowController {

	@Autowired
    private BasicPropertyDAO basicPropertyDAO;
	
	
	private PropertyTaxUtil propertyTaxUtil;
	
	private BasicProperty basicProperty;
	
	private VacancyRemission vacancyRemission;
	
	private VacancyRemissionService vacancyRemissionService;
	
	@Autowired
    private PropertyService propertyService;
	
	@Autowired
	public VacanyRemissionController(VacancyRemissionService vacancyRemissionService, PropertyTaxUtil propertyTaxUtil){
		this.propertyTaxUtil = propertyTaxUtil;
		this.vacancyRemissionService = vacancyRemissionService;
	}
	
	@ModelAttribute
	public VacancyRemission vacancyRemissionModel(@PathVariable String assessmentNo) {
		vacancyRemission = new VacancyRemission();
		basicProperty = basicPropertyDAO.getBasicPropertyByPropertyID(assessmentNo);
	    if (basicProperty != null) {
	    	vacancyRemission.setBasicProperty((BasicPropertyImpl) basicProperty);
	    }
		return vacancyRemission;
	}
	
	@RequestMapping(value = "/create/{assessmentNo}",method = RequestMethod.GET)
	public String newForm(final Model model, @PathVariable String assessmentNo,final HttpServletRequest request) {
	    if (basicProperty != null) {
	    	final Map<String, BigDecimal> propertyTaxDetails = propertyService.getCurrentPropertyTaxDetails(basicProperty
                    .getActiveProperty());
            final BigDecimal currentPropertyTax = propertyTaxDetails.get(CURR_DMD_STR);
            final BigDecimal currentPropertyTaxDue = propertyTaxDetails.get(CURR_DMD_STR).subtract(
                    propertyTaxDetails.get(CURR_COLL_STR));
            final BigDecimal arrearPropertyTaxDue = propertyTaxDetails.get(ARR_DMD_STR).subtract(
                    propertyTaxDetails.get(ARR_COLL_STR));
            final BigDecimal currentWaterTaxDue = propertyService.getWaterTaxDues(basicProperty.getUpicNo(), request);
            model.addAttribute("currentPropertyTax", currentPropertyTax);
            model.addAttribute("currentPropertyTaxDue", currentPropertyTaxDue);
            model.addAttribute("arrearPropertyTaxDue", arrearPropertyTaxDue);
            model.addAttribute("currentWaterTaxDue", currentWaterTaxDue);
            if (currentWaterTaxDue.add(currentPropertyTaxDue).add(arrearPropertyTaxDue).longValue() > 0) {
                model.addAttribute("taxDuesErrorMsg", "Please clear property tax due for availing vacancy remission for your property ");
                return TARGET_TAX_DUES;
            }
            
	    	prepareWorkflow(model,vacancyRemission,new WorkflowContainer());
		    model.addAttribute("stateType", vacancyRemission.getClass().getSimpleName());
		    vacancyRemissionService.addModelAttributes(model, basicProperty);
	    	
	    }
	    return "vacancyRemission-form";
	}
	
	@RequestMapping(value = "/create/{assessmentNo}", method = RequestMethod.POST)
	public String saveVacancyRemission(@Valid @ModelAttribute VacancyRemission vacancyRemission, final BindingResult resultBinder, RedirectAttributes redirectAttributes, 
			final Model model,final HttpServletRequest request,@RequestParam String workFlowAction){
		
		validateDates(vacancyRemission,resultBinder,request);
		if (resultBinder.hasErrors()){
			if (basicProperty != null) {
				prepareWorkflow(model,vacancyRemission,new WorkflowContainer());
			    model.addAttribute("stateType", vacancyRemission.getClass().getSimpleName());
			    vacancyRemissionService.addModelAttributes(model, basicProperty);
		    }
		    return "vacancyRemission-form";
		}else{
			Long approvalPosition = 0l;
	        String approvalComent = "";

	        if (request.getParameter("approvalComent") != null)
	            approvalComent = request.getParameter("approvalComent");
	        if (request.getParameter("workFlowAction") != null)
	            workFlowAction = request.getParameter("workFlowAction");
	        if (request.getParameter("approvalPosition") != null && !request.getParameter("approvalPosition").isEmpty())
	            approvalPosition = Long.valueOf(request.getParameter("approvalPosition"));

	        vacancyRemissionService.saveVacancyRemission(vacancyRemission,approvalPosition,approvalComent,null,workFlowAction);
	        
	        String successMsg = "Vacancy Remission Saved Successfully in the System and forwarded to : "+propertyTaxUtil.getApproverUserName(approvalPosition);
	        model.addAttribute("successMessage", successMsg);
		}
            
		return "vacancyRemission-success";
	}
	
	private void validateDates(final VacancyRemission vacancyRemission, final BindingResult errors,
            final HttpServletRequest request) {
		
		int noOfMonths = DateUtils.noOfMonths(vacancyRemission.getVacancyFromDate(), vacancyRemission.getVacancyToDate());
		if(noOfMonths<6){
			errors.rejectValue("vacancyToDate", "vacancyToDate.incorrect");
		}
    }
	
}
