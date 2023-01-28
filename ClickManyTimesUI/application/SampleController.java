package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;

public class SampleController {

	@FXML
	public static Button btnSelectFile;

	@FXML
	public static Button btnStart;

	@FXML
	public static Label dropFilePane;

	@FXML
	public static RadioButton rbCheckDatabase;

	@FXML
	public static RadioButton rbCountDoubles;

	@FXML
	public static RadioButton rbMakeExercise;

	@FXML
	public static RadioButton rbSingleColumn;

	@FXML
	public static VBox root;

	@FXML
	static TextField tfColumn;

	@FXML
	static TextField tfColumnFrom;

	@FXML
	static TextField tfColumnTo;

	@FXML
	static TextField tfRow;

	@FXML
	static TextField tfSheet;
	@FXML
	static Button btnStartFindDifference;

	@FXML
	void handleDragOver(DragEvent event) {
		SampleControllerFunctions.dragOver(event);
	}

	@FXML
	void handleDragDrop(DragEvent event) {
		SampleControllerFunctions.dragDrop(event);
	}

	@FXML
	void handleSelectFile(ActionEvent event) {
		SampleControllerFunctions.selectFile();
	}

	@FXML
	void handleStart(ActionEvent event) {
		SampleControllerFunctions.getNodes();
		SampleControllerFunctions.runFunctions(SampleControllerFunctions.fctIndex);
	}
	
	@FXML
	void handleStartDifference(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 4;
		SampleControllerFunctions.getNodes();
		SampleControllerFunctions.runFunctions(SampleControllerFunctions.fctIndex);
	}

	@FXML
	void makeSingleColumn(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 0;
		System.out.println(SampleControllerFunctions.fctIndex);
	}

	@FXML
	void countDoubleOccurences(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 1;
		System.out.println(SampleControllerFunctions.fctIndex);
	}

	@FXML
	void checkDatabase(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 2;
		System.out.println(SampleControllerFunctions.fctIndex);
	}

	@FXML
	void makeExersiceFromText(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 3;
		System.out.println(SampleControllerFunctions.fctIndex);
	}

	@FXML
	void checkForDifference(ActionEvent event) {
		SampleControllerFunctions.fctIndex = 4;

	}

}
