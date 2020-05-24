package others;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelSample {

    public static void main(String[] args) {
        try {
            System.out.println("=============");
//用输入流从本地拿到对应的Excel文件
            InputStream stream = new FileInputStream("D:\\taxdata\\demo.xlsx");
//指定要生成的xml的路径，并构建文件对象
            File f = new File("D:\\taxdata\\test4.xml");// 新建个file对象把解析之后得到的xml存入改文件中
            writerXML(stream, f);// 将数据以xml形式写入文本
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void writerXML(InputStream stream, File f)
            throws IOException {
        System.out.println("into writerXML");
        FileOutputStream fo = new FileOutputStream(f);// 得到输入流
        Document doc = readExcell(stream);// 读取EXCEL函数
        Format format = Format.getCompactFormat().setEncoding("UTF-8")
                .setIndent("");
        XMLOutputter XMLOut = new XMLOutputter(format);// 在元素后换行，每一层元素缩排四格
        XMLOut.output(doc, fo);
        fo.close();
    }

    private static Document readExcell(InputStream stream) {
        System.out.println("into readExcell");
//设置根<tax_institutions></tax_institutions>元素
        Element root = new Element("tax_institutions");
        Document doc = new Document(root);
        try {
            XSSFWorkbook wb = new XSSFWorkbook(stream);
//获取工作薄的个数，即一个excel文件中包含了多少个Sheet工作簿
            int WbLength = wb.getNumberOfSheets();
            System.out.println("WbLength=" + WbLength);
            /*对每一个工作簿进行操作*/
            for (int i = 0; i < WbLength; i++) {
                XSSFSheet shee = wb.getSheetAt(i);
                int length = shee.getLastRowNum();
                System.out.println("行数：" + length);
                for (int j = 1; j <= length; j++) {
                    XSSFRow row = shee.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    int cellNum = row.getPhysicalNumberOfCells();// 获取一行中最后一个单元格的位置
                    System.out.println("列数cellNum：" + cellNum);
                    Element e = null;
//设置根元素下的并列元素<tax_institution></tax_institution>
                    e = new Element("tax_institution");
// Element[] es = new Element[16];
                    for (int k = 0; k < cellNum; k++) {
                        XSSFCell cell = row.getCell((short) k);
                        String temp = get(k);
                        System.out.print(k + " " + temp + ":");
                        Element item = new Element(temp);
                        if (cell == null) {
                            item.setText("");
                            e.addContent(item);
                            cellNum++;//如果存在空列，那么cellNum增加1，这一步很重要。
                            continue;
                        } else {
                            String cellvalue = "";
                            switch (cell.getCellType()) {
// 如果当前Cell的Type为NUMERIC
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                case HSSFCell.CELL_TYPE_FORMULA: {
// 判断当前的cell是否为Date
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
// 如果是Date类型则，转化为Data格式

// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
// cellvalue =
                                        cell.getDateCellValue().toLocaleString();

// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                                        Date date = cell.getDateCellValue();
                                        SimpleDateFormat sdf = new SimpleDateFormat(
                                                "yyyy-MM-dd");
                                        cellvalue = sdf.format(date);
                                        item.setText(cellvalue);

                                    }
// 如果是纯数字
                                    else {
// 取得当前Cell的数值
                                        cellvalue = String.valueOf((int) cell.getNumericCellValue());
                                        item.setText(cellvalue);
                                    }
                                    break;
                                }
// 如果当前Cell的Type为STRIN
                                case HSSFCell.CELL_TYPE_STRING:
// 取得当前的Cell字符串
                                    cellvalue = cell.getRichStringCellValue()
                                            .getString();
                                    item.setText(cellvalue);
                                    break;
// 默认的Cell值
                                default:
                                    cellvalue = " ";
                                    item.setText(cellvalue);
                            }
                            e.addContent(item);
                            System.out.println(cellvalue);
                        }
                    }
                    root.addContent(e);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return doc;
    }

    //设置并列元素里的子元素名
    private static String get(int k) {
        String test = "";
        switch (k) {
            case 0:
                test = "org_name";
                break;
            case 1:
                test = "legal_mobile_phone";
                break;
            case 2:
                test = "org_address";
                break;
            case 3:
                test = "cert_type";
                break;
            case 4:
                test = "postal_code";
                break;
            case 5:
                test = "reg_sum";
                break;
            case 6:
                test = "business_scope";
                break;
            case 7:
                test = "social_credit_code";
                break;
            case 8:
                test = "reg_type";
                break;
            case 9:
                test = "legal_person_name";
                break;
            case 10:
                test = "cert_number";
                break;
            case 11:
                test = "found_time";
                break;
            case 12:
                test = "service_status";
                break;
            case 13:
                test = "staff_sum";
                break;
            case 14:
                test = "partner_sum";
                break;
            case 15:
                test = "is_branch_org";
                break;
            default:
        }
        return test;

    }

}