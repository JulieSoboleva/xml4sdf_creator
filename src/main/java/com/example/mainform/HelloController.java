package com.example.mainform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.mainform.Parser.*;

public class HelloController
{
    ObservableList<String> formatList = FXCollections.observableArrayList(
            "amapExternal", "dmapExternal", "ERSExternal", "GeodesyExternal", "NsiExternal");
    ObservableList<String> scales;
    ObservableList<String> referenceSystems;
    ObservableList<String> storageFormats;
    ObservableList<String> secretClasses;
    ObservableList<String> creators;
    ObservableList<String> accessConditions;
    ObservableList<String> subtypes;
    ObservableList<String> locations;

    List<String> amapList = List.of("Атлас перспективных трасс метрополитена на территории города Москвы",
        "Гидрогеологическая карта 1:25000 Новая Москва",
        "Гидрогеологическая карта 1:50000",
        "Геологическая карта дочетвертичных отложений  1:10000",
        "Геологическая карта дочетвертичных отложений 1:50000",
        "Геологическая карта дочетвертичных отложений. Масштаб 1:10 000",
        "Геологическая карта каменоугольных отложений  1:10000",
        "Геологическая карта каменноугольных отложений 1:50000",
        "Геологическая карта каменноугольных отложений. Масштаб 1:10 000",
        "Геологическая карта четвертичных отложений  1:10000",
        "Геологическая карта четвертичных отложений 1:50000",
        "Геологическая карта четвертичных отложений. Масштаб 1:10 000",
        "Гидрологическая карта  1:10000",
        "Карта гидрологическая в маштабе 1:10000",
        "Карта инженерно-геологического районирования 1:10000",
        "Карта карстово-суффозионных процессов в маштабе 1:10000",
        "Карта карстовой и карстово-суффозионной опасности  1:10000",
        "Карта опасности древних карстовых форм и современных карстово-суффозионных процессов.  Масштаб 1:10 000",
        "Карта опасности оползневых явлений и подтопления  1:10000,  в том числе инженерно-геологические разрезы",
        "Карта оползневых процессов и подтопления подземными водами 1:50000",
        "Карта оползневых явлений и подтопления подземными водами 1:10000",
        "Карта районирования по условиям взаимосвязи водоносных горизонтов с элементами защищенности подольско-мячковского водоносного горизонта 1:10000",
        "Карта районирования по условиям взаимосвязи водоносных горизонтов с элементами защищенности подольско-мячковского водоносного горизонта. Масштаб 1:10 000",
        "Карта распространения геологических процессов и явлений 1:25000 Новая Москва",
        "Карта сейсмического микрорайонирования Москвы и ближнего Подмосковья 1:50000",
        "Карта техногенных образований. Масштаб 1:10 000",
        "Карта техногенных отложений  1:10000",
        "Карта техногенных отложений 1:50000",
        "Карта фактического материала",
        "Карта фактического материала 1:25000 Новая Москва",
        "Карта фактического материала 1:50000",
        "Карта фактического материала в масштабе 1:10000",
        "Карта фактического материала геологической съемки  маштаба 1:10000",
        "Карта экологическая в маштабе 1:10000",
        "Структурно-геодинамическая карта 1:25000",
        "Технический отчет \"Геологический атлас Москвы(в 10 томах) Масштаб 1:10000. Пояснительная записка\""
    );

    List<String> dmapList = List.of("План топографический масштаба 1:2000",
            "План топографический масштаба 1:10000",
            "План топографический масштаба 1:25000",
            "План топографический специальный масштаба 1:10000",
            "Цифровой картографический фон  масштаба 1:10000 ЕГКО Москвы",
            "Цифровой план города Москвы открытого пользования  масштаба 1:10000 в системе координат СК-95",
            "Цифровой план города Москвы открытого пользования  масштаба 1:10000 в системе координат МСК Москвы",
            "Цифровой план города Москвы открытого пользования  масштаба 1:10000 в системе координат SK-95 Москвы"
    );

