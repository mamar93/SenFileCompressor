package com.edacy.compression;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterInputStream;

public class Decompression {
	 private File to_decompress;
	 public Decompression(File to_compress)
	 {
		 this.to_decompress = to_compress;
		 
	 }
	 
	 //Cette methode permet de decompresser le fichier avec la classe InflaterInputStream
	 public void inflate() throws Exception
	 {
		  System.out.println("Decompression..");
		 String cheminAbsolu = to_decompress.getAbsolutePath();
		    
		    String finalPath = cheminAbsolu.substring(0,cheminAbsolu.indexOf("_deflated"))+".sfc";
		    FileInputStream fis2 = new FileInputStream(to_decompress.getAbsolutePath());
	        InflaterInputStream iis = new InflaterInputStream(fis2);
	        FileOutputStream fos2 = new FileOutputStream(finalPath);
	 
	        doCopy(iis, fos2);
	 }
	 //methode permettant de copier le fichier dans l'autre en le lisant par ko
	 private void doCopy(InputStream is, OutputStream os) throws Exception {
	        byte[] bytes = new byte[1024];
	        int length;
	        int i = 0;
	        while ((length = is.read(bytes)) >= 0) {
	            os.write(bytes, 0, length);
	            i++;
	            System.out.println(sizeCompressed(i));
	        }
	        os.close();
	        is.close();
	    }
	 private String sizeCompressed(int i)
	 {
		 int ko = 0 ,mo = 0, go = 0;
		 mo = (int)(i /1024);
		 ko = i % 1024;
		 go = mo / 1000;
		 mo = mo % 1000;
		 String tor = "";
		 if(go != 0) tor+=go+"Go ";
		 if(mo != 0) tor+=mo+"Mo ";
		 if(ko != 0) tor+=ko+"Ko";
		 return tor+" Traite !";
		 
	 }
}
