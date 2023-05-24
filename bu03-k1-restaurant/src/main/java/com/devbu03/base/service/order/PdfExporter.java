package com.devbu03.base.service.order;

import com.devbu03.base.common.enums.OrderEnum;
import com.devbu03.base.dto.OrderDTO;
import com.devbu03.base.dto.OrderDetailDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class PdfExporter {

  public static final String FONT_BY_ME = "./etc/fonts/times.ttf";

  private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
  private Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
  private Font tableHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD);
  private Font tableContentFont = new Font(FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL);
  private Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

  BaseFont baseFontTimesNewRoman;

  {
    try {
      baseFontTimesNewRoman = BaseFont.createFont(FONT_BY_ME,
          BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

      titleFont = new Font(baseFontTimesNewRoman, 18, Font.BOLD);
      subtitleFont = new Font(baseFontTimesNewRoman, 14, Font.BOLD);
      tableHeaderFont = new Font(baseFontTimesNewRoman, 12, Font.BOLD);
      tableContentFont = new Font(baseFontTimesNewRoman, 12, Font.NORMAL);
      totalFont = new Font(baseFontTimesNewRoman, 12, Font.BOLD);

    } catch (DocumentException | IOException e) {
      e.printStackTrace();
    }
  }

  private void addTitle(Document document) throws DocumentException {
    Paragraph title = new Paragraph("HÓA ĐƠN", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    title.setSpacingAfter(20);
    document.add(title);
  }

  private void addInvoiceInfo(Document document, OrderDTO invoice) throws DocumentException {
    Paragraph subtitle = new Paragraph("Mã hóa đơn #" + invoice.getId(), subtitleFont);
    subtitle.setAlignment(Element.ALIGN_LEFT);
    subtitle.setSpacingAfter(10);
    document.add(subtitle);

    Paragraph customerName = new Paragraph("Mã người tạo #" + invoice.getCreatedBy(),
        subtitleFont);
    customerName.setAlignment(Element.ALIGN_LEFT);
    customerName.setSpacingAfter(5);
    document.add(customerName);

    // Tạo đối tượng DateTimeFormatter với định dạng "dd/MM/yyyy HH:mm:ss"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    // Chuyển đổi LocalDateTime sang chuỗi theo định dạng "dd/MM/yyyy HH:mm:ss"
    String formattedDateTime = invoice.getCreatedAt().format(formatter);
    Paragraph invoiceDate = new Paragraph("Ngày tạo: " + formattedDateTime,
        subtitleFont);
    invoiceDate.setAlignment(Element.ALIGN_LEFT);
    invoiceDate.setSpacingAfter(5);
    document.add(invoiceDate);

    Paragraph statusInvoice = new Paragraph("Tình trạng đơn hàng: "
        + ((invoice.getStatus() == OrderEnum.ORDER_PAID.getValue()) ? "Đã thanh toán"
        : "Chưa thanh toán"),
        subtitleFont);
    statusInvoice.setAlignment(Element.ALIGN_LEFT);
    statusInvoice.setSpacingAfter(5);
    document.add(statusInvoice);

    Paragraph addressInvoice = new Paragraph("Địa chỉ đặt hàng: "
        + ((invoice.getAddress() != null) ? invoice.getAddress() : "Trống"),
        subtitleFont);
    addressInvoice.setAlignment(Element.ALIGN_LEFT);
    addressInvoice.setSpacingAfter(5);
    document.add(addressInvoice);
  }

  private void addInvoiceItems(Document document, OrderDTO invoice) throws DocumentException {
    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100);
    table.setSpacingBefore(20);
    table.setSpacingAfter(20);

    PdfPCell itemCell = new PdfPCell(new Phrase("Tên sản phẩm", tableHeaderFont));
    itemCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    itemCell.setVerticalAlignment(Element.ALIGN_CENTER);
    itemCell.setMinimumHeight(40);
    table.addCell(itemCell);

    PdfPCell itemDescriptionCell = new PdfPCell(new Phrase("Mô tả sản phẩm", tableHeaderFont));
    itemDescriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    itemCell.setVerticalAlignment(Element.ALIGN_CENTER);
    itemCell.setMinimumHeight(40);
    table.addCell(itemDescriptionCell);

    PdfPCell quantityCell = new PdfPCell(new Phrase("Số lượng", tableHeaderFont));
    quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    itemCell.setVerticalAlignment(Element.ALIGN_CENTER);
    itemCell.setMinimumHeight(40);
    table.addCell(quantityCell);

    PdfPCell priceProductCell = new PdfPCell(new Phrase("Đơn giá", tableHeaderFont));
    priceProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    itemCell.setVerticalAlignment(Element.ALIGN_CENTER);
    itemCell.setMinimumHeight(40);
    table.addCell(priceProductCell);

    PdfPCell totalCell = new PdfPCell(new Phrase("Thành tiền", tableHeaderFont));
    totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    itemCell.setVerticalAlignment(Element.ALIGN_CENTER);
    itemCell.setMinimumHeight(40);
    table.addCell(totalCell);

    for (OrderDetailDTO item : invoice.getItems()) {
      PdfPCell nameCell = new PdfPCell(
          new Phrase(item.getProduct().getName(), tableContentFont));
      nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      table.addCell(nameCell);

      PdfPCell descriptionCell = new PdfPCell(
          new Phrase(item.getProduct().getDescription(), tableContentFont));
      descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      table.addCell(descriptionCell);

      PdfPCell quantityValueCell = new PdfPCell(
          new Phrase(item.getQuantity().toString(), tableContentFont));
      quantityValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(quantityValueCell);

      PdfPCell priceProductValueCell = new PdfPCell(
          new Phrase(item.getProduct().getPrice().toString() + " đồng", tableContentFont));
      priceProductValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(priceProductValueCell);

      PdfPCell priceTotalValueCell = new PdfPCell(
          new Phrase(item.getPrice().toString() + " đồng", tableContentFont));
      priceTotalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(priceTotalValueCell);
    }

    document.add(table);
  }

  private void addTotal(Document document, OrderDTO invoice) throws DocumentException {
    Paragraph subtotal = new Paragraph("Tổng tiền: " + invoice.getTotal().toString() + " đồng",
        totalFont);
    subtotal.setAlignment(Element.ALIGN_RIGHT);
    subtotal.setSpacingAfter(5);
    document.add(subtotal);

    Paragraph nameRestaurant = new Paragraph("Nhà hàng DevBu03", totalFont);
    nameRestaurant.setAlignment(Element.ALIGN_RIGHT);
    nameRestaurant.setSpacingAfter(5);
    document.add(nameRestaurant);
  }

  public void exportInvoiceToPdf(OrderDTO invoice, OutputStream out) throws DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, out);
    document.open();

    addTitle(document);
    addInvoiceInfo(document, invoice);
    addInvoiceItems(document, invoice);
    addTotal(document, invoice);

    document.close();
  }
}