    List<String> ersList = List.of("Материалы аэросъемки",
            "Материалы аэросъемки (NIR)",
            "Материалы аэросъемки (RGB)",
            "Ортофотоплан масшатаба 1:2000 (NIR)",
            "Ортофотоплан масштаба 1:2000 (RGB)",
            "Цифровой цветной ИК фотоплан масштаба 1:25000",
            "Цифровой цветной ортофотоплан масштаба 1 :2000",
            "Цифровой цветной ортофотоплан масштаба 1:2000 (ИК-диапазон)",
            "Цифровой цветной фотоплан масштаба 1:10000",
            "Цифровой цветной фотоплан масшатаба  1:25000",
            "Цифровой черно-белый фотоплан масштаба 1:10000"
    );

    List<String> geodesyList = List.of(
            "Высоты пунктов ОГС Москвы",
            "Координаты пунктов ОГС Москвы",
            "Сведения о деформациях земной поверхности",
            "Сведения о пунктах опорной геодезической сети Москвы"
    );

    List<String> nsiList = List.of(
            "Материалы по установлению местной системы координат города Москвы",
            "Технический отчет"
    );

    @FXML
    private Button bContinue;

    @FXML
    private Button bGetXslx;

    @FXML
    private Button bMakeXml;

    @FXML
    private ComboBox<String> cbAccessCondition;

    @FXML
    private ComboBox<String> cbCreator;

    @FXML
    private ComboBox<String> cbLocation;

    @FXML
    private ComboBox<String> cbReferenceSystem;

    @FXML
    private TextField tfRightHolder;

    @FXML
    private ComboBox<String> cbScale;

    @FXML
    private ComboBox<String> cbSecretClass;

    @FXML
    private ComboBox<String> cbStorageFormat;

    @FXML
    private ComboBox<String> cbSubtype;

    @FXML
    private ChoiceBox<String> chbSelectXmlFormats;

    @FXML
    private DatePicker dpObjectCreateDate;

    @FXML
    private ProgressBar pbTemp;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField tfAreaStateDate;

    @FXML
    private TextField tfExtraRegionInfo;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfObjectQuantity;

    @FXML
    private CheckBox xAccessCondition;

    @FXML
    private CheckBox xAreaStateDate;

    @FXML
    private CheckBox xCreator;

    @FXML
    private CheckBox xExtraRegionInfo;

    @FXML
    private CheckBox xLocation;

    @FXML
    private CheckBox xName;

    @FXML
    private CheckBox xObjectCreateDate;

    @FXML
    private CheckBox xObjectQuantity;

    @FXML
    private CheckBox xReferenceSystem;

    @FXML
    private CheckBox xRightHolder;

    @FXML
    private CheckBox xScale;

    @FXML
    private CheckBox xSecretClass;

    @FXML
    private CheckBox xStorageFormat;

    @FXML
    private CheckBox xSubtype;

    @FXML
    void getFileXlsx(ActionEvent event) throws IOException
    {
        File fileXslx = fileChooser.showOpenDialog(null);
        if (fileXslx != null)
        {
            Parser.parseXlsFile(fileXslx.toPath().toString());
        }
    }

    private FileChooser fileChooser = new FileChooser();

