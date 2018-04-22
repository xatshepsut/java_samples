package com.samples.notes.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.samples.notes.data.Note;

@SuppressWarnings("rawtypes")
public class NoteCellRenderer extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = -3999371229796238341L;
	private static final Color HIGHLIGHT_COLOR = new Color(128, 128, 128);
	
	private static String IMPORTANT_ICON_PATH = "images/important.png";
	private static String BLANK_ICON_PATH = "images/blank.png";
	
	
	public NoteCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object object,
			int index, boolean isSelected, boolean isFocused) {
		
		Note note = (Note) object;
		setText(note.getTitle());
		setIconTextGap(10);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		if (isSelected) {
	      setBackground(HIGHLIGHT_COLOR);
	      setForeground(Color.white);
	    } else {
	      setBackground(Color.white);
	      setForeground(Color.black);
	    }
		
		if (note.isImportant()) {
			ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(IMPORTANT_ICON_PATH));
			setIcon(icon);
		} else {
			ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(BLANK_ICON_PATH));
			setIcon(icon);
		}
		
		return this;
	}
}
