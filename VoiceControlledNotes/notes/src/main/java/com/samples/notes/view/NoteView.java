package com.samples.notes.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.samples.notes.data.Note;

public class NoteView extends JDialog {
	private static final long serialVersionUID = 8747909325802497937L;
	
	private JPanel _contentPane;
	private JPanel _previewPanel;
	private JPanel _editPanel;
	private JLabel _lblNoteTitle;
	private JTextField _txtNoteTitle;
	private JTextPane _txtpnNoteText;
	private JTextPane _txtpnEditableNoteText;
	
	private boolean _isPromptTitle = false;
	private boolean _isPromptText = false;

	private Note _currentNote;
	private boolean _isPreviewMode = false;
	private boolean _isNewNote = false;
	
	// Listener for note editing finished state
	private IReadyNoteListener _listener;
	private JCheckBox _chckbxMarkImportant;
	private JLabel _lblImportant;
	
	public interface IReadyNoteListener {
		public void onNewNote(Note note);
        public void onUpdatedNote(Note oldNote, Note newNote);
    }
	

	public NoteView() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 361);
		setMinimumSize(new Dimension(300,300));
		setResizable(true);
		
		_contentPane = new JPanel();
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(_contentPane);
		
		_previewPanel = new JPanel();
		_previewPanel.setBackground(SystemColor.control);
		_previewPanel.setVisible(false);
		_contentPane.add(_previewPanel, "name_19077727121060");
		
		_editPanel = new JPanel();
		_editPanel.setBackground(SystemColor.control);
		_editPanel.setVisible(false);
		_contentPane.add(_editPanel, "name_19077745331146");
		
		
		// Setting states before component initialization
		_currentNote = new Note();
		_isNewNote = true;
		_isPromptTitle = true;
		_isPromptText = true;
		
		initilizeEditModeComponents();
		switchToEditMode();
	}
	
	public NoteView(boolean previewMode, Note note) {
		if (note == null) {
			throw new IllegalArgumentException();
		}
		
		initilizeView();
		initilizePreviewModeComponents();
		initilizeEditModeComponents();
		
		_currentNote = new Note(note);
		if (previewMode == true) {
			switchToPreviewMode();
		} else {
			switchToEditMode();
		}
	}
	
	public void setReadyNoteListener(IReadyNoteListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		_listener = listener;
	}
	
	private void initilizeView() {
		
	}
	
	private void initilizePreviewModeComponents() {
		
		SpringLayout sl__previewPanel = new SpringLayout();
		_previewPanel.setLayout(sl__previewPanel);
		
		_lblNoteTitle = new JLabel("Title label");
		sl__previewPanel.putConstraint(SpringLayout.WEST, _lblNoteTitle, 50, SpringLayout.WEST, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, _lblNoteTitle, 51, SpringLayout.NORTH, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.NORTH, _lblNoteTitle, 10, SpringLayout.NORTH, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, _lblNoteTitle, 51, SpringLayout.NORTH, _previewPanel);
		_lblNoteTitle.setBackground(Color.WHITE);
		_lblNoteTitle.setFont(new Font("Times LatArm", Font.PLAIN, 20));
		_previewPanel.add(_lblNoteTitle);
		
		_lblImportant = new JLabel("");
		sl__previewPanel.putConstraint(SpringLayout.NORTH, _lblImportant, 15, SpringLayout.NORTH, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.WEST, _lblImportant, 10, SpringLayout.WEST, _previewPanel);
		_lblImportant.setIcon(new ImageIcon(NoteView.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		_previewPanel.add(_lblImportant);
		
		
		JButton btnOk = new JButton("OK");	
//		btnOk.setContentAreaFilled(false);
//		btnOk.setForeground(new Color(255, 255, 255));
//		btnOk.setBackground(UIManager.getColor("Button.darkShadow"));
		sl__previewPanel.putConstraint(SpringLayout.EAST, btnOk, -30, SpringLayout.EAST, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, btnOk, -10, SpringLayout.SOUTH, _previewPanel);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		_previewPanel.add(btnOk);
//		SwingUtilities.getRootPane(btnOk).setDefaultButton(btnOk);
		
		JButton btnEdit = new JButton("Edit");
//		btnEdit.setIcon(null);
//		btnEdit.setForeground(new Color(255, 255, 255));
//		btnEdit.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		sl__previewPanel.putConstraint(SpringLayout.NORTH, btnEdit, 0, SpringLayout.NORTH, btnOk);
		sl__previewPanel.putConstraint(SpringLayout.EAST, btnEdit, -10, SpringLayout.WEST, btnOk);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToEditMode();
			}
		});
		_previewPanel.add(btnEdit);
		
		
		// Initializing JTextPane wrapped with JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		sl__previewPanel.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, _lblNoteTitle);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.NORTH, btnOk);
		sl__previewPanel.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, _previewPanel);
		sl__previewPanel.putConstraint(SpringLayout.EAST, _lblNoteTitle, -10, SpringLayout.EAST, scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		_previewPanel.add(scrollPane);
		
		_txtpnNoteText = new JTextPane();
		_txtpnNoteText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		_txtpnNoteText.setText("Note text goes here");
		_txtpnNoteText.setBackground(SystemColor.control);
		_txtpnNoteText.setEditable(false);
		scrollPane.setViewportView(_txtpnNoteText);
		
		
	}
	
	private void initilizeEditModeComponents() {
		
		SpringLayout sl__previewPanel = new SpringLayout();
		_editPanel.setLayout(sl__previewPanel);
		
		JButton btnSave = new JButton("Save");
//		btnSave.setIcon(null);
//		btnSave.setFocusPainted(false);
//		btnSave.setForeground(Color.WHITE);
//		btnSave.setBackground(UIManager.getColor("Button.darkShadow"));
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, _editPanel);
		sl__previewPanel.putConstraint(SpringLayout.EAST, btnSave, -10, SpringLayout.EAST, _editPanel);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	onSave();
	        }
		});
		_editPanel.add(btnSave);
		btnSave.requestFocusInWindow();
		
		JButton btnCancel = new JButton("Cancel");
