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
package les.opus.framework;

import les.opus.framework.persistence.Bundle;
import les.opus.framework.persistence.LocalPersistence;
import les.opus.framework.persistence.MemoryPersistence;
import les.opus.framework.persistence.GitPersistenceCommand;
import les.opus.framework.persistence.GitPersistenceTool;
import les.opus.framework.repository.AnalysisRepository;
import les.opus.framework.tool.PersistenceTool;
import les.opus.framework.tool.ToolRegister;



/**
 *
 * @author Daniel
 */
public class CollectorFramework {

    /**
     * Global Bundle to share data among tools
     */
    public static final Bundle globalBundle = new Bundle();
    
    
    private GitCollector colector;
    private AnalysisRepository repository;
    private CommitRange range = CommitRange.ALL;
    private String initialCommit;
    private String endCommit;
    private String singleCommit;

    /**
     * Get singleton instance of the framework
     * @return CollectorFramework's singleton instance
     */
    public static CollectorFramework GetInstance() {
        return new CollectorFramework();
    }

    private CollectorFramework() {
    }

    /**
     * Define the repository that will be mined.
     * @param repository
     * @return CollectorFramework's current instance
     */
    public CollectorFramework repository(AnalysisRepository repository) {
        this.repository = repository;
        this.colector = new GitCollector(repository);
        return this;
    }

    /**
     * Define a single commit that will be extracted.
     * @param commit
     * @return CollectorFramework's current instance
     */
    public CollectorFramework single(String commit) {
        range = CommitRange.SINGLE;
        this.singleCommit = commit;
        return this;
    }

    /**
     * Run the framework
     * @throws CollectorFrameworkException will be thrown if any hotspot is missing.
     */
    public void run() throws CollectorFrameworkException {

        if (repository == null) {
            throw new CollectorFrameworkException("Nenhum repositorio registrado");
        }
        if (colector.getTools().isEmpty()) {
            throw new CollectorFrameworkException("Nenhuma ferramenta registrada");
        }

        switch (range) {
            case SINGLE:
                colector.collect(singleCommit);
                break;
            case ALL:
                colector.all().collect();
        }

    }

    /**
     * Define the tools that will be used in the extraction.
     * @param register
     * @return CollectorFramework's current instance
     */
    public CollectorFramework tools(ToolRegister register) {
        colector.addTools(register.getPtools());
        return this;
    }

    /**
     * Add local persistence. If local persistence is selected, the analyzed project will be kept in local path.
     * @return CollectorFramework's current instance
     */
    public CollectorFramework saveLocalGit() {
        colector.addTool(new PersistenceTool(getGitTool(), new LocalPersistence(repository)));
        return this;
    }

    /**
     * Add memory persistence. If memory persistence is selected, the analyzed data will be kept in the global bundle.
     * @return CollectorFramework's current instance
     */
    public CollectorFramework saveMemoryGit() {
        colector.addTool(new PersistenceTool(getGitTool(), new MemoryPersistence(globalBundle)));
        return this;
    }

    private GitPersistenceTool getGitTool() {
        GitPersistenceTool tool = new GitPersistenceTool();
        tool.addCommand(new GitPersistenceCommand());
        return tool;
    }

    enum CommitRange {
        SINGLE,
        ALL,
//        BETWEEN,
//        SINCE_DATE,
//        BEFORE_DATE,
//        LIST
    }

}
