/*******************************************************************************
 * Copyright 2018, Julius Krah
 * by the @authors tag. See the LICENCE in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.juliuskrah.jasper.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import com.juliuskrah.jasper.ApplicationProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Julius Krah
 *
 */
@Slf4j
@Component
public class FileSystemStorageService implements StorageService {
	private final Path rootLocation;
	private final ApplicationProperties properties;

	public FileSystemStorageService(ApplicationProperties properties) throws IOException {
		this.properties = properties;
		this.rootLocation = Paths.get(properties.getStorageLocation().getURL().getPath());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#init()
	 */
	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#deleteAll()
	 */
	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not delete files and folders", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jrxmlFileExists(java.lang.
	 * String)
	 */
	@Override
	public boolean jrxmlFileExists(String file) {
		// @formatter:off
		try {
			Path reportFile = Paths.get(properties.getReportLocation().getURI());
			reportFile = reportFile.resolve(file + ".jrxml");
			if (Files.exists(reportFile))
				return true;
		} catch (IOException e) {
			log.error("Error while trying to get file URI", e);
			return false;
		}
		// @formatter:on
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jasperFileExists(java.lang.
	 * String)
	 */
	@Override
	public boolean jasperFileExists(String file) {
		Path reportFile = rootLocation;
		reportFile = reportFile.resolve(file + ".jasper");
		if (Files.exists(reportFile))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#loadJrxmlFile(java.lang.String)
	 */
	@Override
	public String loadJrxmlFile(String file) {
		// @formatter:off
		try {
			Path reportFile = Paths.get(properties.getReportLocation().getURI());
			reportFile = reportFile.resolve(file + ".jrxml");
			return reportFile.toString();
		} catch (IOException e) {
			log.error("Error while trying to get file prefix", e);
			throw new StorageFileNotFoundException("Could not load file", e);
		}
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#loadJasperFile(java.lang.String)
	 */
	@Override
	public File loadJasperFile(String file) {
		Path reportFile = rootLocation;
		reportFile = reportFile.resolve(file + ".jasper");
		return reportFile.toFile();
	}

}
