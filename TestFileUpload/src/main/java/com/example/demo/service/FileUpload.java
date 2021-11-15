package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Content;
import com.example.demo.entity.Files;
import com.example.demo.entity.ResponseMessage;
import com.example.demo.exceptions.FileEmptyException;
import com.example.demo.exceptions.FileExtensionException;
import com.example.demo.exceptions.HeaderNotMatchedException;
import com.example.demo.rabbitMqConfig.RabbitMqSender;
import com.example.demo.repo.ContentRepo;
import com.example.demo.repo.FileRepo;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class FileUpload {

	@Autowired
	private ContentRepo contentRepo;

	@Autowired
	private RabbitMqSender mqSender;

	private static final String headers[] = { "name","TransactionType", "Transaction_id", "Amount", "Date" };

	public ResponseMessage uploadFile(MultipartFile file) throws Exception {

    	boolean	extensionCheck = false;

		if (file.isEmpty())
			throw new FileEmptyException("File is Empty");

		extensionCheck = checkExtension(file);
		if (!extensionCheck)
			throw new FileExtensionException("File Extension is not correct");

		Files files = new Files(file.getOriginalFilename());

		InputStream is = file.getInputStream();

		List<Content> excelToUser = excelToUser(is, files);
		return saveFileRecord(excelToUser, files);
	}

	public Boolean checkExtension(MultipartFile file) {

		return file.getContentType().equalsIgnoreCase("application/vnd.ms-excel");
	}

	public ResponseMessage saveFileRecord(List<Content> user, Files files) throws JsonProcessingException {
		this.contentRepo.saveAll(user);
		return sendMessageInQueue(files);
	}

	public ResponseMessage sendMessageInQueue(Files file) throws JsonProcessingException {
	
		return mqSender.send(file);
	
	}

	public Boolean validateheader(Row headerRow) {

		return headerRow.getCell(0).getStringCellValue().equalsIgnoreCase(headers[0])
				&& headerRow.getCell(1).getStringCellValue().equalsIgnoreCase(headers[1])
				&& headerRow.getCell(2).getStringCellValue().equalsIgnoreCase(headers[2])
				&& headerRow.getCell(3).getStringCellValue().equalsIgnoreCase(headers[3])
				&& headerRow.getCell(4).getStringCellValue().equalsIgnoreCase(headers[4]);

	}

	public List<Content> excelToUser(InputStream is, Files file) throws IOException {
		List<Content> list = new ArrayList<Content>();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			boolean headercheck = validateheader(sheet.getRow(0));
			if (!headercheck)
				throw new HeaderNotMatchedException("Header not matched");
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Content content = new Content();
				HSSFRow row = sheet.getRow(i);
				content.setName(row.getCell(0).getStringCellValue());
				content.setTransactionType(row.getCell(1).getStringCellValue());
				content.setTransactionID(row.getCell(2).getStringCellValue());
				content.setAmount(row.getCell(3).getNumericCellValue());
				content.setDate(row.getCell(4).getDateCellValue() + "");
				content.setFiles(file);
				list.add(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
		return list;
	}

}
