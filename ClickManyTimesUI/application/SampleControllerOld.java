package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SampleControllerOld {
	@FXML
	public RadioButton rbSingleColumn, rbCountDoubles, rbCheckDatabase, rbMakeExercise;
	public ArrayList<RadioButton> rbBtns;

	@FXML
	public Button btnSelectFile, btnStart;

	@FXML
	public static TextField tfSheet, tfColumn, tfRow;

	@FXML
	private VBox root;

	@FXML
	private Node itmSelectFile;

	@FXML
	private Pane dropFilePane;

	private static int sheet, column, row = 0;
	private static String filePath;

	private SampleControllerFunctions functions;

	private static int fctIndex = 0;

	public SampleControllerOld(VBox root) {
		this.root = root;
		functions = new SampleControllerFunctions();
		getNodes();
		addRadioBtnsToArrayList();
		setOnClick();
		selectRadioButton(fctIndex);
	}

	private void addRadioBtnsToArrayList() {
		rbBtns = new ArrayList<>();
		rbBtns.add(rbSingleColumn);
		rbBtns.add(rbCountDoubles);
		rbBtns.add(rbCheckDatabase);
		rbBtns.add(rbMakeExercise);
	}

	public void radioBtnGroupFunctions() {
		
	}
	
	private void setOnClick() {
		rbSingleColumn.setOnMouseClicked(c -> {
			fctIndex = 0;
			selectRadioButton(fctIndex);
		});
		rbCountDoubles.setOnMouseClicked(c -> {
			fctIndex = 1;
			selectRadioButton(fctIndex);
		});
		rbCheckDatabase.setOnMouseClicked(c -> {
			fctIndex = 2;
			selectRadioButton(fctIndex);
		});
		rbMakeExercise.setOnMouseClicked(c -> {
			fctIndex = 3;
			selectRadioButton(fctIndex);
		});

		btnSelectFile.setOnMouseClicked(c -> {
			SampleControllerFunctions.selectFile();
		});

		btnStart.setOnMouseClicked(c -> {
			runFunctions(fctIndex);
		});

		// #######################################
		dropFilePane.setOnDragOver(c -> {
			SampleControllerFunctions.dragOver(c);
		});

		dropFilePane.setOnDragDropped(c -> {
			SampleControllerFunctions.dragDrop(c);
		});
		// #######################################
		root.setOnDragOver(c -> {
			SampleControllerFunctions.dragOver(c);
		});

		dropFilePane.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.err.println("DRAG OVER");
//                if (event.getGestureSource() != dropFilePane
//                        && event.getDragboard().hasFiles()) {
//                    /* allow for both copying and moving, whatever user chooses */
//                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }
//                event.consume();
				SampleControllerFunctions.dragOver(event);
			}
		});

		root.setOnDragDropped(c -> {
			SampleControllerFunctions.dragDrop(c);
		});
	}

	private void selectRadioButton(int fctIndex) {
		for (int i = 0; i < rbBtns.size(); i++) {
			rbBtns.get(i).setSelected(false);
		}
		rbBtns.get(fctIndex).setSelected(true);
	}

	public static void runFunctions(int fctIndex2) {
		try {
			sheet = Integer.parseInt(tfSheet.getText());
			column = Integer.parseInt(tfColumn.getText());
			row = Integer.parseInt(tfRow.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fctIndex == 0) {
			SampleControllerFunctions.countDoubleOccurences(sheet, column, row);
		}
		if (fctIndex == 1) {
			SampleControllerFunctions.countDoubleOccurences(sheet, column, row);
		}
		if (fctIndex == 2) {

		}
		if (fctIndex == 3) {
			SampleControllerFunctions.createGapsText(sheet, column, row);
		}
	}

	private void getNodes() {

		rbSingleColumn = (RadioButton) root.lookup("#rbSingleColumn");
		rbCountDoubles = (RadioButton) root.lookup("#rbCountDoubles");
		rbCheckDatabase = (RadioButton) root.lookup("#rbCheckDatabase");
		rbMakeExercise = (RadioButton) root.lookup("#rbMakeExercise");

		btnSelectFile = (Button) root.lookup("#btnSelectFile");
		btnStart = (Button) root.lookup("#btnStart");

		tfSheet = (TextField) root.lookup("#tfSheet");
		tfColumn = (TextField) root.lookup("#tfColumn");
		tfRow = (TextField) root.lookup("#tfRow");

		itmSelectFile = (Node) root.lookup("#itmSelectFile");

		dropFilePane = (Pane) root.lookup("#dropFilePane");
	}

	public int getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public SampleControllerFunctions getFunctions() {
		return functions;
	}

	@FXML
	void handleDragOver(DragEvent event) {

	}
}
