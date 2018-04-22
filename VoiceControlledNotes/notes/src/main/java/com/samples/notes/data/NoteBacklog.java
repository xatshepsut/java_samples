package com.samples.notes.data;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class NoteBacklog extends AbstractListModel<Note> {
	private static final long serialVersionUID = -5916607703247330148L;
	private ArrayList<Note> _noteList = null;
	
	
	public NoteBacklog() {
		_noteList = new ArrayList<Note>();
	}
	
	
	/* AbstractListModel methods */
	
	public int getSize() {
      return _noteList.size();
    }
	
    public Note getElementAt(int index) {
    	if (index < _noteList.size()) {
    		return _noteList.get(index);
    	} else {
    		return null;
    	}
    }
	
	
	/* Note backlog getters  */
	
	public Note getNoteWithTitle(String title) {
		Note result = null;
		for (Note note : _noteList) {
			if(note.getTitle().equalsIgnoreCase(title)) {
				result = note;
				break;
			}
		}
		return result;
	}
	
	public int getIndexOfNote(Note note) {
		int result = -1;
		
		for (int i = 0; i < _noteList.size(); i++) {
			Note currentNote = _noteList.get(i);
			
			if(note.equals(currentNote)) {
				result = i;
				break;
			}
		}
		return result;
	}
	
	
	/* Note backlog basic operations */
	
	public void addNewNote(String title, String text) {
		if (title == null || text == null) {
			throw new IllegalArgumentException();
		}
		
		Note note = new Note(title, text);
		_noteList.add(note);
	}
	
	public void addNote(Note note) {
		if (note == null) {
			throw new IllegalArgumentException();
		}
		
		_noteList.add(note);
	}
	
	public void removeNote(Note note) {
		if (note == null) {
			throw new IllegalArgumentException();
		}
		
		int index = getIndexOfNote(note);
		_noteList.remove(index);
	}
	
	public void updateNote(Note oldNote, Note updatedNote) {
		if (oldNote == null || updatedNote == null) {
			throw new IllegalArgumentException();
		}
		
		int index = getIndexOfNote(oldNote);
		_noteList.set(index, updatedNote);
	}
}
