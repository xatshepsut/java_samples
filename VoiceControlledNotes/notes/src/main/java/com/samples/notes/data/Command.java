package com.samples.notes.data;

public class Command {

	public enum CommandType {
	    VIEW, EDIT, REMOVE, ADD, SELECT, SAVE, NONE
	}
	
	private static String VIEW_COMMAND_1 = "open"; 
	private static String VIEW_COMMAND_2 = "view";
	private static String EDIT_COMMAND_1 = "edit";
	private static String EDIT_COMMAND_2 = "modify";
	private static String EDIT_COMMAND_3 = "change";
	private static String REMOVE_COMMAND_1 = "delete";
	private static String REMOVE_COMMAND_2 = "remove";
	private static String ADD_COMMAND_1 = "add"; 
	private static String ADD_COMMAND_2 = "make";
	private static String SELECT_COMMAND = "select";
	private static String SAVE_COMMAND = "save";
	
	private CommandType _type;
	private boolean _isSelection;
	private int _selectionIndex;
	
	private String _commandText;
	
	private Command() {
		_commandText = "";
		
		_type = CommandType.NONE;
		_isSelection = false;
		_selectionIndex = -1;
	}
	

	public static Command CommandWithString(String text) {
		Command result = new Command();
		result.setCommandText(text);
		
		if (result.getCommandText().contains(VIEW_COMMAND_1) || result.getCommandText().contains(VIEW_COMMAND_2)) {
			result.setType(CommandType.VIEW);
		} else if (result.getCommandText().contains(EDIT_COMMAND_1) || result.getCommandText().contains(EDIT_COMMAND_2) ||
				result.getCommandText().contains(EDIT_COMMAND_3)) {
			result.setType(CommandType.EDIT);
		} else if (result.getCommandText().contains(REMOVE_COMMAND_1) || result.getCommandText().contains(REMOVE_COMMAND_2)) {
			result.setType(CommandType.REMOVE);
		} else if (result.getCommandText().contains(ADD_COMMAND_1) || result.getCommandText().contains(ADD_COMMAND_2)) {
			result.setType(CommandType.ADD);
		} else if (result.getCommandText().contains(SELECT_COMMAND)) {
			result.setType(CommandType.SELECT);
		} else if (result.getCommandText().contains(SAVE_COMMAND)) {
			//result.setType(CommandType.SAVE);
			result.setType(CommandType.NONE);
		} else {
			result.setType(CommandType.NONE);
		}
		
		return result;
	}
	
	public String getCommandTypeString() {
		String result = "";
		
		switch (getType()) {
		case ADD:
			result = "<ADD>";
			break;
		case EDIT:
			result = "<EDIT>";
			break;
		case REMOVE:
			result = "<REMOVE>";
			break;
		case VIEW:
			result = "<VIEW>";
			break;
		case SELECT:
			result = "<SELECT>";
			break;
		default:
			result = "<UNKNOWN>";
			break;
		}
		
		return result;
	}
	
	private void extractIndex() {
		if (getCommandText().contains("zero")) {
			setSelectionIndex(0);
		} else if (getCommandText().contains("one")) {
			setSelectionIndex(1);
		} else if (getCommandText().contains("two")) {
			setSelectionIndex(2);
		} else if (getCommandText().contains("three")) {
			setSelectionIndex(3);
		} else if (getCommandText().contains("four")) {
			setSelectionIndex(4);
		} else if (getCommandText().contains("five")) {
			setSelectionIndex(5);
		} else if (getCommandText().contains("six")) {
			setSelectionIndex(6);
		} else if (getCommandText().contains("seven")) {
			setSelectionIndex(7);
		} else if (getCommandText().contains("seven")) {
			setSelectionIndex(8);
		} else if (getCommandText().contains("nine")) {
			setSelectionIndex(9);
		} else if (getCommandText().contains("ten")) {
			setSelectionIndex(10);
		}
	}
	
	public CommandType getType() {
		return _type;
	}
	private void setType(CommandType type) {
		_type = type;
		
		if (_type == CommandType.SELECT) {
			setSelection(true);
			extractIndex();
		}
	}
	public boolean isSelection() {
		return _isSelection;
	}
	private void setSelection(boolean isSelection) {
		_isSelection = isSelection;
	}
	public int getSelectionIndex() {
		return _selectionIndex;
	}
	private void setSelectionIndex(int selectionIndex) {
		_selectionIndex = selectionIndex;
	}
	public String getCommandText() {
		return _commandText;
	}
	public void setCommandText(String commandText) {
		_commandText = commandText;
	}
}
