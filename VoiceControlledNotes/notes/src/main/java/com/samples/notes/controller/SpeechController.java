package com.samples.notes.controller;

import java.io.IOException;

import com.samples.notes.data.Command;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class SpeechController {
	private LiveSpeechRecognizer _jsgfRecognizer;
	private Configuration _configuration;
	
	private static String ACOUSTIC_MODEL_PATH = "resource:/edu/cmu/sphinx/models/en-us/en-us";
	private static String DICTIONARY_PATH = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	private static String GRAMMAR_PATH = "src/main/resources/grammar/";
	private static String GRAMMAR_NAME = "command";
	
	
	public SpeechController() {
		configure();
		
		try {
			_jsgfRecognizer = new LiveSpeechRecognizer(_configuration);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Command startRecognition() {
		Command command = null;
		
		try {
			 _jsgfRecognizer.startRecognition(true);
			 
            SpeechResult result = _jsgfRecognizer.getResult();
            String utterance = result.getHypothesis();
            System.out.println(utterance);
	        
            command = Command.CommandWithString(utterance);

	        _jsgfRecognizer.stopRecognition();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return command;
	}
	
	private void configure() {
		_configuration = new Configuration();
		
		_configuration.setAcousticModelPath(ACOUSTIC_MODEL_PATH);
		_configuration.setDictionaryPath(DICTIONARY_PATH);
		
		_configuration.setGrammarPath(GRAMMAR_PATH);
        _configuration.setUseGrammar(true);
        _configuration.setGrammarName(GRAMMAR_NAME);
	}
}