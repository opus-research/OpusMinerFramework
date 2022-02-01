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
package les.opus.framework.tool.state;

import les.opus.framework.persistence.PersistenceMechanism;
import les.opus.framework.tool.ToolState;
import org.repodriller.scm.SCMRepository;

/**
 * State used before the execution of the framework.
 *
 * @author Daniel
 */
public class PreExecutionState implements ToolState {

    private SCMRepository repo;
    private PersistenceMechanism writer;

    /**
     * Define the current data.
     *
     * @param repo
     * @param writer
     */
    public PreExecutionState(SCMRepository repo, PersistenceMechanism writer) {
        this.repo = repo;
        this.writer = writer;
    }

    @Override
    public Object[] getStateData() {
        return new Object[]{this};
    }

    /**
     * Get the repodriller repository
     *
     * @return
     */
    public SCMRepository getRepo() {
        return repo;
    }

    /**
     * Get the mechanism persistence
     *
     * @return
     */
    public PersistenceMechanism getWriter() {
        return writer;
    }
}
