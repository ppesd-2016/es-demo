package es.demo.data;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FakeDataGenerator {

	public static void main(String[] args) {
	    try {
			FileInputStream file = new FileInputStream(new File("fakedatatemplate.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet companySheet = workbook.getSheet("company");
			XSSFSheet employeeSheet = workbook.getSheet("employee");
			XSSFSheet stockpriceSheet = workbook.getSheet("stockprice");
			Fairy fairy = Fairy.create();
			for(int i=1;i<=2;i++){
				Company company = fairy.company();
				String code = company.vatIdentificationNumber();
				writeCompany(companySheet, company, code);
				for(int j=1;j<=20;j++){
					Person person = fairy.person();
					writeEmployee(employeeSheet,person,code);
				}


				writeStockprice(stockpriceSheet, code);
			}
			file.close();
		    FileOutputStream out = new FileOutputStream(new File("fakedata.xlsx"));
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void writeCompany(XSSFSheet sheet, Company company, String code){
		int rownum = sheet.getLastRowNum()+1;
		XSSFRow row = sheet.createRow(rownum);

		XSSFCell codeCell = row.createCell(0);
		codeCell.setCellValue(code);
		
		XSSFCell nameCell = row.createCell(1);
		nameCell.setCellValue(company.name());
		
		XSSFCell urlCell = row.createCell(2);
		urlCell.setCellValue(company.url());
		
		XSSFCell emailCell = row.createCell(3);
		emailCell.setCellValue(company.email());
		
		XSSFCell sectorCell = row.createCell(4);
		sectorCell.setCellValue(company.domain());
		
		XSSFCell regNumberCell = row.createCell(5);
		regNumberCell.setCellValue(company.vatIdentificationNumber());
	}
	
	private static void writeEmployee(XSSFSheet sheet,Person person,String code){
		
		int rownum = sheet.getLastRowNum()+1;
		XSSFRow row = sheet.createRow(rownum);

		XSSFCell fullnameCell = row.createCell(0);
		fullnameCell.setCellValue(person.fullName());

		XSSFCell firstnameCell = row.createCell(1);
		firstnameCell.setCellValue(person.firstName());

		XSSFCell middlenameCell = row.createCell(2);
		middlenameCell.setCellValue(person.middleName());

		XSSFCell lastnameCell = row.createCell(3);
		lastnameCell.setCellValue(person.lastName());

		XSSFCell workemailCell = row.createCell(4);
		workemailCell.setCellValue(person.companyEmail());

		XSSFCell personalemailCell = row.createCell(5);
		personalemailCell.setCellValue(person.nationalIdentificationNumber()+"@gmail.com");

		XSSFCell ageCell = row.createCell(6);
		ageCell.setCellValue(person.age());

		XSSFCell contactnumberCell = row.createCell(7);
		contactnumberCell.setCellValue(person.telephoneNumber());

		XSSFCell companycodeCell = row.createCell(8);
		companycodeCell.setCellValue(code);

		XSSFCell employeeidCell = row.createCell(9);
		employeeidCell.setCellValue(person.username());
	}
	
	private static void writeStockprice(XSSFSheet sheet,String code){
		
		Random r = new Random();
		double base = r.nextInt(10);
		double S0 = base+r.nextDouble()*100;
		double vol = r.nextDouble();
		Map<Integer,Double> prices = FakePricer.getPrices(S0, vol);
		Set<Integer> dates = prices.keySet();
		
		String dt = "1984-12-31";  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int date:dates){
			c.add(Calendar.DATE, 1);  // number of days to add
			dt = sdf.format(c.getTime());  // dt is now the new date
			
			int rownum = sheet.getLastRowNum()+1;
			XSSFRow row = sheet.createRow(rownum);
			
			XSSFCell codeCell = row.createCell(0);
			codeCell.setCellValue(code);
			
			XSSFCell dateCell = row.createCell(1);
			dateCell.setCellValue(dt);
			
			XSSFCell priceCell = row.createCell(2);
			priceCell.setCellValue(prices.get(date));

		}
	}
	
	public static void print(Object... args){
		System.out.println();
		for (Object arg : args) {
	        System.out.print(arg+", ");
	    }
	}

}
