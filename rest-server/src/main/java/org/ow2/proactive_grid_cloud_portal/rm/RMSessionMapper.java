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
 * $$ACTIVEEON_INITIAL_DEV$$
 */
package org.ow2.proactive_grid_cloud_portal.rm;

import org.objectweb.proactive.api.PAActiveObject;
import org.ow2.proactive.resourcemanager.common.util.RMProxyUserInterface;
import org.ow2.proactive_grid_cloud_portal.common.SessionIdGenerator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class RMSessionMapper {

    private Map<String, RMProxyUserInterface> sessions;
    private static RMSessionMapper sessionMapper;
    private Map<String, Long> sessionsLastAccessToClient;

    private RMSessionMapper() {
        sessions = new ConcurrentHashMap<String, RMProxyUserInterface>();
        sessionsLastAccessToClient = new ConcurrentHashMap<String, Long>();
    }

    public static synchronized RMSessionMapper getInstance() {
        if (sessionMapper == null) {
            sessionMapper = new RMSessionMapper();
        }
        return sessionMapper;
    }

    public String add(RMProxyUserInterface rm) {
        String id = SessionIdGenerator.newSessionId();
        sessions.put(id, rm);
        sessionsLastAccessToClient.put(id, System.currentTimeMillis());
        return id;
    }

    public Map<String, RMProxyUserInterface> getSessionsMap() {
        return sessions;
    }

    public Map<String, Long> getSessionsLastAccessToClient() {
        return sessionsLastAccessToClient;
    }

    /**
     * Remove the proxy associated to the session id <code>key</code>
     * This method also terminates the active object used as proxy 
     * @param key the session id
     */
    public void remove(String key) {
        RMProxyUserInterface proxy = sessions.remove(key);
        try {
            PAActiveObject.terminateActiveObject(proxy, true);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        sessionsLastAccessToClient.remove(key);
    }
}
