package com.rrhh.auxiliares;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class wordDemo {

	public static void main(String[] args) {
		JTextField ponDNi = new JTextField();
		ponDNi.setText("Mi dni aaa");
		File miTemplate = new File("src/com/rrhh/auxiliares/templates/informe_empleado.dotm");
		WordProcessing.createNewDocumentFromTemplate(miTemplate.getAbsolutePath());
		WordProcessing.typeTextAtBookmark("nombre", ponDNi.getText());
		WordProcessing.saveDocumentAsAndClose("Funciona");
		WordProcessing.exec();
		
	}

}
