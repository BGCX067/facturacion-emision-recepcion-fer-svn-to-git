package com.beltsoft.jim.fer.negocio.util;

import java.io.File;

public class CBBMain {
	private static final String PDF_EXTENSION = "pdf";

	private static final String IMG_EXTENSION = "png";

	private static final String path = "/Users/Prospectiva/Desktop/pdfsCBB/";
	private static final String imagepath = path + "/images/";

	public static void main(String[] args) {

		System.out.println("--- START OF TEST ---");
		File[] files = new File(path).listFiles();
		for (File archivo : files) {
			if (!archivo.isDirectory()) {
				try {
					if (archivo.getName().endsWith(PDF_EXTENSION)) {
						System.out.println("Processing file "
								+ archivo.getName());
						long snapshotTime = System.currentTimeMillis();
						generarImagenJmupdf(archivo.getName().substring(0,
								archivo.getName().lastIndexOf(".")));
//						generarImagenPdfRenderer(archivo.getName().substring(0,
//								archivo.getName().lastIndexOf(".")));
						System.out
								.println("PDF to image process elapsed time (in milis): "
										+ (System.currentTimeMillis() - snapshotTime));
						leerQR(archivo.getName().substring(0,
								archivo.getName().lastIndexOf(".")));
						System.out
								.println("Full process elapsed time (in milis): "
										+ (System.currentTimeMillis() - snapshotTime));
						System.out.println();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("--- END OF TEST ---");
	}

//	private static void generarImagenPdfRenderer(String name) {
//		RandomAccessFile raf;
//		try {
//			raf = new RandomAccessFile(path + name + "." + PDF_EXTENSION, "r");
//
//			FileChannel channel = raf.getChannel();
//			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
//					channel.size());
//			PDFFile pdffile = new PDFFile(buf);
//			// draw the first page to an image
//			int num = pdffile.getNumPages();
//			PDFPage page = pdffile.getPage(0);
//
//			// get the width and height for the doc at the default zoom
//			int width = (int) page.getBBox().getWidth();
//			int height = (int) page.getBBox().getHeight();
//
//			Rectangle rect = new Rectangle(0, 0, width, height);
//			int rotation = page.getRotation();
//			Rectangle rect1 = rect;
//			if (rotation == 90 || rotation == 270)
//				rect1 = new Rectangle(0, 0, rect.height, rect.width);
//
//			// generate the image
//			BufferedImage img = (BufferedImage) page.getImage(rect.width,
//					rect.height, // width & height
//					rect1,null);// clip rect
//			
//			ImageIO.write(img, IMG_EXTENSION, new File(imagepath
//					+ name + "." + IMG_EXTENSION));
//
//		} catch (FileNotFoundException e1) {
//			System.err.println(e1.getLocalizedMessage());
//		} catch (IOException e) {
//			System.err.println(e.getLocalizedMessage());
//		}
//
//	}

	private static void generarImagenJmupdf(String name) {
		float scale = 2.0f;
		int rotation = -1;
		int pageNumber = 1;

//		try {
//			com.jmupdf.pdf.PdfDocument pdfDoc = new com.jmupdf.pdf.PdfDocument(
//					path + name + "." + PDF_EXTENSION, "");
//
//			com.jmupdf.page.Page page = pdfDoc.getPage(pageNumber);
//
//			com.jmupdf.page.PageRenderer render = new com.jmupdf.page.PageRenderer(
//					page, scale, rotation,
//					com.jmupdf.enums.ImageType.IMAGE_TYPE_RGB);
//			render.render(true);
//
//			ImageIO.write(render.getImage(), IMG_EXTENSION, new File(imagepath
//					+ name + "." + IMG_EXTENSION));
//
//			render.dispose();
//			pdfDoc.dispose();
//		} catch (DocException e) {
//			e.printStackTrace();
//		} catch (DocSecurityException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	private static void leerQR(String name) {
//		File jdField_if = new File(imagepath + name + "." + IMG_EXTENSION);
//		Object localObject1 = null;
//		p jdField_do = new p();
//
//		try {
//			jdField_do.q = new Properties();
//			jdField_do.q.put("QR", "true");
//			jdField_do.q.put("austra1iaTab1e", "true");
//			jdField_do.p = true;
//			localObject1 = ImageIO.read(jdField_if);
//		} catch (IOException localIOException) {
//		}
//		if (localObject1 == null) {
//			System.out.println("[ ]");
//			return;
//		}
//		b localb = new b((RenderedImage) localObject1);
//		ac[] arrayOfac = localb.a(jdField_do);
//		if (arrayOfac.length > 0) {
//			System.out.println(arrayOfac[0].c());
//		}
	}
}
