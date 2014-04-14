/**
 *  Copyright 2012-2014 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.model.assignment;

import java.util.HashSet;
import java.util.Set;
import org.mapstruct.ap.model.Assignment;

import org.mapstruct.ap.model.common.ModelElement;
import org.mapstruct.ap.model.common.Type;

/**
 * An inline conversion between source and target type of a mapping.
 *
 * @author Gunnar Morling
 */
public class TypeConversion extends ModelElement implements Assignment {


    private final Set<Type> importTypes;
    private final Set<Type> exceptionTypes;
    private final String openExpression;
    private final String closeExpression;

    /**
     * A reference to mapping method in case this is a two-step mapping, e.g. from
     * {@code JAXBElement<Bar>} to {@code Foo} to for which a nested method call will be generated:
     * {@code setFoo(barToFoo( jaxbElemToValue( bar) ) )}
     */
    private Assignment assignment;


    TypeConversion( Set<Type> importTypes,
            Set<Type> exceptionTypes,
            String openExpression,
            String closeExpression ) {
        this.importTypes = new HashSet<Type>( importTypes );
        this.importTypes.addAll( exceptionTypes );
        this.exceptionTypes = exceptionTypes;
        this.openExpression = openExpression;
        this.closeExpression = closeExpression;
    }

    @Override
    public Set<Type> getImportTypes() {
        return importTypes;
    }

    @Override
    public Set<Type> getExceptionTypes() {
        return exceptionTypes;
    }

    public String getOpenExpression() {
        return openExpression;
    }

    public String getCloseExpression() {
        return closeExpression;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    @Override
    public String getSourceReference() {
        return assignment.getSourceReference();
    }

    @Override
    public void setAssignment( Assignment assignment ) {
        this.assignment = assignment;
    }

    @Override
    public boolean isSimple() {
        return false;
    }
}