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

import les.opus.framework.tool.ToolCommand;
import les.opus.framework.tool.ToolState;
import les.opus.framework.tool.state.ExecutionState;

/**
 * Default Command to allow collect projects data
 * @author Daniel
 */
public class GitPersistenceCommand implements ToolCommand {

    /**
     * Run the command
     * @param state
     */
    @Override
    public void run(ToolState state) {
        if (state instanceof ExecutionState) {

            ExecutionState ee = (ExecutionState) state;
            PersistenceMechanism persistence = (PersistenceMechanism) ee.getWriter();

            Bundle b = new Bundle();
            b.put("commit", ee.getCommit());
            persistence.write(b);
        }

    }

    /**
     * Get the command's name
     * @return String. Command's name
     */
    @Override
    public String getName() {
        return "Git Persistence";
    }

    /**
     * Get the command's description
     * @return String. Command's descriptions
     */
    @Override
    public String getDescription() {
        return "Coleta dados dos repositorios";
    }
    
    

}
