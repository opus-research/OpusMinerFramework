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

import les.opus.framework.repository.AnalysisRepository;
import les.opus.framework.tool.PersistenceTool;
import java.util.ArrayList;
import java.util.List;
import les.opus.framework.repository.RepositoryMining;
import org.repodriller.filter.range.Commits;

/**
 * Delegator responsible to connect the framework with repodriller.
 * @author Daniel
 */
public class GitCollector {

    private AnalysisRepository repository;
    private RepositoryMining mining;
    private ArrayList<PersistenceTool> tools = new ArrayList<>();

    /**
     * Constructor. Define the repository that will be analyzed.
     * @param repository
     */
    public GitCollector(AnalysisRepository repository) {
        this.repository = repository;
        mining = new RepositoryMining().in(repository);
    }

    /**
     * Return the selected persistence tools.
     * @return List of selected Persistence Tools
     */
    public ArrayList<PersistenceTool> getTools() {
        return tools;
    }

    /**
     * Add a list of persistence tools.
     * @param tools
     */
    public void addTools(List<PersistenceTool> tools) {
        this.tools.addAll(tools);
    }

    /**
     * Add a single persistence tool
     * @param tool
     */
    public void addTool(PersistenceTool tool) {
        this.tools.add(tool);
    }

    /**
     * Call repodriller.
     */
    public void collect() {

        for (PersistenceTool t : tools) {
            mining.process(t.getTool(), t.getPersistenceMechanism());
        }

        mining.mine();
    }

    /**
     * Run along all commits. 
     * @return Current instance of GitCollector
     */
    public GitCollector all() {
        mining.through(Commits.all());
        return this;
    }

    /**
     * Run in a range of commits
     * @param initialCommit
     * @param finalCommit
     */
    public void range(String initialCommit, String finalCommit) {
        mining.through(Commits.range(initialCommit, finalCommit));
    }

    /**
     * Run in a single commits. 
     * @param commit
     */
    public void collect(String commit) {
        mining.through(Commits.single(commit));
    }

    /**
     * Run in specific commits
     * @param commitList
     */
    public void collect(List<String> commitList) {
        mining.through(Commits.list(commitList));
    }

}
