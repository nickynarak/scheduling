/*
 * ################################################################
 *
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 1997-2011 INRIA/University of
 *                 Nice-Sophia Antipolis/ActiveEon
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 * ################################################################
 * $$PROACTIVE_INITIAL_DEV$$
 */
package org.ow2.proactive.scheduler.common.task.dataspaces;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.objectweb.proactive.annotation.PublicAPI;


/**
 * InputSelector is a couple of {@link FileSelector} and {@link InputAccessMode}
 *
 * @author The ProActive Team
 * @since ProActive Scheduling 1.1
 */
@PublicAPI
@XmlAccessorType(XmlAccessType.FIELD)
public class InputSelector implements Serializable {

    private static final long serialVersionUID = 60L;

    private FileSelector inputFiles = null;

    private InputAccessMode mode;

    public InputSelector() {
    }

    /**
     * Create a new instance of InputSelector
     *
     * @param inputFiles
     * @param mode
     */
    public InputSelector(FileSelector inputFiles, InputAccessMode mode) {
        this.inputFiles = inputFiles;
        this.mode = mode;
    }

    /**
     * Get the inputFiles
     *
     * @return the inputFiles
     */
    public FileSelector getInputFiles() {
        return inputFiles;
    }

    /**
     * Set the inputFiles value to the given inputFiles value
     *
     * @param inputFiles the inputFiles to set
     */
    public void setInputFiles(FileSelector inputFiles) {
        this.inputFiles = inputFiles;
    }

    /**
     * Get the mode
     *
     * @return the mode
     */
    public InputAccessMode getMode() {
        return mode;
    }

    /**
     * Set the mode value to the given mode value
     *
     * @param mode the mode to set
     */
    public void setMode(InputAccessMode mode) {
        this.mode = mode;
    }

    /**
     * Return a string representation of this selector.
     */
    public String toString() {
        return "(" + this.mode + "-" + this.inputFiles + ")";
    }

}