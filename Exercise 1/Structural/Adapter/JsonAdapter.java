
package Structural.Adapter;

/**
 *
 * @author Ajithaa_Malini
 */
public class JsonAdapter {
    private XmlData xmlData;

    public JsonAdapter(XmlData xmlData) {
        this.xmlData = xmlData;
    }

    public String getJson() {
        // Conversion logic from XML to JSON
        String xml = xmlData.getXml();
        String json = "{ \"data\": { \"item\": \"Item1\" } }";
        return json;
    }
}
