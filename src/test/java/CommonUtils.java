import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class CommonUtils {

    public static List<Map<String, String>> dataRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File resource = new File("F:\\RestAssured\\src\\test\\resources\\RCB.json");
        JsonNode node = mapper.readValue(new String(Files.readAllBytes(resource.toPath())), JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            if (entry.getKey().equals("player")) {
                String json = entry.getValue().toString();
                TypeReference<List<Map<String, String>>> typeReference = new TypeReference<List<Map<String, String>>>() {
                };
                return mapper.readValue(json, typeReference);
            }
        }


        return null;
    }

}