    @FXML
    private void initialize() throws IOException
    {
        chbSelectXmlFormats.setItems(formatList);
        chbSelectXmlFormats.setValue("Форматы XML-файлов");
        //chbSelectXmlFormats.setSelectionModel(SelectionMode.MULTIPLE);
        chbSelectXmlFormats.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if ((int)t1 == 0)   // amapExternal
                        {
                            xName.setSelected(true);
                            xScale.setSelected(true);
                            xExtraRegionInfo.setSelected(true);
                            xReferenceSystem.setSelected(true);
                            xStorageFormat.setSelected(true);
                            xSecretClass.setSelected(true);
                            xCreator.setSelected(true);
                            xRightHolder.setSelected(true);
                            xAreaStateDate.setSelected(true);
                            xAccessCondition.setSelected(true);
                            xObjectCreateDate.setSelected(true);
                            xSubtype.setSelected(true);
                            xObjectQuantity.setSelected(true);
                            xLocation.setSelected(true);
                        }
                        else if ((int)t1 == 1)  //dmapExternal
                        {
                            xName.setSelected(true);
                            xScale.setSelected(true);
                            xExtraRegionInfo.setSelected(true);
                            xReferenceSystem.setSelected(true);
                            xStorageFormat.setSelected(true);
                            xSecretClass.setSelected(true);
                            xCreator.setSelected(true);
                            xRightHolder.setSelected(true);
                            xAreaStateDate.setSelected(true);
                            xAccessCondition.setSelected(true);
                            xObjectCreateDate.setSelected(true);
                            xSubtype.setSelected(true);
                            xObjectQuantity.setSelected(true);
                            xLocation.setSelected(true);
                        }
                        else if ((int)t1 == 2)  //ERSExternal
                        {
                            xName.setSelected(true);
                            xScale.setSelected(true);
                            xExtraRegionInfo.setSelected(true);
                            xReferenceSystem.setSelected(true);
                            xStorageFormat.setSelected(true);
                            xSecretClass.setSelected(true);
                            xCreator.setSelected(true);
                            xRightHolder.setSelected(true);
                            xAreaStateDate.setSelected(true);
                            xAccessCondition.setSelected(true);
                            xObjectCreateDate.setSelected(true);
                            xSubtype.setSelected(true);
                            xObjectQuantity.setSelected(true);
                            xLocation.setSelected(false);
                        }
                        else if ((int)t1 == 3)  //GeodesyExternal
                        {
                            xName.setSelected(true);
                            xScale.setSelected(true);
                            xExtraRegionInfo.setSelected(true);
                            xReferenceSystem.setSelected(true);
                            xStorageFormat.setSelected(true);
                            xSecretClass.setSelected(true);
                            xCreator.setSelected(true);
                            xRightHolder.setSelected(true);
                            xAreaStateDate.setSelected(false);
                            xAccessCondition.setSelected(true);
                            xObjectCreateDate.setSelected(true);
                            xSubtype.setSelected(true);
                            xObjectQuantity.setSelected(false);
                            xLocation.setSelected(true);
                        }
                        else if ((int)t1 == 4)  //NsiExternal
                        {
                            xName.setSelected(true);
                            xScale.setSelected(true);
                            xExtraRegionInfo.setSelected(true);
                            xReferenceSystem.setSelected(true);
                            xStorageFormat.setSelected(true);
                            xSecretClass.setSelected(true);
                            xCreator.setSelected(true);
                            xRightHolder.setSelected(true);
                            xAreaStateDate.setSelected(true);
                            xAccessCondition.setSelected(true);
                            xObjectCreateDate.setSelected(true);
                            xSubtype.setSelected(true);
                            xObjectQuantity.setSelected(false);
                            xLocation.setSelected(true);
                        }
                    }
                });

        parseReferences("B:\\__JAVA\\SDF to XML\\Справочники (1).xlsx");
        scales = FXCollections.observableArrayList(refScale.values().stream().toList());
        cbScale.setItems(scales);
        cbScale.setEditable(false);

        referenceSystems = FXCollections.observableArrayList(refReference_sys.values().stream().toList());
        cbReferenceSystem.setItems(referenceSystems);

        storageFormats = FXCollections.observableArrayList(refStorage_meanings.values().stream().toList());
        cbStorageFormat.setItems(storageFormats);

        secretClasses = FXCollections.observableArrayList(refSecret_class.values().stream().toList());
        cbSecretClass.setItems(secretClasses);

        creators = FXCollections.observableArrayList(refCounetparty.values().stream().toList());
        cbCreator.setItems(creators);

        tfRightHolder.setText("Фондодержатель"); // PromptText

        accessConditions = FXCollections.observableArrayList(refAccess_condition.values().stream().toList());
        cbAccessCondition.setItems(accessConditions);

      //  subtypes = FXCollections.observableArrayList(refSubtype.values().stream().toList());
      //  cbSubtype.setItems(subtypes);

        locations = FXCollections.observableArrayList(refCounetparty.values().stream().toList());
        cbLocation.setItems(locations);
    }

    @FXML
    private void makeXMLfileClick(ActionEvent event)
    {
        String fileName = chbSelectXmlFormats.getValue();
        System.out.println(fileName);
        chbSelectXmlFormats.setDisable(true);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement = doc.createElement("ArrayOfAMapIntegrationXML");
            if (fileName == "dmapExternal") {
                rootElement = doc.createElement("ArrayOfDMapIntegrationXML");
            }
            else if (fileName == "ERSExternal") {
                rootElement = doc.createElement("ArrayOfERSIntegrationXML");
            }
            else if (fileName == "GeodesyExternal") {
                rootElement = doc.createElement("ArrayOfGeodesyIntegrationXML");
            }
            else if (fileName == "NsiExternal") {
                rootElement = doc.createElement("ArrayOfNsiIntegrationXML");
            }

            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");

            // добавляем дочерние элементы к корневому
            for (Doc iDoc : Parser.data) {
                Node curentNode = getIntegration(doc, iDoc, fileName);
                if (curentNode != null) {
                    rootElement.appendChild(curentNode);
                }
            }

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
       //     StreamResult console = new StreamResult(System.out);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyyy-MM-dd_HH-mm-ss");
            StreamResult file = new StreamResult(new File("B:\\__JAVA\\SDF to XML\\OUTS\\" + fileName + LocalDateTime.now().format(formatter) + ".xml"));

            //записываем данные
          //  transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }

        chbSelectXmlFormats.setDisable(false);
    }

    private Node getIntegration(Document doc, Doc item, String fileName)
    {
        Element integ = doc.createElement("AMapIntegrationXML");
        if (fileName == "dmapExternal") {
            integ = doc.createElement("DMapIntegrationXML");
        }
        else if (fileName == "ERSExternal") {
            integ = doc.createElement("ERSIntegrationXML");
        }
        else if (fileName == "GeodesyExternal") {
            integ = doc.createElement("GeodesyIntegrationXML");
        }
        else if (fileName == "NsiExternal") {
            integ = doc.createElement("NsiIntegrationXML");
        }
        // создаем элементы
        switch (fileName)
        {
            case "dmapExternal":
                return workFileDmap(integ, doc, item);
            case "amapExternal":
                return workFileAmap(integ, doc, item);
            case "ERSExternal":
                return workFileERS(integ, doc, item);
            case "GeodesyExternal":
                return workFileGeodesy(integ, doc, item);
            case "NsiExternal":
                return workFileNsi(integ, doc, item);
            default:
                return integ;
        }
    }

    private Node addRegionRef(Document doc, String name)
    {
        Element node = doc.createElement(name);
        Node n = node.appendChild(doc.createElement("int"));
        n.appendChild(doc.createTextNode("45"));
        n = node.appendChild(doc.createElement("int"));
        n.appendChild(doc.createTextNode("201"));
        return node;
    }

    private Node workFileDmap(Element integ, Document doc, Doc item)
    {
        String name = item.getName().trim();
        if (!dmapList.contains(name))
            return null;

        int code = Parser.getSubtypeCode(name, 0);
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "subtype_ref", String.valueOf(code)));

        code = Parser.getSecretClassCode(item.getSecretClass());
        integ.appendChild(getIntegrationElements(doc, "secretclass_ref", String.valueOf(code)));
        integ.appendChild(getIntegrationElements(doc, "name", name));
        integ.appendChild(getIntegrationElements(doc, "nomenclature", name));

        code = Parser.getReferenceSystemCode(item.getReferenceSystem());
        integ.appendChild(getIntegrationElements(doc, "referencesystem_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "extraregioninfo", item.getExtraRegionInfo()));

        code = Parser.getCounetpartyCode(item.getCreator()); //getRightHolder());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref",null));

        code = Parser.getCounetpartyCode(item.getCreator());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "creator_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "creator_ref",null));

        code = Parser.getAccessConditionCode(item.getAccessCondition());
        integ.appendChild(getIntegrationElements(doc, "accesscondition_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "scale", String.valueOf(Parser.getScaleCode(item.getScale()))));

        code = Parser.getCounetpartyCode(item.getCreator()); //getLocation());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "location_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "location_ref",null));

        integ.appendChild(getIntegrationElements(doc, "storageformat_ref", String.valueOf(Parser.getStorageFormat(item.getStorageFormat(), 0))));
                //getStringOfKey(Parser.getStorageFormat(item.getStorageFormat()))));

        integ.appendChild(getIntegrationElements(doc, "areastatedate", item.getAreaStateDate()));
        integ.appendChild(getIntegrationElements(doc, "objectcreatedat", item.getObjectCreateDate()));
        integ.appendChild(getIntegrationElements(doc, "comment", item.getSubtype() + '\n' + item.getComments()));
        integ.appendChild(getIntegrationElements(doc, "objectquantity", item.getObjectQuantity()));


        integ.appendChild(addRegionRef(doc, "regions_ref"));
        return integ;
    }

    private Node workFileAmap(Element integ, Document doc, Doc item)
    {
        String name = item.getName().trim();
        if (!amapList.contains(name))
            return null;

        int code = Parser.getSubtypeCode(name, 1);
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "subtype_ref", String.valueOf(code)));

        code = Parser.getSecretClassCode(item.getSecretClass());
        integ.appendChild(getIntegrationElements(doc, "secretclass_ref", String.valueOf(code)));
        integ.appendChild(getIntegrationElements(doc, "name", name));
        integ.appendChild(getIntegrationElements(doc, "nomenclature", name));

        code = Parser.getReferenceSystemCode(item.getReferenceSystem());
        integ.appendChild(getIntegrationElements(doc, "referencesystem_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "extraregioninfo", item.getExtraRegionInfo()));

        code = Parser.getCounetpartyCode(item.getCreator()); //getRightHolder());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref",null));

        code = Parser.getCounetpartyCode(item.getCreator());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "creator_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "creator_ref",null));

        code = Parser.getAccessConditionCode(item.getAccessCondition());
        integ.appendChild(getIntegrationElements(doc, "accesscondition_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "scale", String.valueOf(Parser.getScaleCode(item.getScale()))));

        code = Parser.getCounetpartyCode(item.getCreator()); //getLocation());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "location_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "location_ref",null));

        integ.appendChild(getIntegrationElements(doc, "storageformat_ref", String.valueOf(Parser.getStorageFormat(item.getStorageFormat(), 1))));
        //integ.appendChild(getIntegrationElements(doc, "storageformat_ref", getStringOfKey(Parser.getStorageFormat(item.getStorageFormat()))));

        integ.appendChild(getIntegrationElements(doc, "areastatedate", item.getAreaStateDate()));
        integ.appendChild(getIntegrationElements(doc, "objectcreatedat", item.getObjectCreateDate()));

//        integ.appendChild(getIntegrationElements(doc, "objectchangedat", null));
//        integ.appendChild(getIntegrationElements(doc, "minscale", null));
//        integ.appendChild(getIntegrationElements(doc, "maxareastatedate", null));
        integ.appendChild(getIntegrationElements(doc, "comment", item.getSubtype() + '\n' + item.getComments()));
        integ.appendChild(getIntegrationElements(doc, "objectquantity", item.getObjectQuantity()));
//        integ.appendChild(getIntegrationElements(doc, "incomingdoc", null));
//        integ.appendChild(getIntegrationElements(doc, "outgoingdoc", null));
//        integ.appendChild(getIntegrationElements(doc, "wgscoordinates", null));

        integ.appendChild(addRegionRef(doc, "regions_ref"));
        return integ;
    }

    private Node workFileERS(Element integ, Document doc, Doc item)
    {
        String name = item.getName().trim();
        if (!ersList.contains(name))
            return null;

        int code = Parser.getSubtypeCode(name, 2);
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "subtype_ref", String.valueOf(code)));

        code = Parser.getSecretClassCode(item.getSecretClass());
        integ.appendChild(getIntegrationElements(doc, "secretclass_ref", String.valueOf(code)));
        integ.appendChild(getIntegrationElements(doc, "name", name));
        integ.appendChild(getIntegrationElements(doc, "nomenclature", name));

        code = Parser.getReferenceSystemCode(item.getReferenceSystem());
        integ.appendChild(getIntegrationElements(doc, "referencesystem_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "extraregioninfo", item.getExtraRegionInfo()));

        code = Parser.getCounetpartyCode(item.getCreator()); //.getRightHolder());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref",null));

        code = Parser.getCounetpartyCode(item.getCreator());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "creator_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "creator_ref",null));

        code = Parser.getAccessConditionCode(item.getAccessCondition());
        integ.appendChild(getIntegrationElements(doc, "accesscondition_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "scale", String.valueOf(Parser.getScaleCode(item.getScale()))));

        code = Parser.getCounetpartyCode(item.getCreator()); //.getLocation());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "location_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "location_ref",null));

        integ.appendChild(getIntegrationElements(doc, "storageformat_ref", String.valueOf(Parser.getStorageFormat(item.getStorageFormat(), 2))));
        //integ.appendChild(getIntegrationElements(doc, "storageformat_ref", getStringOfKey(Parser.getStorageFormat(item.getStorageFormat()))));

        integ.appendChild(getIntegrationElements(doc, "areastatedate", item.getAreaStateDate()));
        integ.appendChild(getIntegrationElements(doc, "objectcreatedat", item.getObjectCreateDate()));
        integ.appendChild(getIntegrationElements(doc, "comment", item.getSubtype() + '\n' + item.getComments()));
        integ.appendChild(getIntegrationElements(doc, "objectquantity", item.getObjectQuantity()));

//        integ.appendChild(getIntegrationElements(doc, "objectchangedat", null));
//        integ.appendChild(getIntegrationElements(doc, "minscale", null));
//        integ.appendChild(getIntegrationElements(doc, "maxareastatedate", null));
//        integ.appendChild(getIntegrationElements(doc, "incomingdoc", null));
//        integ.appendChild(getIntegrationElements(doc, "outgoingdoc", null));
//        integ.appendChild(getIntegrationElements(doc, "wgscoordinates", null));

        integ.appendChild(addRegionRef(doc, "regions_ref"));
        return integ;
    }

    private Node workFileGeodesy(Element integ, Document doc, Doc item)
    {
        String name = item.getName().trim();
        if (!geodesyList.contains(name))
            return null;

        int code = Parser.getSubtypeCode(name, 4);
        //if (code != 0)
        integ.appendChild(getIntegrationElements(doc, "subtype_ref", String.valueOf(code)));

        code = Parser.getSecretClassCode(item.getSecretClass());
        integ.appendChild(getIntegrationElements(doc, "secretclass_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "name", name));
        integ.appendChild(getIntegrationElements(doc, "nomenclature", name));

        code = Parser.getReferenceSystemCode(item.getReferenceSystem());
        integ.appendChild(getIntegrationElements(doc, "referencesystem_ref", String.valueOf(code)));
//        integ.appendChild(getIntegrationElements(doc, "heightsystem_ref", null));
        integ.appendChild(getIntegrationElements(doc, "extraregioninfo", item.getExtraRegionInfo()));

        code = Parser.getCounetpartyCode(item.getCreator()); //.getRightHolder());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref",null));

        code = Parser.getCounetpartyCode(item.getCreator());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "creator_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "creator_ref",null));

        code = Parser.getAccessConditionCode(item.getAccessCondition());
        integ.appendChild(getIntegrationElements(doc, "accesscondition_ref", String.valueOf(code)));
        // integ.appendChild(getIntegrationElements(doc, "scale", String.valueOf(Parser.getScaleCode(item.getScale()))));

        code = Parser.getCounetpartyCode(item.getCreator()); //.getLocation());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "location_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "location_ref",null));

        integ.appendChild(getIntegrationElements(doc, "storageformat_ref", String.valueOf(Parser.getStorageFormat(item.getStorageFormat(), 4))));
        //integ.appendChild(getIntegrationElements(doc, "storageformat_ref", getStringOfKey(Parser.getStorageFormat(item.getStorageFormat()))));

//        integ.appendChild(getIntegrationElements(doc, "minscale", null));
//        integ.appendChild(getIntegrationElements(doc, "wgscoordinates", null));
        integ.appendChild(getIntegrationElements(doc, "areastatedate", item.getAreaStateDate()));
        integ.appendChild(getIntegrationElements(doc, "objectcreatedat", item.getObjectCreateDate()));
        integ.appendChild(getIntegrationElements(doc, "comment", item.getSubtype() + '\n' + item.getComments()));
        integ.appendChild(getIntegrationElements(doc, "objectquantity", item.getObjectQuantity()));

        integ.appendChild(addRegionRef(doc, "regions_ref"));
        return integ;
    }

    private Node workFileNsi(Element integ, Document doc, Doc item)
    {
        String name = item.getName().trim();
        if (!nsiList.contains(name))
            return null;

        int code = Parser.getSubtypeCode(name, 11);
        //if (code != -1)
        integ.appendChild(getIntegrationElements(doc, "subtype_ref", String.valueOf(code)));

        code = Parser.getSecretClassCode(item.getSecretClass());
        integ.appendChild(getIntegrationElements(doc, "secretclass_ref", String.valueOf(code)));
        integ.appendChild(getIntegrationElements(doc, "name", name));
        //nomenclature

        code = Parser.getReferenceSystemCode(item.getReferenceSystem());
        integ.appendChild(getIntegrationElements(doc, "referencesystem_ref", String.valueOf(code)));

        integ.appendChild(getIntegrationElements(doc, "extraregioninfo", item.getExtraRegionInfo()));

        code = Parser.getCounetpartyCode(item.getCreator()); //.getRightHolder());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "rightholder_ref",null));

        code = Parser.getCounetpartyCode(item.getCreator());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "creator_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "creator_ref",null));

        code = Parser.getAccessConditionCode(item.getAccessCondition());
        integ.appendChild(getIntegrationElements(doc, "accesscondition_ref", String.valueOf(code)));
       // integ.appendChild(getIntegrationElements(doc, "scale", String.valueOf(Parser.getScaleCode(item.getScale()))));
        //integ.appendChild(getIntegrationElements(doc, "minscale", null));
        code = Parser.getCounetpartyCode(item.getCreator()); //.getLocation());
        if (code != 0)
            integ.appendChild(getIntegrationElements(doc, "location_ref", String.valueOf(code)));
        else
            integ.appendChild(getIntegrationElements(doc, "location_ref",null));

        integ.appendChild(getIntegrationElements(doc, "storageformat_ref", String.valueOf(Parser.getStorageFormat(item.getStorageFormat(), 11))));
      //integ.appendChild(getIntegrationElements(doc, "storageformat_ref", getStringOfKey(Parser.getStorageFormat(item.getStorageFormat()))));
        integ.appendChild(getIntegrationElements(doc, "areastatedate", item.getAreaStateDate()));
        integ.appendChild(getIntegrationElements(doc, "objectcreatedat", item.getObjectCreateDate()));
        integ.appendChild(getIntegrationElements(doc, "comment", item.getSubtype() + '\n' + item.getComments()));
        integ.appendChild(getIntegrationElements(doc, "objectquantity", item.getObjectQuantity()));
        //integ.appendChild(getIntegrationElements(doc, "wgscoordinates", null));

        integ.appendChild(addRegionRef(doc, "regions_ref"));
        return integ;
    }

    // утилитный метод для создание нового узла XML-файла
    private static Node getIntegrationElements(Document doc, String name, String value)
    {
        Element node = doc.createElement(name);
        if (value != null) {
            node.appendChild(doc.createTextNode(value));
        }
        else {
            node.appendChild(doc.createComment("нет данных"));
        }
        return node;
    }

    private static String getStringOfKey(ArrayList<Integer> keies)
    {
        String res = "";
        for (int i = 0; i < keies.size(); i++) {
            res += keies.get(i);
            if (i < keies.size()-1)
                res += ',';
        }
        return res;
    }
}