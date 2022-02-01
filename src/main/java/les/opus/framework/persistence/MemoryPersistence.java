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

/**
 * Default persistence for save data in memory
 * @author Daniel
 */
public class MemoryPersistence implements PersistenceMechanism {

    private Bundle globalBundle;
    
    /**
     * Define the global bundles 
     * @param globalBundle
     */
    public MemoryPersistence(Bundle globalBundle) {
        this.globalBundle = globalBundle;
    }

    /**
     * Write the data in memory
     * @param subBundle
     */
    @Override
    public void write(Bundle subBundle) {
        for (String s : subBundle.getAllProperties().keySet()) {
            Object object = subBundle.get(s);
            globalBundle.put(s, object);
        }
    }

    /**
     * Close the persistence
     */
    @Override
    public void close() {

    }

}
