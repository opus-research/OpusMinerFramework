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

import les.opus.framework.persistence.PersistenceMechanism;

/**
 * Persistence tool are tools related with a persistence mechanism. 
 * @author Daniel
 */
public class PersistenceTool{
    
    private Tool tool;
    private PersistenceMechanism persistenceMechanism;

    /**
     * Create a instance of persistence tool.
     * @param tool
     * @param persistenceMechanism
     */
    public PersistenceTool(Tool tool, PersistenceMechanism persistenceMechanism) {
        this.tool = tool;
        this.persistenceMechanism = persistenceMechanism;
    }

    /**
     * Get the tool
     * @return
     */
    public Tool getTool() {
        return tool;
    }

    /**
     * Get the persistence mechanism
     * @return
     */
    public PersistenceMechanism getPersistenceMechanism() {
        return persistenceMechanism;
    }

    
}
