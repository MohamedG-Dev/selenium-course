package selenium.learning.framework.testComponents;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JSONDataReader {

	public List<HashMap<String, String>> getJsonDataToMap() {
		String jsonContent;
		List<HashMap<String, String>> data = null;
		try {
			jsonContent = FileUtils.readFileToString(
					new File(System.getProperty("user.dir") + "/src/test/resources/purchaseOrder.json"));
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
