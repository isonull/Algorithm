//// I, Zijun Joe Yan zy275, student of Algorithms at the University of
//// Cambridge, am the author of this code and on <2017-02-10>
// If you want to use this .java file, try link the PDFBOX library which is free
//// and open-source.
// package rb234TreeAnimation;
//
// import java.io.File;
// import java.io.IOException;
// import java.util.Arrays;
// import java.util.Comparator;
//
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
// import org.apache.pdfbox.pdmodel.PDPageContentStream;
// import org.apache.pdfbox.pdmodel.common.PDRectangle;
// import org.apache.pdfbox.pdmodel.font.PDFont;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;
// import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//
// public class PDFIO {
// public static void deleteDir(File path) {
// if (!path.exists())
// return;
// if (path.isFile()) {
// path.delete();
// return;
// }
// File[] files = path.listFiles();
// for (int i = 0; i < files.length; i++) {
// deleteDir(files[i]);
// }
// path.delete();
// }
//
// public static void initializeEnvir() {
// File RBTreeDir = new File("RBTreePicture");
// File TTFTreeDir = new File("TTFTreePicture");
// deleteDir(RBTreeDir);
// deleteDir(TTFTreeDir);
// RBTreeDir.mkdirs();
// TTFTreeDir.mkdirs();
// }
//
// public static void generateJPG(String str) throws Exception {
// RBTree rbTree = new RBTree();
// TTFTree ttfTree = new TTFTree();
// Exception ilegalInput = new Exception("The input String is ilegal.");
// char c1;
// char c2 = '.';
// for (int i = 0; i < str.length(); ++i) {
// c1 = str.charAt(i);
// if (i < str.length() - 1) {
// c2 = str.charAt(i + 1);
// }
// if ((c1 <= 'Z' && c1 >= 'A') || (c1 <= '9' && c1 >= '0')) {
// rbTree.insert(c1);
// ttfTree.insert(c1);
// } else {
// if (c1 == '<') {
// rbTree.predecessor(c2);
// ttfTree.predecessor(c2);
// ++i;
// } else if (c1 == '?') {
// rbTree.search(c2);
// ttfTree.search(c2);
// ++i;
// } else if (c1 == ' ') {
// } else {
// throw ilegalInput;
// }
// }
// }
// }
//
// @SuppressWarnings("deprecation")
// public static void generatePDF(File dirPath, String fileName, String input)
//// throws IOException {
// PDRectangle pageSize = new PDRectangle(1024, 768);
// PDDocument doc = null;
// PDPage page = null;
//
// doc = new PDDocument();
// page = new PDPage(pageSize);
// doc.addPage(page);
// PDFont font = PDType1Font.HELVETICA_BOLD;
// PDPageContentStream content = new PDPageContentStream(doc, page);
//
// content.setNonStrokingColor(0, 0, 0); // gray background
// content.fillRect(0, 0, 1024, 768);
//
// // draw text
// content.setNonStrokingColor(255, 255, 255); // black text
// content.beginText();
// content.setFont(font, 10);
// content.moveTextPositionByAmount(0, 0);
// content.drawString(
// "I, Joe Yan zy275, student of Algorithms at the University of Cambridge, am
//// the author of this pdf and on <2017-02-10>");
// content.setFont(font, 30);
// content.moveTextPositionByAmount(0, 100);
// content.drawString("INPUT: " + input);
// content.endText();
// content.close();
//
// File jpgFile[] = dirPath.listFiles();
// Arrays.sort(jpgFile, new Comparator<File>() {
// @Override
// public int compare(File o1, File o2) {
// if (o1.isHidden() || o2.isHidden()) {
// return 0;
// }
// String n1 = o1.getName();
// String n2 = o2.getName();
// return Integer.parseInt(n1.substring(0, n1.length() - 4))
// - Integer.parseInt(n2.substring(0, n2.length() - 4));
// }
// });
//
// PDImageXObject image;
//
// for (int i = 0; i < jpgFile.length; ++i) {
// if (!jpgFile[i].isHidden()) {
// page = new PDPage(pageSize);
// doc.addPage(page);
// content = new PDPageContentStream(doc, page);
// image = PDImageXObject.createFromFileByContent(jpgFile[i], doc);
// content.drawImage(image, 0, 0);
// content.close();
// }
// }
//
// doc.save(fileName);
// doc.close();
// }
//
// public static void main(String args[]) throws Exception {
// initializeEnvir();
//
// File RBTreeDir = new File("RBTreePicture");
// File TTFTreeDir = new File("TTFTreePicture");
// String input = "ALGORITHMS CAMBRIDGE 2017 YOURCRSID ?A ?Z <M";
//
// PDFIO.generateJPG(input);
//
// generatePDF(RBTreeDir, "RBTree.pdf", input);
// generatePDF(TTFTreeDir, "TTFTree.pdf", input);
//
// initializeEnvir();
// }
// }
