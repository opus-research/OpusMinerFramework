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

package les.opus.framework.tool;

import les.opus.framework.persistence.Bundle;
import les.opus.framework.persistence.PersistenceMechanism;
import java.util.ArrayList;
import java.util.List;
import les.opus.framework.tool.state.ExecutionState;
import les.opus.framework.tool.state.PosExecutionState;
import les.opus.framework.tool.state.PreExecutionState;
import org.repodriller.domain.Commit;
import org.repodriller.scm.SCMRepository;

/**
 * The tools are the heart of the framework, they are responsible for managing
 * all the operations that will be performed on the data collected. 
 * To do this, you can add a list of commands. It is the function of the tool to 
 * decide when and which commands should be executed at each moment of the analysis
 * @author Daniel
 */
public abstract class Tool implements CommitVisitor{

    private List<ToolCommand> commands = new ArrayList<>();

    /**
     * Add a command
     * @param command
     * @return Current instance of the tool.
     */
    public Tool addCommand(ToolCommand command) {
        commands.add(command);
        return this;
    }

    @Override
    public abstract String name();

    @Override
    public void initialize(SCMRepository repo, PersistenceMechanism writer) {
        runCommands(new PreExecutionState(repo,writer));
    }

    @Override
    public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
        runCommands(new ExecutionState(repo,commit,writer));
    }

    @Override
    public void finalize(SCMRepository repo, PersistenceMechanism writer, Bundle memoryBundle) {
        runCommands(new PosExecutionState(repo,writer,memoryBundle));
    }

    /**
     * Return tool's description
     * @return
     */
    public abstract String getDescription();

    /**
     * Return tool's offered properties
     * @return
     */
    public abstract ArrayList<String> offeredProperties();

    /**
     * Run the commands
     * @param state currente analyze's state
     */
    protected void runCommands(ToolState state) {
        commands.forEach((tc) -> {
            tc.run(state);
        });
    }

    /**
     * Return all the commands
     * @return List of commands
     */
    public List<ToolCommand> getCommands() {
        return commands;
    }

    
}
