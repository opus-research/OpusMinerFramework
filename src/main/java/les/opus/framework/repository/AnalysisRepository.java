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
package les.opus.framework.repository;

import java.io.File;
import org.repodriller.scm.GitRemoteRepository;
import org.repodriller.scm.GitRepository;
import org.repodriller.scm.SCM;
import org.repodriller.scm.SCMRepository;

/**
 * Class for specify the repository that will be mined.
 * @author Daniel
 */
public class AnalysisRepository extends SCMRepository {

    private String url;
    private Source source;
    private String projectName;

    /**
     * Create new instance
     * @param url
     * @param source
     * @param projectName
     * @return AnalysisRepository's instance
     */
    public static AnalysisRepository buildRepository(String url, Source source, String projectName) {
        String projectPath = url;

        if (source.equals(Source.GIT)) {
            projectPath = "./temp/" + projectName;
            if (!checkIfProjectAlreadyDownloaded(projectPath)) {
                GitRemoteRepository.hostedOn(url).inTempDir(projectPath).buildAsSCMRepository();
            }
        }
        SCMRepository scmr = GitRepository.singleProject(projectPath);
        return new AnalysisRepository(url, source, projectName, scmr.getScm(),
                scmr.getOrigin(), scmr.getPath(), scmr.getHeadCommit(), scmr.getFirstCommit());
    }

    private AnalysisRepository(String url, Source source, String projectName,
            SCM scm, String origin, String path, String headCommit, String firstCommit) {
        super(scm, origin, path, headCommit, firstCommit);
        this.url = url;
        this.source = source;
        this.projectName = projectName;
    }

    private static boolean checkIfProjectAlreadyDownloaded(String path) {
        File checkPath = new File(path);
        return checkPath.isDirectory();
    }

    /**
     * Return repository url
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Return repository source type
     * @return
     */
    public Source getSource() {
        return source;
    }

    /**
     * Return project name
     * @return
     */
    public String getProjectName() {
        return projectName;
    }

}
