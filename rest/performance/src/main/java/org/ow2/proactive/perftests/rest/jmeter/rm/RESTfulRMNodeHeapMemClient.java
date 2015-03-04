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
 *  Initial developer(s):               The ActiveEon Team
 *                        http://www.activeeon.com/
 *  Contributor(s):
 *
 * ################################################################
 * $ACTIVEEON_INITIAL_DEV$
 */
package org.ow2.proactive.perftests.rest.jmeter.rm;

import org.apache.jmeter.config.Arguments;
import org.ow2.proactive.perftests.rest.utils.MBeanQueries;


/**
 * Retrieves (JVM) heap memory consumption of each node. 
 */
public class RESTfulRMNodeHeapMemClient extends BaseRESTfulRMNodeClient {
    private static final String PARAM_HMEM_CLIENT_REFRESH_TIME = "hMemClientRefreshTime";

    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = super.getDefaultParameters();
        defaultParameters.addArgument(PARAM_HMEM_CLIENT_REFRESH_TIME, "${hMemClientRefreshTime}");
        return defaultParameters;
    }

    @Override
    protected String getQueryUrl(String serverUrl, String jmxUrl) {
        return MBeanQueries.getQueryUrl(serverUrl, MBeanQueries.getResourcePath(MBeanQueries.MBEAN_HEAP_MEM),
                jmxUrl, MBeanQueries.QUERY_HEAP_MEM);
    }

    @Override
    protected String getResourceDescription() {
        return "node-heap-memory";
    }
}