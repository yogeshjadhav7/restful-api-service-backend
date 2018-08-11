package web.mlservice.intellecto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonObject;

import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.RobotGameBehaviour;
import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class RobotService {

	public final static String ML_SERVICE_HOST = "localhost:8000"; 
	
	public static boolean train(final long userId, List<Intellecto_Users_Behaviour> behaviourList) {
		
        HttpURLConnection client = null;
        StringBuilder response = new StringBuilder();
        
        try {
            URL url = new URL("http://" + ML_SERVICE_HOST + "/service/intellecto/train/");
            client = (HttpURLConnection) url.openConnection();
            client.setDoOutput(true);
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("POST");
            client.connect();

            
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", Long.toString(userId));
            jsonObject.addProperty("behaviour", JsonObjectMapper.toJsonString(behaviourList, false));
            String jsonRequest = jsonObject.toString();

            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(jsonRequest);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                response.append(line.trim());
                line = reader.readLine();
            }

            String responseStr = response.toString();
            responseStr = responseStr.substring(1, responseStr.length() - 1);
            responseStr = responseStr.replace("\\\"", "\"");
            return responseStr.equalsIgnoreCase(Boolean.toString(true));
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        finally {
        		client.disconnect();
		}
		
        
	}
	
	public static String predict(final long userId, RobotGameBehaviour robotGameBehaviour) {
		
        HttpURLConnection client = null;
        StringBuilder response = new StringBuilder();
        
        try {
            URL url = new URL("http://" + ML_SERVICE_HOST + "/service/intellecto/predict/");
            client = (HttpURLConnection) url.openConnection();
            client.setDoOutput(true);
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("POST");
            client.connect();

            
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", Long.toString(userId));
            jsonObject.addProperty("behaviour", JsonObjectMapper.toJsonString(robotGameBehaviour, false));
            String jsonRequest = jsonObject.toString();

            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(jsonRequest);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                response.append(line.trim());
                line = reader.readLine();
            }

            String responseStr = response.toString();
            responseStr = responseStr.substring(1, responseStr.length() - 1);
            responseStr = responseStr.replace("\\\"", "\"");
            return responseStr;
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        finally {
        		client.disconnect();
		}
		
	}
	
}
