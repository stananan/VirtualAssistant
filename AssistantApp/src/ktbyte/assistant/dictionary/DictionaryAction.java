package ktbyte.assistant.dictionary;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class DictionaryAction extends Action{
	String[] keywords = { "definition", "speech", "what", "is", "the", "of" };
	double[] scores =   { 3,           3, 0.2,    0.2,  0.2, 0.2 };

	@Override
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		List<String> words = Arrays.asList(command.split(" "));
		HttpRequest req = null;
		String location = words.get(words.size() - 1);
		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals("of")) {
				location = words.get(i+1);
			}
		}
		String url = ("https://api.dictionaryapi.dev/api/v2/entries/en/" + location);

		req = Unirest.get(url);
		System.out.println(req.getUrl());
		try {
		  HttpResponse<JsonNode> boom = req.asJson();
		  System.out.println(boom);
			JsonNode node = boom.getBody();
			System.out.println(node);
			
			defResult(node);
			
			
		} catch (UnirestException e) {
			System.out.println("request error occurred: " + e);
		}
	}

	@Override
	public double getLikelihood(String command) { 
		double score = 0;
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			if (command.contains(keyword)) {
				score += scores[i];
			}
		}
		return score;
	}
	
	private static void defResult(JsonNode node) {
		  
		  Assistant assistant = Assistant.getInstance();
			JSONArray json = node.getArray();
			String word = json.optJSONObject(0).getString("word");
			String def = json.optJSONObject(0).optJSONArray("meanings").optJSONObject(0).optJSONArray("definitions").optJSONObject(0).getString("definition");
			String pos = json.optJSONObject(0).optJSONArray("meanings").optJSONObject(0).getString("partOfSpeech");

			System.out.println("def");
			assistant.displayItem(new Response("POS: "+ pos +". Definition: " + def ));
	}
	
	

}

