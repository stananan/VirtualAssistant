package ktbyte.assistant.time;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;
import ktbyte.assistant.app.Response;

public class GetTimeAction extends Action{
	String[] keywords = {"time", "what", "is", "the"};
	double[] scores =   {4 , 0.2, 0.2, 0.2};
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		String result = LocalTime.now().toString();
		
		assistant.displayItem(new Response("Local time:" + result));
	}
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
}
