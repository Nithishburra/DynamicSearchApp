package com.ashokit.Reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ashokit.Binding.Response.InsuranceResponse;

public class InsuranceExcelExporter {

	public void Export(List<InsuranceResponse> plans, HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet Sheet = workbook.createSheet("plans");
		XSSFRow HeaderRow = Sheet.createRow(0);
		HeaderRow.createCell(0).setCellValue("plan ID");
		HeaderRow.createCell(1).setCellValue("plan Name");
		HeaderRow.createCell(2).setCellValue("Holder Name");
		HeaderRow.createCell(3).setCellValue("Holder Ssn");
		HeaderRow.createCell(4).setCellValue("plan Status");

		for (int i = 0; i < plans.size(); i++) {
			InsuranceResponse plan = plans.get(i);
			XSSFRow datarow = Sheet.createRow(i + 1);
			datarow.createCell(0).setCellValue(plan.getPlanId());
			datarow.createCell(1).setCellValue(plan.getPlanName());
			datarow.createCell(2).setCellValue(plan.getHolderName());
			datarow.createCell(3).setCellValue(plan.getHolderSsn());
			datarow.createCell(4).setCellValue(plan.getPlanStatus());
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}