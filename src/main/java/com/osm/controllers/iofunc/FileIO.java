package com.osm.controllers.iofunc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileIO {
	@SuppressWarnings("unused")
	private static Logger logger=Logger.getLogger(FileIO.class);
	
	public byte[] getFile(String filenamewithpath, HttpServletRequest request, Boolean isPhoto) throws IOException{
		System.out.println("-----------------------------------------");
		System.out.println("File name with path => "+ filenamewithpath);
		System.out.println("Is Photo => "+isPhoto);
		System.out.println("-----------------------------------------");
		String uploadsDir = "/uploads"+filenamewithpath;
		String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
		File myFile = new File(realPathtoUploads);
		String myDir = myFile.getParent();
        System.out.println("Real Path to Uploads =>"+myDir);
        if(! new File(myDir).exists())
        {
        	System.out.println("Location not exist...");
            new File(myDir).mkdirs();
        }else{
        	 System.out.println("Location already existed...");
        }
		String rpath = request.getServletContext().getRealPath(uploadsDir);
		System.out.println("Real path of the file => "+rpath);
		Path path = Paths.get(rpath);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		}catch (IOException e){
			data = null;
			if(isPhoto){
				System.out.println("Try to load default photo when no valid photo existed/found...");
				Resource resource = null;
				resource = new ClassPathResource("images/no-image-available.jpg");
				FileInputStream fileInputStream = null;
				try{
					File file = resource.getFile();
					byte[] bFile = new byte[(int) file.length()];
					fileInputStream = new FileInputStream(file);
				    fileInputStream.read(bFile);
				    fileInputStream.close();
				    System.out.println("no-image-available.jpg data fetched...");
				    return bFile;
				}catch(Exception e1){
					data = null;
					System.out.println("Fail to read default image...");
					System.out.println("ERROR: "+e1.getLocalizedMessage());
				}finally{
					fileInputStream.close();
					resource = null;
				}
			}
			e.printStackTrace();
		}
		return data;
	}
	
	public boolean saveFile(HttpServletRequest request, String path, MultipartFile file,String newFileName){
		if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads"+path;
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                
                System.out.println("Real Path to Uploads =>"+realPathtoUploads);
                
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdirs();
                }else{
                	System.out.println("Another same name file already exist...try to overwrite");
                }
                
                String orgName = null;
                
                if(newFileName != ""){
                	orgName = newFileName;
                }else{
                	orgName = file.getOriginalFilename();
                }
                
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                file.transferTo(dest);
                System.out.println("File saved successfully => "+uploadsDir+orgName);
            }catch(Exception e){
            	e.printStackTrace();
            	return false;
            }
            return true;
		}else{
			System.out.println("Sorry, File is empty...");
			return false;
		}
	}
}
