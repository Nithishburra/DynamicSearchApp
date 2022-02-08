package com.ashokit.Rest;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.tool.schema.spi.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.Binding.Request.InsuranceRequest;
import com.ashokit.Binding.Response.InsuranceResponse;
import com.ashokit.Reports.InsuranceExcelExporter;
import com.ashokit.Reports.InsurancePdfexporter;
import com.ashokit.service.InsuranceService;
import com.lowagie.text.DocumentException;

@RestController
public class InsuranceController {

	@Autowired
	private InsuranceService service;

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformatter.format(new Date());
		String headerkey = "Content-Disposition";
		String headerValue = "attachement; filename = plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerkey, headerValue);

		List<InsuranceResponse> plans = service.bySearch(null);
		InsuranceExcelExporter excelreport = new InsuranceExcelExporter();
		excelreport.Export(plans, response);
	}
	

	@GetMapping("/pdf") 
    public void generatePDF(HttpServletResponse response) throws Exception { 
	 response.setContentType("application/pdf"); 
	 
    String headerKey = "Content-Disposition"; 
    String headerValue ="attachment; filename=Plans.pdf";
     response.setHeader(headerKey, headerValue);
     List<InsuranceResponse> plans = service.bySearch(null);
	  InsurancePdfexporter pdfreport = new InsurancePdfexporter();
	  //pdfreport.ExportPdf(plans);  
	  pdfreport.ExportPdf(plans, response);
 }

	@PostMapping("/plans")
	public ResponseEntity<List<InsuranceResponse>> bySearch(@RequestBody InsuranceRequest request) {
		List<InsuranceResponse> search = service.bySearch(request);
		return new ResponseEntity<>(search, HttpStatus.OK);
	}

	@GetMapping("/planname")
	public ResponseEntity<List<String>> getbyplanname() {
		List<String> list = service.getbyplanname();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getbyplanstatus() {
		List<String> planstatuslist = service.getbyplanstatus();
		return new ResponseEntity<>(planstatuslist, HttpStatus.OK);
	}

}
