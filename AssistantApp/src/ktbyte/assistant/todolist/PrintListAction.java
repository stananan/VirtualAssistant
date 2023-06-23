package ktbyte.assistant.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class PrintListAction extends Action{
	String[] keywords = {"list", "todo", "the", "print"};
	double[] scores =   {4 , 4, 0.2, 4};
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		//ToDoListApp.myList
		List<String> words = Arrays.asList(command.split(" "));

		if(words.contains("print")) {
			assistant.displayItem(new Response(ToDoListApp.theList.toString()));
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

}
