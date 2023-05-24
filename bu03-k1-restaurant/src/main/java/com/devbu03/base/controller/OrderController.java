package com.devbu03.base.controller;

import com.devbu03.base.auth.AuthenticationDetailResponse;
import com.devbu03.base.dto.OrderCTE;
import com.devbu03.base.dto.OrderDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.order.OrderService;
import com.devbu03.base.service.order.PdfExporter;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController extends BaseController<OrderService> {

  private final MessageSource messageSource;

  public OrderController(OrderService service, ResponseService responseService,
      MessageSource messageSource) {
    super(service, responseService);
    this.messageSource = messageSource;
  }

  @GetMapping("/invoice/{id}")
  public Response<OrderDTO> findInvoiceByOrderId(@PathVariable Long id) {
    OrderDTO order = getService().findInvoiceByOrderId(id);
    return super.success(order);
  }

  @GetMapping("/staff/export/{id}")
  public ResponseEntity<byte[]> exportInvoicePdfByOrderId(@PathVariable Long id)
      throws DocumentException {
    OrderDTO order = getService().findInvoiceByOrderId(id);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PdfExporter pdfExporter = new PdfExporter();
    pdfExporter.exportInvoiceToPdf(order, out);

    // Tạo phản hồi HTTP chứa tài liệu PDF
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    String fileName = "filename=invoice_" + id + "_" + df.format(new Date()) + ".pdf";
    ContentDisposition disposition = ContentDisposition
        .builder("attachment")
        .filename(fileName)
        .build();
    headers.setContentDisposition(disposition);
    headers.setContentLength(out.size());
    return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
  }

  @PutMapping("/staff/update-status/{id}")
  public Response updateStatusOrderByOrderId(@PathVariable Long id, @RequestParam Integer status) {
    getService().updateStatusOrderByOrderId(id, status);
    return super.success(messageSource.getMessage("success.statusUpdateOrder", null,
        LocaleContextHolder.getLocale()));
  }

  @PostMapping("/")
  public Response<OrderDTO> order(@RequestBody OrderDTO dto, Authentication authentication) {
    AuthenticationDetailResponse userAccount =
        (AuthenticationDetailResponse) authentication.getDetails();
    dto.setCreatedBy(userAccount.getId());
    OrderDTO orderDTO = this.getService().create(dto);
    return super.success(orderDTO);
  }

  @DeleteMapping("/owner/{id}")
  public Response delete(@PathVariable("id") Long id) {
    this.getService().deleteById(id);
    return super.success();
  }

  /**
   * Lấy tất cả lịch sử order theo id khách hàng
   *
   * @param userId
   * @return
   */
  @GetMapping("/customer/get/{userId}")
  public Response<List<OrderDTO>> getOrderHistoryByUserId(@PathVariable("userId") Long userId) {
    return getResponseService().success(getService().getAllOrder(userId));
  }

  @GetMapping("/owner/getTotalPriceOfDays")
  public Response<List<OrderDTO>> getMaxTotalPrice(@RequestParam String createdBegin,
      @RequestParam String createdEnd) {
    return super.success(getService().getMaxTotalPrice(createdBegin, createdEnd));
  }

  @GetMapping("/owner/getTotalPriceOfMonth")
  public Response<Integer> getTotalPriceOfMonth(@RequestParam Integer
      month, @RequestParam Integer year) {
    return super.success(getService().getTotalPriceOfMonth(month, year));
  }

  @GetMapping("/owner/totalOfDays")
  public Response<List<OrderCTE>> totalOfDays(@RequestParam String createdAt) {
    return super.success(getService().totalOfDays(createdAt));
  }

  @GetMapping("/owner/totalOfMonth")
  public Response<List<OrderCTE>> totalOfMonths(@RequestParam Integer month,
      @RequestParam Integer year) {
    return super.success(getService().totalOfMonths(month, year));
  }

  @GetMapping("/owner/getTotalPriceOfYear")
  public Response<List<OrderCTE>> getTotalPriceOfYear(@RequestParam Integer year) {
    return super.success(getService().getTotalPriceOfYear(year));
  }

  @GetMapping("/staff/list-order")
  public Response<List<OrderDTO>> getListOrder() {
    return super.success((getService().getListOrder()));
  }

  @GetMapping("staff/order")
  public Response<OrderDTO> getOrderById(@Param("id") Long id) {
    return super.success((getService().getOrderById(id)));
  }
}
