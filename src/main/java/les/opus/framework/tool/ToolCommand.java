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
 * Each command represents a single responsibility operation.
 * @author Daniel
 */
public interface ToolCommand {

    /**
     * Define if the command will be executed
     */
    boolean ENABLE = true;

    /**
     * Execute the command
     * @param state
     */
    public void run(ToolState state);

    /**
     * Get the command's name
     * @return command's name
     */
    public String getName();

    /**
     * Get the command's description
     * @return command's description
     */
    public String getDescription();
}
