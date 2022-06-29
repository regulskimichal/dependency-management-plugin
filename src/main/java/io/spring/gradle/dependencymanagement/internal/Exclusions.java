/*
 * Copyright 2014-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.gradle.dependencymanagement.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A set of dependency exclusions.
 *
 * @author Andy Wilkinson
 */
class Exclusions {

	private final Map<String, Set<Exclusion>> exclusionsByDependency = new HashMap<String, Set<Exclusion>>();

	void add(String dependency, Collection<Exclusion> exclusionsForDependency) {
		if (exclusionsForDependency.isEmpty()) {
			return;
		}
		Set<Exclusion> exclusions = this.exclusionsByDependency.get(dependency);
		if (exclusions == null) {
			exclusions = new HashSet<Exclusion>();
			this.exclusionsByDependency.put(dependency, exclusions);
		}
		exclusions.addAll(exclusionsForDependency);
	}

	void addAll(Exclusions exclusions) {
		for (Map.Entry<String, Set<Exclusion>> entry : exclusions.exclusionsByDependency.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}
	}

	Set<Exclusion> exclusionsForDependency(String dependency) {
		return this.exclusionsByDependency.get(dependency);
	}

	@Override
	public String toString() {
		return this.exclusionsByDependency.toString();
	}

}
