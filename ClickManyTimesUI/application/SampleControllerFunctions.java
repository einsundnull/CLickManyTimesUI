package application;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import clickMain.ClickMain;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import readExcel.ReadExcelFile;

public class SampleControllerFunctions {

	private static File excelFile;
	private static List<File> fileList;
	private static int sheet = 0;
	private static int column = 0;
	private static int row = 0;
	public static int fctIndex = 0;
	private static Object columnII;
	private static int wordCount;
	private static int columnFrom;
	private static int columnTo;

	static {
		getNodes();
	}

	public static LinkedList<String[]> readExcelSingleSheet(File file) {
		LinkedList<String[]> data = new LinkedList<String[]>();
		wordCount = 0;
		try {
			FileInputStream inputStream = new FileInputStream(new File(ExcelParameter.excelFilePathIn));
			Workbook workbook = new XSSFWorkbook(inputStream);
			try {
				Sheet sheet = workbook.getSheetAt(ExcelParameter.sheet);
				for (Row row : sheet) {
					String[] cells = new String[ExcelParameter.columns];
					for (int i = 0; i < 3; i++) {
						Cell cell = row.getCell(i);
						try {
							cells[i] = cell.getStringCellValue();
						} catch (Exception e) {
							cells[i] = "Empty";
						}
						System.out.print(cells[i] + " ");
					}
					System.out.println("");
					ExcelParameter.data.add(cells);
					wordCount++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void compareTowCellsHozizontalFromExcelArrayFindeDifferenceOffWords(String[] list, boolean checkForUpperCase)
			throws AWTException, InterruptedException, UnsupportedFlavorException, IOException {
//		This int determines how often the action in the table should be performed

		int i = 1;
		int c = 1;
		String stringI;
		String stringII;
		String posNoun = "";
		String toPaste = "";
		while (c < list.length) {
			boolean reset = false;
			if (checkForUpperCase) {
				char[] temp = list[c].toCharArray();
				if (Character.isUpperCase(temp[0])) {
					posNoun = "\t" + "n?";
				} else {
					posNoun = "";
				}

			}
			stringI = list[c];
			stringII = list[c - 1];
			stringII = stringII.toLowerCase();
			stringI = stringI.toLowerCase();
			if (stringI.equals(stringII)) {
				reset = false;
			} else {
				reset = true;
			}

			toPaste = toPaste + i + posNoun + "\n";
			i++;
			if (reset) {
				i = 1;
				reset = false;
			}
			c++;
		}
		StringSelection stringSelection = new StringSelection(toPaste + i);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public static void compareTowCellsVerticalFromExcelArrayCountOccurenceOfWords(String[] list, boolean checkForUpperCase)
			throws AWTException, InterruptedException, UnsupportedFlavorException, IOException {
//		This int determines how often the action in the table should be performed

		int i = 1;
		int c = 1;
		String stringI;
		String stringII;
		String posNoun = "";
		String toPaste = "";
		while (c < list.length) {
			boolean reset = false;
			if (checkForUpperCase) {
				char[] temp = list[c].toCharArray();
				if (Character.isUpperCase(temp[0])) {
					posNoun = "\t" + "n?";
				} else {
					posNoun = "";
				}

			}
			stringI = list[c];
			stringII = list[c - 1];
			stringII = stringII.toLowerCase();
			stringI = stringI.toLowerCase();
			if (stringI.equals(stringII)) {
				reset = false;
			} else {
				reset = true;
			}

			toPaste = toPaste + i + posNoun + "\n";
			i++;
			if (reset) {
				i = 1;
				reset = false;
			}
			c++;
		}
		StringSelection stringSelection = new StringSelection(toPaste + i);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public static void createGapsText(int sheet, int column, int row) {
//		// Make sentences With gaps
		String[] sentences;
		try {
			sentences = ReadExcelFile.readExcelFileSingleColumn(excelFile, sheet, column, true);
			ArrayList<ArrayList<String>> temp = ClickMain.splitSentencesToArrayList(sentences);
			temp = ClickMain.makeExerciseFromSentences(temp);
			ClickMain.makeSingleStringFromArrayArrayList(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void makeSingleColumnOfAllWords(int sheet, int column, int row) {
		// Make single Column of all words
		String[] sentences;
		try {
			sentences = ReadExcelFile.readExcelFileSingleColumn(excelFile, sheet, column, true);
			ClickMain.makeSingleColumnStringFromList(sentences);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void countDoubleOccurences(int sheet, int column, int row) {
		// Make single Column of all words
		String[] sentences;
		System.out.println(fctIndex);
		try {
			sentences = ReadExcelFile.readExcelFileSingleColumn(excelFile, sheet, column, true);
			compareTowCellsVerticalFromExcelArrayCountOccurenceOfWords(sentences, false);
		} catch (IOException | AWTException | InterruptedException | UnsupportedFlavorException e) {
			e.printStackTrace();
		}
	}

	public static void selectFile() {
		FileChooser fch = new FileChooser();
		fch.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("EXCEL files (*.xlsx)", "*.xlsx");
		fch.getExtensionFilters().add(extFilter);
		excelFile = fch.showOpenDialog(null);
	}

	public static void dragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
		event.consume();
	}

	public static void dragDrop(DragEvent event) {
		System.err.println("DRAG DROPPED");
		if (event.getDragboard().hasFiles()) {

			fileList = event.getDragboard().getFiles();
			if (FilenameUtils.getExtension(fileList.get(0).getName()).equals("xlsx")) {

				excelFile = fileList.get(0);
				System.err.println(fileList.get(0));
				try {
					System.out.println(excelFile.getName());
					SampleController.dropFilePane.setText(excelFile.getName());
					System.out.println(fileList.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

			}
		}
		event.consume();
	}

	public static void runFunctions(int fctIndex2) {
		try {
			sheet = Integer.parseInt(SampleController.tfSheet.getText());
			column = Integer.parseInt(SampleController.tfColumn.getText());
			row = Integer.parseInt(SampleController.tfRow.getText());
			columnFrom = Integer.parseInt(SampleController.tfColumnFrom.getText());
			columnTo = Integer.parseInt(SampleController.tfColumnTo.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sheet = Integer.parseInt(SampleController.tfSheet.getText());
			columnFrom = Integer.parseInt(SampleController.tfColumnFrom.getText());
			columnTo = Integer.parseInt(SampleController.tfColumnTo.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (fctIndex == 0) {

			makeSingleColumnOfAllWords(sheet, column, row);
		}
		if (fctIndex == 1) {
			countDoubleOccurences(sheet, column, row);
		}
		if (fctIndex == 2) {

		}
		if (fctIndex == 3) {
			createGapsText(sheet, column, row);
		}
		if (fctIndex == 4) {
			LinkedList<String[]> temp = ReadExcelFile.readExcelFromTo(excelFile, sheet, columnFrom, columnTo);
//			findeDifference(temp);
			findSequence(temp);
		}
	}

	private static LinkedList<String> findeDifference(LinkedList<String[]> data) {
		LinkedList<String> newList = new LinkedList<>();
		String toPaste = "";
		for (int i = 0; i < data.size(); i++) {
			String one = data.get(i)[0].replaceAll("[^ÄÖÜäöü]", "");
			String two = data.get(i)[1].replaceAll("[^ÄÖÜäöü]", "");

			if (one.isEmpty() && !two.isEmpty()) {
				newList.add(two);
				toPaste = toPaste + two + "\n";
			} else {
				newList.add("#");
				toPaste = toPaste + "#" + "\n";
			}

		}
		System.out.println(data);
		StringSelection stringSelection = new StringSelection(toPaste);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		return newList;
	}

	private static LinkedList<String> findSequence(LinkedList<String[]> data) {
		LinkedList<String> newList = new LinkedList<>();
		StringBuilder toPaste = new StringBuilder();
		CharSequence[] sequences = { "ach", "och", "uch", "ich", "äch", "öch", "üch" };
		for (String[] entry : data) {
			String temp = entry[0].replace(" ", "");
			boolean found = false;
			for (CharSequence sequence : sequences) {
				if (temp.contains(sequence)) {
					toPaste.append(sequence).append("\n");
					found = true;
					break;
				}
			}
			if (!found) {
				toPaste.append("#\n");
			}
		}

		StringSelection stringSelection = new StringSelection(toPaste.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		return newList;
	}

	private static LinkedList<String> findEnding(LinkedList<String[]> data) {
		LinkedList<String> newList = new LinkedList<>();
		StringBuilder toPaste = new StringBuilder();
		CharSequence[] sequences = { "ung", "heit", "keit", "schaft", "ion", "thek", "üch" };
		CharSequence[] sequencesII = { "g", "ness", "mus", "zeug", "e" };
		for (String[] entry : data) {
			String temp = entry[0].replace(" ", "");
			boolean found = false;
			for (CharSequence sequence : sequences) {
				if (temp.contains(sequence)) {
					toPaste.append(sequence).append("\n");
					found = true;
					break;
				} 
//				else if (!temp.contains(sequence) && temp.contains(sequencesII)) {
//
//				}
			}
			if (!found) {
				toPaste.append("#\n");
			}
		}

		StringSelection stringSelection = new StringSelection(toPaste.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		return newList;
	}

//	private static LinkedList<String> findSequence(LinkedList<String[]> data) {
//		LinkedList<String> newList = new LinkedList<>();
//		String toPaste = "";
//		CharSequence oneSeq = "ach";
//		CharSequence twoSeq = "och";
//		CharSequence threeSeq = "uch";
//		for (int i = 0; i < data.size(); i++) {
//			String temp = data.get(i)[0].replace(" ", "");
//			String add = "";
//			boolean found = false;
//			if (temp.contains(oneSeq)) {
//				add = oneSeq + "";
//				found = true;
//			} else if (temp.contains(twoSeq)) {
//				add = twoSeq + "";
//				found = true;
//			} else if (temp.contains(threeSeq)) {
//				add = threeSeq + "";
//				found = true;
//			} else if (!found) {
//				add = "#";
//			}
//			toPaste = toPaste + add + "\n";
//			System.out.println(toPaste);
//		}
//		StringSelection stringSelection = new StringSelection(toPaste);
//		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		clipboard.setContents(stringSelection, null);
//		return newList;
//	}

	public static void getNodes() {

		SampleController.rbSingleColumn = (RadioButton) SampleController.root.lookup("#rbSingleColumn");
		SampleController.rbCountDoubles = (RadioButton) SampleController.root.lookup("#rbCountDoubles");
		SampleController.rbCheckDatabase = (RadioButton) SampleController.root.lookup("#rbCheckDatabase");
		SampleController.rbMakeExercise = (RadioButton) SampleController.root.lookup("#rbMakeExercise");

		SampleController.btnSelectFile = (Button) SampleController.root.lookup("#btnSelectFile");
		SampleController.btnStart = (Button) SampleController.root.lookup("#btnStart");
		SampleController.btnStartFindDifference = (Button) SampleController.root.lookup("#btnStartFindDifference");
		SampleController.tfSheet = (TextField) SampleController.root.lookup("#tfSheet");
		SampleController.tfColumn = (TextField) SampleController.root.lookup("#tfColumn");
		SampleController.tfColumnFrom = (TextField) SampleController.root.lookup("#tfColumnFrom");
		SampleController.tfColumnTo = (TextField) SampleController.root.lookup("#tfColumnTo");
		SampleController.tfRow = (TextField) SampleController.root.lookup("#tfRow");

		SampleController.dropFilePane = (Label) SampleController.root.lookup("#dropFilePane");
	}

}
