package com.npst.upi.portal.merchant.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility extends SimpleFileVisitor<Path> {

	private static ZipOutputStream zos;
	private Path sourceDir;

	public ZipUtility(Path sourceDir) {
		this.sourceDir = sourceDir;
	}

	public static String zipFiles(final List<String> filePaths, final String basePath) throws Exception {
		File firstFile = new File(filePaths.get(0));
		String zipFileName = basePath + firstFile.getName().replace(".pdf", "").concat(".zip");
		Path path = Paths.get(basePath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		FileOutputStream fos = new FileOutputStream(zipFileName);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (String aFile : filePaths) {
			zos.putNextEntry(new ZipEntry(new File(aFile).getName()));
			byte[] bytes = Files.readAllBytes(Paths.get(aFile));
			zos.write(bytes, 0, bytes.length);
			zos.closeEntry();
		}
		zos.close();
		return zipFileName;
	}

	public static String zipDirectory(final String dirPath, final String basePath) throws Exception {
		Path sourceDir = Paths.get(dirPath);
		String zipFileName = generateFileName(basePath, dirPath);
		zos = new ZipOutputStream(new FileOutputStream(zipFileName));
		Files.walkFileTree(sourceDir, new ZipUtility(sourceDir));
		zos.close();
		return zipFileName;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
		Path targetFile = sourceDir.relativize(file);
		zos.putNextEntry(new ZipEntry(targetFile.toString()));
		byte[] bytes = Files.readAllBytes(file);
		zos.write(bytes, 0, bytes.length);
		zos.closeEntry();
		return FileVisitResult.CONTINUE;
	}

	public static String generateFileName(final String basePath, final String dirPath) throws Exception {
		StringBuilder builder=new StringBuilder();
		//builder.append(dirPath.substring(dirPath.lastIndexOf("/") + 1, dirPath.length()).concat(".zip"));
		builder.append(dirPath.replace("/", "_" ).concat(".zip"));
		return builder.toString();
	}
}
