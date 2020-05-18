import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelXML {
    private final static short OTA_CODE_INDEX = 2;
    private final static short OTA_DESC_INDEX = 3;
    private final static short OTA_TYPE_INDEX = 5;
    private final static short NDC_CODE_INDEX = 7;
    private final static short NDC_CODESET_INDEX = 8;
    private final static short NDC_DESC_INDEX = 9;
    private final static String xlsxFile = "/home/qingrong.wang/a_work/Task/18198/PADIS_Pricing Servicing Error Codes_Less_20200317.xlsx";
    private final static String xmlFile = "/home/qingrong.wang/a_work/Task/18198/RePricePADISCode_Less_OneContain.xml";

    private final static String XML_ROOT_ELEMENT = "ErrorMappings";
    private final static String XML_ErrorMapping_ELEMENT = "ErrorMapping";
    private final static String XML_ErrorMessage_ELEMENT = "ErrorMessage";
    private final static String XML_ErrorContainList_ELEMENT = "ContainsList";
    private final static String XML_ErrorContain_ELEMENT = "Contains";

    private final static String XML_OTAErrorCode_Attribute = "otaErrorCode";
    private final static String XML_NDCErrorCode_Attribute = "padisErrorCode";
    private final static String XML_NDCErrorCodeSet_Attribute = "padisSet";

    public static void main(String[] args) {
        try {

            InputStream stream = new FileInputStream(xlsxFile);
            File f = new File(xmlFile);
            writerXML(stream, f);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find the file path.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writerXML(InputStream stream, File f)
            throws IOException {
        FileOutputStream fo = new FileOutputStream(f);
        Document doc = readExcell(stream);
        Format format = Format.getCompactFormat().setEncoding("UTF-8")
                .setIndent("");
        XMLOutputter XMLOut = new XMLOutputter(format);
        XMLOut.output(doc, fo);
        fo.close();
    }

    private static Document readExcell(InputStream stream) {

        // set the root Element
        Element root = new Element(XML_ROOT_ELEMENT);

        Document doc = new Document(root);
        try {
            XSSFWorkbook wb = new XSSFWorkbook(stream);
            int WbLength = wb.getNumberOfSheets();
            System.out.println("Total Sheets:  " + WbLength);

            for (int i = 0; i < WbLength - 4; i++) {
                XSSFSheet shee = wb.getSheetAt(i);
                int length = shee.getPhysicalNumberOfRows();

                System.out.println("Processing Sheet :  " + shee.getSheetName());
                System.out.println("Total Rows:  " + length);

                generateErrorMapForEachSheet(length, shee, root);

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

    private static void generateErrorMapForEachSheet(int length, XSSFSheet shee, Element root) {
        for (int j = 1; j < length; j++) {
            /*System.out.println("Processing Row :  " + j);*/
            XSSFRow row = shee.getRow(j);
            if (row == null) {
                continue;
            }
            // generate the ErrorMapping Block
            Element errorMapping = new Element(XML_ErrorMapping_ELEMENT);
            generateErrorMapping(errorMapping, row, OTA_CODE_INDEX, XML_OTAErrorCode_Attribute, true);
            generateErrorMapping(errorMapping, row, NDC_CODE_INDEX, XML_NDCErrorCode_Attribute, true);
            generateErrorMapping(errorMapping, row, NDC_CODESET_INDEX, XML_NDCErrorCodeSet_Attribute, true);
            generateErrorMapping(errorMapping, row, NDC_DESC_INDEX, XML_ErrorMessage_ELEMENT, false);
            // generate the containList Block
            Element containList = new Element(XML_ErrorContainList_ELEMENT);
            errorMapping.addContent(containList);
            /*generateErrorMapping(containList, row, OTA_DESC_INDEX, XML_ErrorContain_ELEMENT, false);*/
            generateErrorMapping(containList, row, OTA_TYPE_INDEX, XML_ErrorContain_ELEMENT, false);
            // add the ErrorMapping into the root element
            root.addContent(errorMapping);
        }
    }

    private static void generateErrorMapping(Element errorMapping, XSSFRow row, short cellIndex, String elementName, boolean attribute) {

        XSSFCell cell = row.getCell((short) cellIndex);
        String cellValue = "";
        if (cell == null) {
            return;
        }

        switch (cell.getCellTypeEnum()) {
            case NUMERIC: {
                cellValue = String.valueOf((int) cell.getNumericCellValue());
                break;
            }
            case STRING: {
                cellValue = cell.getRichStringCellValue().getString();
                break;
            }
            default:
                System.out.println("Cell Type not identified. ");
        }

        if (!cellValue.isEmpty()) {
            if (attribute) {
                errorMapping.setAttribute(elementName, cellValue);
            } else {
                Element item = new Element(elementName);
                item.setText(cellValue);
                errorMapping.addContent(item);
            }
        }
    }

}