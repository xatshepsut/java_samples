package com.samples.notes.controller;

import java.awt.Dialog.ModalityType;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

import com.samples.notes.data.Command;
import com.samples.notes.data.Note;
import com.samples.notes.data.NoteBacklog;
import com.samples.notes.view.NoteView;
import com.samples.notes.view.NoteView.IReadyNoteListener;

public class MainViewController {
	private NoteBacklog _noteBacklog = null;
	private IMainView _view = null;
	
	private SpeechController _speechController = null;
	
	public interface IMainView {
		public void onRefreshTable();
		public void onSelectNoteAtIndex(int selectionIndex);
	}
	
	private IReadyNoteListener readyNoteListener = new IReadyNoteListener() {
		public void onNewNote(Note note) {
			addNote(note);
		}
        public void onUpdatedNote(Note oldNote, Note newNote) {
        	updateNote(oldNote, newNote);
        }
    };
	
	MainViewController() {
		_noteBacklog = new NoteBacklog();

		Note note = new Note("some title", "some text");
		_noteBacklog.addNote(note);
		Note note2 = new Note("some title 2", "some text 2", true);
		_noteBacklog.addNote(note2);
		Note note3 = new Note("some title 3", "some text 3", true);
		_noteBacklog.addNote(note3);
		
		//_speechController = new SpeechController();
	}
	
	public ListModel<Note> getNoteDataModel() {
		return _noteBacklog;
	}
	
	public void setView(IMainView view) {
		_view = view;
		_view.onRefreshTable();
	}
	
	//
	
	public void viewNote(Note note) {
		NoteView noteView = new NoteView(true, note);
		noteView.setModal(true);
		noteView.setModalityType(ModalityType.APPLICATION_MODAL);
		noteView.setReadyNoteListener(readyNoteListener);
		noteView.setVisible(true);
	}
	
	public void makeNote() {
		NoteView noteView = new NoteView();
		noteView.setModal(true);
		noteView.setModalityType(ModalityType.APPLICATION_MODAL);
        noteView.setReadyNoteListener(readyNoteListener);
        noteView.setVisible(true);
	}
	
	public void editNote(Note note) {
		NoteView noteView = new NoteView(false, note);
		noteView.setModal(true);
		noteView.setModalityType(ModalityType.APPLICATION_MODAL);
		noteView.setReadyNoteListener(readyNoteListener);
		noteView.setVisible(true);
	}
	
	//
	
	public void addNote(Note note) {
		_noteBacklog.addNote(note);
		_view.onRefreshTable();
	}
	
	public void removeNote(Note note) {
		_noteBacklog.removeNote(note);
		_view.onRefreshTable();
	}
	
	public void updateNote(Note oldNote, Note newNote) {
		_noteBacklog.updateNote(oldNote, newNote);
		_view.onRefreshTable();
	}
	
	public void getVoiceCommand() {
		getVoiceCommand(null);
	}
	
	public void getVoiceCommand(Note note) {
		Command command = _speechController.startRecognition();

		String message = "Command recognised is " + command.getCommandTypeString();
		if (command.isSelection()) {
			if (command.getSelectionIndex() != -1) {
				message += " index " + command.getSelectionIndex();				
			}
		}
		
		int response = JOptionPane.showConfirmDialog(null, message, "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.YES_OPTION) {
	      
	    	switch (command.getType()) {
			case ADD:
				makeNote();
				break;
			case EDIT:
				editNote(note);
				break;
			case REMOVE:
				removeNote(note);
				break;
			case VIEW:
				viewNote(note);
				break;
			case SELECT:
				if (command.getSelectionIndex() != -1) {
					_view.onSelectNoteAtIndex(command.getSelectionIndex());
				}
				break;
			default:
				break;
			}
	    }
		
	}
}
