interface JsonData {
    String getJson();
}

class XmlData {
    private String xml;
    public XmlData(String xml) { this.xml = xml; }
    public String getXml() { return xml; }
}

class XmlToJsonAdapter implements JsonData {
    private XmlData xmlData;

    public XmlToJsonAdapter(XmlData xmlData) {
        this.xmlData = xmlData;
    }

    @Override
    public String getJson() {
        System.out.println("Converting XML to JSON...");
        return "{ \"data\": \"" + xmlData.getXml().replaceAll("<[^>]*>", "") + "\" }";
    }
}

public class AnalyticsTool {
    public void processData(JsonData jsonData) {
        System.out.println("Processing JSON data: " + jsonData.getJson());
    }

    public static void main(String[] args) {
        XmlData oldData = new XmlData("<data>Hello World</data>");
        JsonData adapter = new XmlToJsonAdapter(oldData);

        AnalyticsTool tool = new AnalyticsTool();
        tool.processData(adapter);
    }
}