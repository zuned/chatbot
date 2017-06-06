/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.googledrive;

/**
 * @author Nitin.Gupta
 *
 */

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;

public class DriveQuickstart {

	/** Application name. */
	private static final String APPLICATION_NAME = "Drive API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"),
			".credentials/drive-java-quickstart.json");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/drive-java-quickstart.json
	 */
	private static final List<String> SCOPES = Arrays
			.asList(DriveScopes.DRIVE_METADATA_READONLY);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static Credential authorize() throws IOException,
			GeneralSecurityException {
		/*
		 * // Load client secrets. InputStream in =
		 * DriveQuickstart.class.getResourceAsStream("/client_secret.json");
		 * GoogleClientSecrets clientSecrets =
		 * GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		 * 
		 * // Build flow and trigger user authorization request.
		 * GoogleAuthorizationCodeFlow flow = new
		 * GoogleAuthorizationCodeFlow.Builder( HTTP_TRANSPORT, JSON_FACTORY,
		 * clientSecrets, SCOPES) .setDataStoreFactory(DATA_STORE_FACTORY)
		 * .setAccessType("offline") .build();
		 * 
		 * Credential credential = new AuthorizationCodeInstalledApp( flow, new
		 * LocalServerReceiver()).authorize("user");
		 * 
		 * System.out.println( "Credentials saved to " +
		 * DATA_STORE_DIR.getAbsolutePath()); return credential;
		 */

		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(HTTP_TRANSPORT)
				.setJsonFactory(JSON_FACTORY)
				.setServiceAccountId("117540606509965029342")
				.setServiceAccountPrivateKeyFromP12File(
						new java.io.File(
								"C:/Users/nitin.gupta/Dropbox/Work/hCentive/Technology/Google Drive Module/google-app-access-50dfae1ab5be.p12"))
				.setServiceAccountScopes(
						Collections.singleton(DriveScopes.DRIVE))
				.setServiceAccountUser("nitin@ishimaya.com").build();

		/*
		 * GoogleCredential credential = GoogleCredential .fromStream( new
		 * FileInputStream(
		 * "C:/Users/nitin.gupta/Dropbox/Work/hCentive/Technology/Google Drive Module/google-app-access-3482db971655.json"
		 * )) .createScoped(Collections.singleton(DriveScopes.DRIVE));
		 */

		return credential;
	}

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static Drive getDriveService() throws IOException,
			GeneralSecurityException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	public List<File> search() throws IOException, GeneralSecurityException {
		FileList result = getDriveService().files().list().setPageSize(10)
				.set("name", "addtext_com_MTQ0NDMwNjkyMzU.jpg").execute();

		// Print the names and IDs for up to 10 files.
		/*
		 * FileList result = service.files().list().setMaxResults(10)
		 * .setFields("nextPageToken, files(id, name)").execute();
		 */
		List<File> files = result.getFiles();
		if (files == null || files.size() == 0) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf(file.getId());
			}
		}

		return files;
	}

	public Permission getAnonymousPermission(){
		Permission perm = new Permission();
		perm.setType("anyone");
		perm.setRole("reader");
		
		return perm;
	}
	
	public void createFolder() throws IOException, GeneralSecurityException {
		File fileMetadata = new File();
		fileMetadata.setName("TestByApi2");
		fileMetadata.setMimeType("application/vnd.google-apps.folder");
		
		Permission permission = getAnonymousPermission();
		
		ArrayList<Permission> permissions = new ArrayList<Permission>(0);
		permissions.add(permission);
		
//		fileMetadata.setPermissions(permissions);
		
		File file = getDriveService().files().create(fileMetadata)
				.setFields("id").execute();
		System.out.println("Folder ID: " + file.getId());
	}
	
	public void createFile(){
		
	}
	
	public void getFolderUrl(){
		
	}
	
	public void getFileUrl(){
		
	}

	public void addFileToFolder() throws IOException, GeneralSecurityException{
		String folderId = "0B4Q5lAdgrwLiQm80ekVhYWpmbVE";
		File fileMetadata = new File();
		fileMetadata.setName("photo.jpg");
		fileMetadata.setParents(Collections.singletonList(folderId));
		java.io.File filePath = new java.io.File("C:/Users/nitin.gupta/Downloads/6001 Rangsutra Series - 6.jpg");
		FileContent mediaContent = new FileContent("image/jpeg", filePath);
		File file = getDriveService().files().create(fileMetadata, mediaContent)
		        .setFields("id, parents")
		        .execute();
		System.out.println("File ID: " + file.getId());
	}
	
	public static void main(String[] args) throws IOException,
			GeneralSecurityException {
		// Build a new authorized API client service.

		DriveQuickstart quickstart = new DriveQuickstart();
		quickstart.addFileToFolder();
	}
}