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

import les.opus.framework.persistence.NoPersistence;
import les.opus.framework.persistence.PersistenceMechanism;
import java.util.ArrayList;
import java.util.List;

/**
 * Management system for tools
 * @author Daniel
 */
public class ToolRegister {

    private List<PersistenceTool> ptools = new ArrayList<>();

    /**
     * Registers a new tool. By default will be used NoPersistence mechanism.
     * @param newTool
     */
    public void registerTool(Tool newTool) {
        PersistenceTool t = new PersistenceTool(newTool, new NoPersistence());
        ptools.add(t);
    }

    /**
     * Register a tool with a specific persistence mechanism
     * @param newTool
     * @param persistence
     */
    public void registerTool(Tool newTool, PersistenceMechanism persistence) {
        PersistenceTool t = new PersistenceTool(newTool, persistence);
        ptools.add(t);
    }

    /**
     * Unregister a tool
     * @param tool
     */
    public void unregisterTool(Tool tool) {
        PersistenceTool toRemove = null;
        for (PersistenceTool pt : ptools) {
            if (pt.getTool().name().equals(tool.name())) {
                toRemove = pt;
            }
        }
        if (toRemove != null) {
            ptools.remove(toRemove);
        }
    }

    /**
     * Get the list of persistence tools
     * @return List of persistence tools
     */
    public List<PersistenceTool> getPtools() {
        return ptools;
    }

    
}
