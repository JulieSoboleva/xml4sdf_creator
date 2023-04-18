package com.example.mainform;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Parser
{
    public static List<Doc> data = new ArrayList<>();
    public static Map<Integer, String> refAccess_condition = new HashMap<>();
    public static Map<Integer, String> refCounetparty = new HashMap<>();
    public static Map<Integer, String> refGroups = new HashMap<>();
    public static Map<Integer, String> refHeight_sys = new HashMap<>();
    public static Map<Integer, String> refReference_sys = new HashMap<>();
    public static Map<Integer, String> refRegion = new HashMap<>();
    public static Map<Integer, String> refScale = new HashMap<>();
    public static Map<Integer, String> refSecret_class = new HashMap<>();
    public static Map<Integer, String> refStorage_meanings = new HashMap<>();
    public static Map<Integer, Integer>[] refStorage = new Map[16];
    public static Map<Integer, String>[] refSubtype = new Map[16];

    public static void parseXlsFile(String path) throws IOException
    {
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Row curRow;
        Doc curDoc;
        int index = 1;
        for (int i=4; i < 403; i++)
        {
            curRow = sheet.getRow(i);
            curDoc = new Doc(index);
            for (int j = 2; j < 18; j++) {
                switch (j) {
                    case 2:
                        curDoc.setName(getStringValue(curRow.getCell(j)));
                        break;
                    case 3:
                        curDoc.setExtraRegionInfo(getStringValue(curRow.getCell(j)));
                        break;
                    case 4:
                        curDoc.setReferenceSystem(getStringValue(curRow.getCell(j)));
                        break;
                    case 6:
                        curDoc.setStorageFormat(getStringValue(curRow.getCell(j)));
                        break;
                    case 7:
                        curDoc.setSecretClass(getStringValue(curRow.getCell(j)));
                        break;
                    case 8:
                        curDoc.setCreator(getStringValue(curRow.getCell(j)));
                        break;
                    case 9:
                        curDoc.setRightHolder(getStringValue(curRow.getCell(j)));
                        break;
                    case 10:
                        curDoc.setAreaStateDate(getStringValue(curRow.getCell(j)));
                        break;
                    case 11:
                        curDoc.setAccessCondition(getStringValue(curRow.getCell(j)));
                        break;
                    case 12:
                        curDoc.setObjectCreateDate(getStringValue(curRow.getCell(j)));
                        break;
                    case 13:
                        curDoc.setSubtype(getStringValue(curRow.getCell(j)));
                        break;
                    case 15:
                        curDoc.setObjectQuantity(getStringValue(curRow.getCell(j)));
                        break;
                    case 16:
                        curDoc.setComments(getStringValue(curRow.getCell(j)));
                        break;
                    case 17:
                        curDoc.setLocation(getStringValue(curRow.getCell(j)));
                        break;
                    default:
                        break;
                }
            }
            if (!curDoc.isEmpty()) {
                if (index == 1) {
                    data.add(curDoc);
                }
                else {
                    curDoc.cloneFieldsFrom(data.get(data.size()-1));
                    data.add(curDoc);
                }
            }
            index++;
        }

        System.out.println("Кол-во записей: " + data.size());
        workbook.close();
        file.close();
    }

    private static String getStringValue(Cell c)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //"HH:mm:ss MM/dd/yyyy");
        switch (c.getCellType())
        {
            case STRING:
                return c.getRichStringCellValue().getString();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(c))
                {
                    return c.getLocalDateTimeCellValue().format(formatter);
                } else
                {
                    return String.valueOf((int)c.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(c.getBooleanCellValue());

            case FORMULA:
                return c.getCellFormula();

            default:
                return "";
        }
    }

    private static void printMap(Map<Integer, String> map)
    {
        if (map == null)
            return;
        for (Integer key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

    private static void printMapInteger(Map<Integer, Integer> map)
    {
        if (map == null)
            return;
        for (Integer key : map.keySet())
        {
            System.out.println(key + " : " + map.get(key));
        }
    }

    private static void printListOfDoc(List<Doc> docs)
    {
        for (Doc doc : docs) {
            System.out.println(doc);
        }
    }

    public static void parseReferences(String path) throws IOException
    {
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        Row curRow;
        for (int i=2; i < 8; i++) {
            curRow = sheet.getRow(i);
            refAccess_condition.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=11; i < 103; i++) {
            curRow = sheet.getRow(i);
            refCounetparty.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=106; i < 122; i++) {
            curRow = sheet.getRow(i);
            refGroups.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=125; i < 153; i++) {
            curRow = sheet.getRow(i);
            refHeight_sys.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=156; i < 170; i++) {
            curRow = sheet.getRow(i);
            refReference_sys.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=173; i < 266; i++) {
            curRow = sheet.getRow(i);
            refRegion.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        for (int i=269; i < 316; i++) {
            curRow = sheet.getRow(i);
            String s = curRow.getCell(1).getRichStringCellValue().getString().replaceAll(" ", "");
            refScale.put((int)curRow.getCell(0).getNumericCellValue(), s);
        }

        for (int i=319; i < 324; i++) {
            curRow = sheet.getRow(i);
            refSecret_class.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

//        for (int i=315; i < 423; i++) {
//            curRow = sheet.getRow(i);
//            refStorage_format.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
//        }

        for (int i=438; i < 560; i++) {
            curRow = sheet.getRow(i);
            int id_group = (int) curRow.getCell(4).getNumericCellValue();
            if (refSubtype[id_group - 1] == null)
                refSubtype[id_group - 1] = new HashMap<>();
            refSubtype[id_group - 1].put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(2).getRichStringCellValue().getString());
        }

        for (int i=563; i < 688; i++){
            curRow = sheet.getRow(i);
            int id_group = curRow.getCell(2).getCellType() == CellType.NUMERIC ?
                    (int)curRow.getCell(2).getNumericCellValue() :
                    Integer.parseInt(curRow.getCell(2).getRichStringCellValue().getString());
            if (refStorage[id_group - 1] == null)
                refStorage[id_group - 1] = new HashMap<>();
            refStorage[id_group - 1].put((int)curRow.getCell(0).getNumericCellValue(), (int)curRow.getCell(1).getNumericCellValue());
        }

        for (int i=691; i < 736; i++) {
            curRow = sheet.getRow(i);
            refStorage_meanings.put((int)curRow.getCell(0).getNumericCellValue(), curRow.getCell(1).getRichStringCellValue().getString());
        }

        workbook.close();
        file.close();
//        System.out.println("refAccess_condition");
//        printMap(refAccess_condition);
//        System.out.println("refCounetparty");
//        printMap(refCounetparty);
//        System.out.println("refGroups");
//        printMap(refGroups);
//        System.out.println("refHeight_sys");
//        printMap(refHeight_sys);
//        System.out.println("refReference_sys");
//        printMap(refReference_sys);
//        System.out.println("refRegion");
//        printMap(refRegion);
//        System.out.println("refScale");
//        printMap(refScale);
//        System.out.println("refSecret_class");
//        printMap(refSecret_class);
//        System.out.println("refStorage_meanings");
//        printMap(refStorage_meanings);
//        System.out.println("refSubtype");
//        for (Map<Integer, String> group : refSubtype) {
//            printMap(group);
//        }
//        System.out.println("refStorage");
//        for (Map<Integer, Integer> group : refStorage) {
//            System.out.println("group = " + group);
//            printMapInteger(group);
//        }
    }

    public static int getScaleCode(String s)
    {
        return refScale.entrySet().stream()
                .filter(entry -> s.equals(entry.getValue()))
                .findFirst().map(Map.Entry::getKey)
                .orElse(-1);
    }

    public static int getSubtypeCode(String s, int id_group)
    {
        s = s.toLowerCase();
        String finalS = s;
        if (s.equals("сведения о пунктах опорной геодезической сети москвы") ||
                s.contains("координаты пунктов огс москвы") ||
                s.contains("высоты пунктов огс москвы")){
            return 46;
        }
        if (s.contains("фотоплан")){
            return 69;
        }
        if (s.contains("аэросъемки")){
            return 72;
        }
        if (s.contains("план топографический") ||
                s.contains("цифровой картографический фон")){
            return 2;
        }
        if (s.contains("сведения о деформациях земной поверхности")){
            return 58;
        }
        if (s.equals("материалы по установлению местной системы координат города москвы")){
            return 124;
        }
        int result = refSubtype[id_group].entrySet().stream()
                    .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                    .findFirst().map(Map.Entry::getKey)
                    .orElse(-1);

        if (result == -1 && id_group == 1){
            result = 17;
        }
        return result;
    }

    public static int getSecretClassCode(String s)
    {
        if (s.equals("Сведения, составляющие охраняемую законом тайну, отсутствуют")){
            return 1;
        }
        if (s.equals("Государственная тайна")) {
            return 3;
        }
        if (s.trim().equals("Служебная тайна") || s.trim().equals("ДСП")){
            return 2;
        }
        s = s.toLowerCase();
        String finalS = s;
        return refSecret_class.entrySet().stream()
                .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                .findFirst().map(Map.Entry::getKey)
                .orElse(-1);
    }

    public static int getReferenceSystemCode(String s)
    {
        if (s.equals("МСК Москвы")) {
            return 12;
        }
        if (s.equals("СК-95")){
            return 9;
        }
        s = s.toLowerCase();
        String finalS = s;
        return refReference_sys.entrySet().stream()
                .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                .findFirst().map(Map.Entry::getKey)
                .orElse(-1);
    }

    public static int getCounetpartyCode(String s)
    {
        if (s.contains("Мосгоргеотрест"))
            return 4;
        s = s.toLowerCase();
        String finalS = s;
        return refCounetparty.entrySet().stream()
                .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                .findFirst().map(Map.Entry::getKey)
                .orElse(0);
    }

    public static int getAccessConditionCode(String s)
    {
        if (s.contains("от 4 марта 2017 г. № 262"))
            return 2;
        s = s.toLowerCase();
        String finalS = s;
        return refAccess_condition.entrySet().stream()
                .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                .findFirst().map(Map.Entry::getKey)
                .orElse(-1);

    }

    public static Integer getStorageFormat(String s, int id_group)
    {
        Integer code = -1;
        Integer res = -1;
        for (String f : refStorage_meanings.values()) {
            if (s.contains(f)) {
                Integer cur = getOneFormatKey(f);
                if (cur != -1) {
                    code = cur;
                    break;
                }

            } else if (f.toUpperCase().equals("MIF/MID") && s.toUpperCase().contains("MID/MIF")){
                Integer cur = getOneFormatKey("MIF/MID");
                if (cur != -1) {
                    code = cur;
                    break;
                }

            } else if (f.equals("Цифровой в форме БД") && (s.toLowerCase().equals("цифровой в форме бд") ||
                       s.toLowerCase().equals("цифровой в форме базы данных") ||
                       s.toLowerCase().equals("цифровой в формате бд") ||
                       s.toLowerCase().equals("цифровой в формате базы данных"))){
                Integer cur = getOneFormatKey("Цифровой в форме БД");
                if (cur != -1) {
                    code = cur;
                    break;
                }
            }
        }

        return code;
//        final Integer finalCode = code;
//        return refStorage[id_group].entrySet().stream()
//                .filter(entry -> finalCode.equals(entry.getValue()))
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(-1);
    }

    private static Integer getOneFormatKey(String value)
    {
        value = value.toLowerCase();
        String finalS = value;
        return refStorage_meanings.entrySet().stream()
                .filter(entry -> finalS.indexOf(entry.getValue().toLowerCase()) > -1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }
}
