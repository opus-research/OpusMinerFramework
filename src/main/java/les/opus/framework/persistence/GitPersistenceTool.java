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

import les.opus.framework.tool.Tool;
import java.util.ArrayList;


/**
 * Default Tool to allow collect projects data
 * @author Daniel
 */
public class GitPersistenceTool extends Tool {
 /**
     * Get the tool's name
     * @return String. Tool's name
     */
    @Override
    public String name() {
        return "persistenceGit";
    }

    /**
     * Get the tool's description
     * @return String. Tool's descriptions
     */
    @Override
    public String getDescription() {
        return "Uma ferramenta para armazenar os dados minerados de um projeto do git";
    }

    /**
     * Lists the offered properties. Offered properties are the produced data after the tool's run.
     * @return Tool's offered properties
     */
    @Override
    public ArrayList<String> offeredProperties() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add("Codigo fonte");
        properties.add("Diff");
        properties.add("Autor do commit");
        return properties;
    }


}
