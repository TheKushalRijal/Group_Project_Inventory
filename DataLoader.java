import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<RecordItem> loadRecords(String filePath) {
        List<RecordItem> records = new ArrayList<>();

        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath))).trim();

            if (json.startsWith("[")) json = json.substring(1);
            if (json.endsWith("]")) json = json.substring(0, json.length() - 1);

            String[] objects = json.split("\\},\\s*\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();
                String[] pairs = obj.split(",");

                String date = "", product = "", type = "";
                String quantity = "", status = "";

                for (String pair : pairs) {
                    String[] kv = pair.split(":", 2);

                    if (kv.length < 2) continue;

                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();

                    switch (key) {
                        case "date": date = value; break;
                        case "product": product = value; break;
                        case "type": type = value; break;
                        case "quantity": quantity = value; break;
                        case "status": status = value; break;
                    }
                }

                records.add(new RecordItem(date, product, type, quantity, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
