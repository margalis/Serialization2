import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import json_xmlTests.Car;
import json_xmlTests.Human;
import json_xmlTests.Phones;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final String XML_SERIALIZED_FILE_NAME = "src/test/resources/human.xml";

    public static void main(String[] args) throws IOException {
        List<Phones> phones = new ArrayList<>();
        phones.add(new Phones("096857496", "AM"));
        phones.add(new Phones("098857496", "AM"));
        Car car = new Car("Honda", 2016);
        Human h = new Human("Mariam", 20);
        h.setCar(car);
        h.setPhones(phones);

        //gson serialization  // function
        // HumanToJson(h);

        //gson serialization for collections
        String ss = "{\"name\":\"Mariam\",\"car\":{\"model\":\"honda\",\"year\":2015},\"phones\":" +
                "[{\"number\":\"096857496\",\"country\":\"AM\"},{\"number\":\"098857496\",\"country\":\"AM\"}]}";

        //jsonToHuman(ss);

        //XML serialization  using XMLEncoder
        //  XmlEncodingHuman(h);

        //and XMLDecoder
        //  XmlDecodingHuman();

        //XML serialization using library Jackson-format
        //does not find the function,dont know what to do, this is the reason I decided to try with Maven dependencies
        //  ObjectToXmlJks(h);

        //writing in file
        //  ObjectToXmlFileJks(h,"jkshuman");

        //deserialize
        /*  Human h1 = XmlToObjectJks("<Human><name>Mariam</name><car><model>Honda</model>" +
                "<year>2016</year></car><phones><phones><number>096857496</number><country>AM</country></phones" +
                "><phones><number>098857496</number><country>AM</country></phones></phones></Human>");
        System.out.println(h1);*/

        //deserialize from file
        Human h2=XmlFileToObjectJks("src/test/resources/jkshuman.xml");
        System.out.println(h2);
    }

    static void jsonToHuman(String ss) {
        Gson g = new Gson();
        Human human = g.fromJson(ss, Human.class);
        System.out.println(human);
    }

    static void HumanToJson(Human h) {
        Gson g = new Gson();
        String s = g.toJson(h);
        System.out.println(s);
    }

    static void ObjectToXmlJks(Object h) {
        try {
            XmlMapper Mapper = new XmlMapper();
            String s = Mapper.writeValueAsString(h);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    static Human XmlToObjectJks(String xmlString) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Human human = xmlMapper.readValue(xmlString, Human.class);
        return human;
    }

    @AdditionalFunction
    static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    static Human XmlFileToObjectJks(String pathname) throws IOException {
        File file = new File(pathname);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Human human = xmlMapper.readValue(xml, Human.class);
        return human;
    }

    static void ObjectToXmlFileJks(Object h, String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("src/test/resources/" + filename + ".xml"), h);
        File file = new File("src/test/resources/" + filename + ".xml");
    }

    static void XmlEncodingHuman(Human h) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(XML_SERIALIZED_FILE_NAME)))) {
            encoder.writeObject(h);
            //if there is no getters/setters  AND noarg c-tor in classes, then this will not work
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void XmlDecodingHuman() {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(XML_SERIALIZED_FILE_NAME)))) {
            Human human = (Human) decoder.readObject();
            System.out.println(human);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File human.xml not found");
        }
    }
}
