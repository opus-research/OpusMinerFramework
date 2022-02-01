/*
 * Copyright 2018 Daniel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package les.opus.framework.persistence;

import les.opus.framework.repository.AnalysisRepository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.repodriller.domain.Commit;

/**
 * Default persistence for save local files
 * @author Daniel
 */
public class LocalPersistence implements PersistenceMechanism {

    private AnalysisRepository repository;
    private String relativePath;
    private String commitPath;

    /**
     * Define the repository that will be saved
     * @param repository
     */
    public LocalPersistence(AnalysisRepository repository) {
        this.repository = repository;
        createProjectFolder();
    }

    private void createProjectFolder() {
        relativePath = "./projects/" + repository.getProjectName();
        File projectPath = new File(relativePath);
        projectPath.mkdirs();
    }

    private void createCommitFolder(String commit) {
        commitPath = relativePath + "/" + commit;
        File projectPath = new File(relativePath + "/" + commit);
        projectPath.mkdir();
    }

    /**
     * Write the local data.
     * @param subBundle
     */
    @Override
    public void write(Bundle subBundle) {
        try {
            Commit commit = (Commit) subBundle.get("commit");
            createCommitFolder(commit.getHash());

            //write code
            File f = new File(commitPath + "/code");
            f.mkdir();
            commit.getModifications().forEach((m) -> {
                try {
                    String fileName = m.getFileName().substring(m.getFileName().lastIndexOf("/"));
                    write(f.getAbsolutePath(), fileName, m.getSourceCode());
                } catch (Exception e) {
                    System.err.println("Commit só possui modificação em arquivos de config");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write(String path, String fileName, String content) {
        File f = new File(path + "/" + fileName);
        try {
            FileWriter writer = new FileWriter(f);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Close the persistence
     */
    @Override
    public void close() {

    }

}
