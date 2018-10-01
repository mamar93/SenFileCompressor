package com.edacy.compression;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class Compression {
	 private File to_compress;
	 public Compression(File to_compress)
	 {
		 this.to_compress = to_compress;
		 
	 }
	 //methode permettant de recuperer les bytes a partir d'un fichier passe en parametre
	 private byte[] getArrayByteFromFile(File f) throws IOException {
		 
		 Path path = Paths.get(f.getAbsolutePath());
		 byte[] data = Files.readAllBytes(path);
		 return data;
	 
	 }
	 //cette methode permet de compresser le fichier mais elle est limitee , parce que quand le 
	 //fichier devient gros , la methode getArrayByteFromFile levera une exception de type array  size too large
	 public void compressIt() throws IOException
	 {
	 	 
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 byte[] input = getArrayByteFromFile(to_compress);
	     Deflater compressor = new Deflater();
	     compressor.setLevel(Deflater.BEST_COMPRESSION);//on prefere la qualite sur la rapidite
	     compressor.setInput(input);
	     compressor.finish();
	     byte[] buf = new byte[1024];
	     while (!compressor.finished())
	     {
	         int count = compressor.deflate(buf);
	         bos.write(buf, 0, count);
	     }
	     byte [] data=   bos.toByteArray();
	     OutputStream output = new FileOutputStream(to_compress);
	     output.write(data);
	     output.close();
	 }
	 //cette methode aussi comme celle au dessus permet de compresser un fichier avec la classe DeflaterOutputStream
	 //qui se base sur l'algorithme DEFLATE
	 public void deflate() throws Exception
	 {
		    System.out.println("Compresion...");
		    FileInputStream fis = new FileInputStream(to_compress.getAbsolutePath());
		    String cheminAbsolu = to_compress.getAbsolutePath();
		    String nomRep = to_compress.getName();
		    String finalPath = cheminAbsolu.substring(0,cheminAbsolu.lastIndexOf("\\")+1)+nomRep.substring(0,nomRep.indexOf('.'))+"_deflated"+".sfc";
	        FileOutputStream fos = new FileOutputStream(finalPath);
	        DeflaterOutputStream dos = new DeflaterOutputStream(fos);
	        doCopy(fis, dos); 
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
