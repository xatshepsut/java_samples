package com.samples.notes.controller;

import java.awt.EventQueue;

import com.samples.notes.view.MainView;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView(new MainViewController());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
