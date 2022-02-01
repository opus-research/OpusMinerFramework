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

import les.opus.framework.repository.AnalysisRepository;
import java.util.HashMap;

/**
 * Class for hold data
 * @author Daniel
 */
public class Bundle {

    private HashMap<String, Object> properties = new HashMap<>();

    /**
     * New clean instance
     */
    public Bundle() {

    }

    /**
     * New instance with repository propertie
     * @param repository
     */
    public Bundle(AnalysisRepository repository) {
        properties.put("projectName", repository.getProjectName());
    }

    /**
     * Get all properties
     * @return A HashMap<String, Object> containing all properties
     */
    public HashMap<String, Object> getAllProperties(){
        return properties;
    }

    /**
     * Add a new propertie.
     * @param key Propertie's key
     * @param value Propertie's value
     */
    public void put(String key, Object value) {
        properties.put(key, value);
    }

    /**
     * Get a specific propertie value
     * @param key
     * @return Object. The propertie value
     */
    public Object get(String key) {
        return properties.get(key);
    }

}
