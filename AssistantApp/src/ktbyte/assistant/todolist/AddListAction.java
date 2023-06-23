package ktbyte.assistant.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class AddListAction extends Action{
	String[] keywords = {"list", "todo", "to", "the", "add"};
	double[] scores =   {4 , 4, 0.2, 0.2, 4};
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		//ToDoListApp.myList
		List<String> words = Arrays.asList(command.split(" "));

		if(words.contains("add")) {
			String temp = words.get(1);
			for(int i = 2; i < words.size()-2; i++) {
				if(words.get(i).equals("to") && words.get(i+1).contentEquals("the") && words.get(i+2).contentEquals("list") || words.get(i+2).equals("todolist")) {
					break;
				}else {
					
					temp = temp + " " + words.get(i);
				}

			}
			ToDoListApp.theList.add(temp);
			assistant.displayItem(new Response("Added " + temp + " to the list"));
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
