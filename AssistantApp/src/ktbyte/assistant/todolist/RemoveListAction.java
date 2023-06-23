package ktbyte.assistant.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class RemoveListAction extends Action{
	String[] keywords = {"list", "todo", "from", "the", "remove"};
	double[] scores =   {4 , 4, 0.2, 0.2, 4};
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		//ToDoListApp.myList
		List<String> words = Arrays.asList(command.split(" "));

		if(words.contains("remove")) {
			String temp = words.get(1);
			
			for(int i = 2; i < words.size()-2; i++) {
				if(words.get(i).equals("from") && words.get(i+1).equals("the") && words.get(i+2).equals("list") || words.get(i+2).equals("todolist")) {
					break;
				}else {
					temp = temp + " " + words.get(i);
				}

			}
			
			
			if(ToDoListApp.theList.contains(temp)) {
				ToDoListApp.theList.remove(temp);
				assistant.displayItem(new Response("Removed "+ temp +" from the list"));
			}else {
				assistant.displayItem(new Response("Sorry, didn't find, " + temp +" as a thing in the list"));
			}	
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
