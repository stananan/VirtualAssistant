package ktbyte.assistant.todolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;

public class ToDoListApp extends App{
	static ArrayList<String> theList = new ArrayList<>();
	public List<Action> getActions(){
		
		
		
		return Arrays.asList(new AddListAction(), new RemoveListAction(), new ClearListAction(), new PrintListAction());
		
	}
}
