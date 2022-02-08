package com.ashokit.Reports;

import java.util.List;
import java.awt.Color;
import javax.servlet.http.HttpServletResponse;

import com.ashokit.Binding.Response.InsuranceResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class InsurancePdfexporter {

	public void ExportPdf(List<InsuranceResponse> plans, HttpServletResponse response) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);

		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph("List of plans", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.0f, 1.5f, 2.0f, 2.0f, 2.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Plan ID", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("plan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("holder Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("holder Ssn", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("plan Status", font1));
		table.addCell(cell);

		for (InsuranceResponse plan : plans) {
			table.addCell(String.valueOf((plan).getPlanId()));
			table.addCell(plan.getPlanName());
			table.addCell(plan.getHolderName());
			table.addCell(String.valueOf((plan).getHolderSsn()));
			table.addCell(plan.getPlanStatus());
		}

		document.add(table);
		document.close();
	}
}
