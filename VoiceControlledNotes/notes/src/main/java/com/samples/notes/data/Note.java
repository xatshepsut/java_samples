package com.samples.notes.data;

public class Note {
	private String _title;
	private String _text;
	private boolean _isImportant;
	
	public Note() {
		setTitle("");
		setText("");
		setImportant(false);
	}
	
	public Note(String title, String text) {
		if (title.length() == 0) {
			int length = text.length() > 20 ? 20 : text.length(); 
			setTitle(text.substring(0, length));
		} else {
			setTitle(title);
		}
		
		setText(text);
		setImportant(false);
	}
	
	public Note(String title, String text, boolean isImportant) {
		this(title, text);
		setImportant(isImportant);
	}
	
	public Note(Note anotherNote) {
	    this.setTitle(anotherNote.getTitle());
	    this.setText(anotherNote.getText());
	    this.setImportant(anotherNote.isImportant());
    }
	
	public String getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		_title = title;
	}
	public String getText() {
		return _text;
	}
	public void setText(String text) {
		_text = text;
	}
	public boolean isImportant() {
		return _isImportant;
	}
	public void setImportant(boolean isImportant) {
		_isImportant = isImportant;
	}

	public boolean equals(Note anotherNote) {
		if (anotherNote == null) {
			return false;
		}
		
		boolean result = this.getText().equalsIgnoreCase(anotherNote.getText());
		result &= this.getTitle().equalsIgnoreCase(anotherNote.getTitle());
		result &= this.isImportant() == anotherNote.isImportant();
		
		return result;
	}

}
