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
 * A NoPersistence is a PersistenceMechanism that ignores all method calls.
 * It exists so that code that works with PersistenceMechanisms can avoid NPEs without if-guards.
 *
 * @author Mauricio Aniche
 */
public class NoPersistence implements PersistenceMechanism {

	@Override
	public void write(Bundle bundle) {

	}

	@Override
	public void close() {

	}

}
