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

/**
 * The framework provides data collected through states, indicating the current 
 * stage of the repository's mining. Information about the repository, commits, 
 * persistence, and data from previously processed tools is made available to the 
 * developer at each mining stage (before, during and after).
 * @author danie
 */
public interface ToolState {

    /**
     * Get the data
     * @return
     */
    Object[] getStateData();
    
}
