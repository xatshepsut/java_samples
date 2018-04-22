package com.samples.notes.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.samples.notes.components.NoteCellRenderer;
import com.samples.notes.controller.MainViewController;
import com.samples.notes.controller.MainViewController.IMainView;
import com.samples.notes.data.Note;

public class MainView extends JFrame implements IMainView {
	
	private static final long serialVersionUID = 927083687282467387L;
	private MainViewController _controller;

	private JPanel _contentPane ;
	private JList<Note> _jListNotes;
	
	private JButton _btnAdd;
	private JButton _btnRemove;
	private JButton _btnEdit;
	private JButton _btnView;
	private JButton _btnVoiceCommand;

	//private NotesBacklog _data

	public MainView(MainViewController controller) {
		// TODO: get rid of exceptions in main, they prevent declaring this object as static!!!
		// Constructor supposes that it's initialized, i.e. objects need to exist after we exit
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		setResizable(false);
		
		_contentPane = new JPanel();
		_contentPane.setBackground(Color.WHITE);
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_contentPane);
		_contentPane.setLayout(null);
		
		_jListNotes = new JList<Note>();
		
//		_btnView = new JButton("View");
//		_btnAdd = new JButton("Add");
//		_btnEdit = new JButton("Edit");
//		_btnRemove = new JButton("Remove");
//		_btnVoiceCommand = new JButton("Voice command");
		
		setupComponents();
		
		
		setController(controller);
		
		_jListNotes.setModel(_controller.getNoteDataModel());
		
		_controller.setView(this);
	}
	
	public MainViewController getController() {
		return _controller;
	}
	public void setController(MainViewController controller) {
		_controller = controller;
	}
	
	@SuppressWarnings("unchecked")
	private void setupComponents() {

		_btnView = new JButton("View");
		_btnAdd = new JButton("Add");
		_btnEdit = new JButton("Edit");
		_btnRemove = new JButton("Remove");
		_btnVoiceCommand = new JButton("Voice command");
		
		// Notes jList setup
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 51, 327, 266);
		_contentPane.add(scrollPane);
		
		scrollPane.setViewportView(_jListNotes);
		_jListNotes.setCellRenderer(new NoteCellRenderer());
		_jListNotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_jListNotes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		_jListNotes.setBackground(UIManager.getColor("Button.highlight"));
		_jListNotes.setFixedCellHeight(22);
		
		// View button setup
		_btnView.setIcon(null);
		_btnView.setFocusPainted(false);
		_btnView.setBackground(SystemColor.controlHighlight);
		_btnView.setBounds(349, 53, 83, 25);
		_btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onViewButtonClicked();
			}
		});
		_contentPane.add(_btnView);
		
		// Add button setup
		_btnAdd.setIcon(null);
		_btnAdd.setFocusPainted(false);
		_btnAdd.setBounds(349, 191, 83, 25);
		_btnAdd.setBackground(SystemColor.controlHighlight);
		_btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAddButtonClicked();
			}
		});
		_contentPane.add(_btnAdd);
		
		
		// Remove button setup
		_btnRemove.setBackground(SystemColor.controlHighlight);
		_btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRemoveButtonClicked();
			}
		});
		_btnRemove.setIcon(null);
		_btnRemove.setFocusPainted(false);
		_btnRemove.setBounds(349, 129, 83, 25);
		_contentPane.add(_btnRemove);
		
		
		// Edit button setup
		_btnEdit.setBackground(SystemColor.controlHighlight);
		_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEditButtonClicked();
			}
		});
		_btnEdit.setIcon(null);
		_btnEdit.setFocusPainted(false);
		_btnEdit.setBounds(349, 91, 83, 25);
		_contentPane.add(_btnEdit);
		
		
		// Voice command button setup
		_btnVoiceCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onVoiceCommandButtonClicked();
			}
		});
		_btnVoiceCommand.setIcon(null);
		_btnVoiceCommand.setFocusPainted(false);
		_btnVoiceCommand.setBackground(Color.YELLOW);
		_btnVoiceCommand.setBounds(10, 13, 137, 25);
		_contentPane.add(_btnVoiceCommand);
		
	}
	
	private void enableControls(boolean enable) {
		_btnView.setEnabled(enable);
		_btnRemove.setEnabled(enable);
		_btnEdit.setEnabled(enable);
	}
	
	/* Button click events */
	
	private void onViewButtonClicked() {
		Note note = (Note) _jListNotes.getSelectedValue();
		_controller.viewNote(note);
	}
	
	private void onEditButtonClicked() {
		Note note = (Note) _jListNotes.getSelectedValue();
		_controller.editNote(note);
	}
	
	private void onRemoveButtonClicked() {
		Note note = (Note) _jListNotes.getSelectedValue();
		_controller.removeNote(note);
	}
	
	private void onAddButtonClicked() {
		_controller.makeNote();
	}
	
	private void onVoiceCommandButtonClicked() {
		Note note = (Note) _jListNotes.getSelectedValue();
		_jListNotes.isSelectionEmpty();
		_controller.getVoiceCommand(note);
	}
	
	

	/* IMainView interface implementation */
	
	public void onRefreshTable() {
		_jListNotes.repaint();
		onSelectNoteAtIndex(0);
	}

	public void onSelectNoteAtIndex(int selectionIndex) {
		if (_jListNotes.getModel().getSize() > 0) {
			if (_jListNotes.getModel().getSize() < selectionIndex) {
				_jListNotes.setSelectedIndex(0);				
			} else {
				_jListNotes.setSelectedIndex(selectionIndex);	
			}
			enableControls(true);
		} else {
			enableControls(false);
		}
	}

	
}
