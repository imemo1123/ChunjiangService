package cn.memo.mail;

import java.io.File;  
import java.io.FileInputStream;  
  
import javax.print.Doc;  
import javax.print.DocFlavor;  
import javax.print.DocPrintJob;  
import javax.print.PrintService;  
import javax.print.PrintServiceLookup;  
import javax.print.ServiceUI;  
import javax.print.SimpleDoc;  
import javax.print.attribute.DocAttributeSet;  
import javax.print.attribute.HashDocAttributeSet;  
import javax.print.attribute.HashPrintRequestAttributeSet;  
import javax.swing.JFileChooser;  

public class PrintDemo {
	public static void main(String[] args) {
		JFileChooser fileChooser = new JFileChooser();
		//int state = fileChooser.showOpenDialog(null);
		//if (state == fileChooser.APPROVE_OPTION) {
			File file = new File("D:/test.txt");
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			//PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
			if(defaultService != null){
				try{
					DocPrintJob job =  defaultService.createPrintJob();
					FileInputStream fis = new FileInputStream(file);
					DocAttributeSet das =new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);
					job.print(doc, pras);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		//}
	}
}