//		btnCancel.setOpaque(false);
//		btnCancel.setPressedIcon(null);
//		btnCancel.setRequestFocusEnabled(false);
//		btnCancel.setFocusPainted(false);
//		btnCancel.setForeground(Color.WHITE);
//		btnCancel.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, btnCancel, 0, SpringLayout.SOUTH, btnSave);
		sl__previewPanel.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.WEST, btnSave);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		_editPanel.add(btnCancel);
		
		_txtNoteTitle = new JTextField();
		_txtNoteTitle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				// Remove prompt text when editing
				if (_isPromptTitle) {
					_txtNoteTitle.setText("");
					_isPromptTitle = false;
				}
			}
		});
		sl__previewPanel.putConstraint(SpringLayout.NORTH, _txtNoteTitle, 10, SpringLayout.NORTH, _editPanel);
		sl__previewPanel.putConstraint(SpringLayout.WEST, _txtNoteTitle, 10, SpringLayout.WEST, _editPanel);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, _txtNoteTitle, 43, SpringLayout.NORTH, _editPanel);
		sl__previewPanel.putConstraint(SpringLayout.EAST, _txtNoteTitle, 0, SpringLayout.EAST, btnSave);
		_txtNoteTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		_txtNoteTitle.setText("Enter title...");
		_txtNoteTitle.setColumns(10);
		_editPanel.add(_txtNoteTitle);
		
		
		// Initializing JTextPane wrapped with JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -30, SpringLayout.NORTH, btnSave);
		sl__previewPanel.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, _txtNoteTitle);
		sl__previewPanel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, _txtNoteTitle);
		sl__previewPanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, _editPanel);
		_editPanel.add(scrollPane);
		
		_txtpnEditableNoteText = new JTextPane();
		_txtpnEditableNoteText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		_txtpnEditableNoteText.setText("Enter note text...");
		_txtpnEditableNoteText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Remove prompt text when editing
				if (_isPromptText) {
					_txtpnEditableNoteText.setText("");
					_isPromptText = false;
				}
			}
		});
		scrollPane.setViewportView(_txtpnEditableNoteText);
		
		_chckbxMarkImportant = new JCheckBox("Mark as important");
		sl__previewPanel.putConstraint(SpringLayout.NORTH, _chckbxMarkImportant, 5, SpringLayout.SOUTH, scrollPane);
		sl__previewPanel.putConstraint(SpringLayout.WEST, _chckbxMarkImportant, 10, SpringLayout.WEST, _editPanel);
		sl__previewPanel.putConstraint(SpringLayout.SOUTH, _chckbxMarkImportant, -1, SpringLayout.NORTH, btnSave);
		_editPanel.add(_chckbxMarkImportant);
		
	}
	
	private void switchToEditMode() {
		assert _currentNote != null;
		
		if (!_isNewNote) {
			_txtNoteTitle.setText(_currentNote.getTitle());
			_txtpnEditableNoteText.setText(_currentNote.getText());		
			_chckbxMarkImportant.setSelected(_currentNote.isImportant());
		}
		
		_previewPanel.setVisible(false);
		_editPanel.setVisible(true);

		_isPreviewMode = false;
	}
	
	private void switchToPreviewMode() {
		assert _currentNote != null;
		
		_lblNoteTitle.setText(_currentNote.getTitle());
		_txtpnNoteText.setText(_currentNote.getText());
		showWarningIcon(_currentNote.isImportant());
		
		_editPanel.setVisible(false);
		_previewPanel.setVisible(true);
		
		_isPreviewMode = true;
	}
	
	private void showWarningIcon(boolean show) {
		if (show) {
			((SpringLayout) _previewPanel.getLayout()).putConstraint(SpringLayout.WEST, _lblNoteTitle, 50, SpringLayout.WEST, _previewPanel);
			_lblImportant.setVisible(true);
		} else {
			_lblImportant.setVisible(false);
			((SpringLayout) _previewPanel.getLayout()).putConstraint(SpringLayout.WEST, _lblNoteTitle, 10, SpringLayout.WEST, _previewPanel);
		}
	}
	
	private void onSave() {
		if (_isPreviewMode) {
    		return;
    	}
    	
        if (_listener != null) {
        	if (_isNewNote) {
        		_currentNote.setTitle(_txtNoteTitle.getText());
        		_currentNote.setText(_txtpnEditableNoteText.getText());
        		_currentNote.setImportant(_chckbxMarkImportant.isSelected());
        		_listener.onNewNote(_currentNote);
        	} else {
        		Note oldNote = new Note(_currentNote);
        		_currentNote.setTitle(_txtNoteTitle.getText());
        		_currentNote.setText(_txtpnEditableNoteText.getText());
        		_currentNote.setImportant(_chckbxMarkImportant.isSelected());
        		_listener.onUpdatedNote(oldNote, _currentNote);
        	}
        }
        
        dispose();
	}
}
