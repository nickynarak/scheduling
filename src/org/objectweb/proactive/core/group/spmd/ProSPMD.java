/* 
* ################################################################
* 
* ProActive: The Java(TM) library for Parallel, Distributed, 
*            Concurrent computing with Security and Mobility
* 
* Copyright (C) 1997-2002 INRIA/University of Nice-Sophia Antipolis
* Contact: proactive-support@inria.fr
* 
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or any later version.
*  
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
* USA
*  
*  Initial developer(s):               The ProActive Team
*                        http://www.inria.fr/oasis/ProActive/contacts.html
*  Contributor(s): 
* 
* ################################################################
*/ 
package org.objectweb.proactive.core.group.spmd;

import org.objectweb.proactive.ActiveObjectCreationException;
import org.objectweb.proactive.ProActive;
import org.objectweb.proactive.core.group.Group;
import org.objectweb.proactive.core.group.ProActiveGroup;
import org.objectweb.proactive.core.mop.ClassNotReifiableException;
//import org.objectweb.proactive.core.mop.MOP;
import org.objectweb.proactive.core.node.Node;
import org.objectweb.proactive.core.node.NodeException;

/**
 * <p>
 * This class provides a static method to build (an deploy) an 'SPMD' group of active objects
 * with all references between them to communicate.
 * </p><p>
 * For instance, the following code builds objects of type <code>A</code> on nodes
 * <code>node1,node2,...</code>, with parameters <code>param1,param2,...</code>
 * and build for each object created its diffusion group to communicate with the others. 
 * </p>
 * <pre>
 * Object[] params = {param1,param2,...};
 * Node[] nodes = {node1,node2,...};
 * 
 * A group  =  (A) ProSPMD.newSPMDGroup("A", params, nodes);
 * </pre>
 * 
 * @version 1.0,  2003/10/09
 * @since   ProActive 1.0.3
 * @author Laurent Baduel
 */


public class ProSPMD {
/**
   * Creates an object representing a group (a typed group) and creates members with params cycling on nodeList.
   * This object represents the set of activities.
   * @param <code>className</code> the name of the (upper) class of the group's member. In this case of SPMD
   * prommaning the Class must extend <code>GroupMember</code>.
   * @param <code>params</code> the array that contain the parameters used to build the group's member.
   * @param <code>nodeList</code> the nodes where the members are created.
   * @return a typed group with its members. <code>null</code> if <code>className</code> does not extend
   * <code>GroupMember</code>.
   * @throws ActiveObjectCreationException if a problem occur while creating the stub or the body
   * @throws ClassNotFoundException if the Class corresponding to <code>className</code> can't be found.
   * @throws ClassNotReifiableException if the Class corresponding to <code>className</code> can't be reify.
   * @throws NodeException if the node was null and that the DefaultNode cannot be created
   */
	public static Object newSPMDGroup(String className, Object[][] params, Node[] nodeList)
		throws ClassNotFoundException, ClassNotReifiableException, ActiveObjectCreationException, NodeException {

//		if (!((MOP.forName(className)).isAssignableFrom(org.objectweb.proactive.core.group.spmd.SPMDMember.class))) {
//			System.out.println("Impossible to build a Group with this class : " + className);
//			return null;
//		}
		
		Object result = ProActiveGroup.newGroup(className);
		Group g = ProActiveGroup.getGroup(result);

		for (int i=0 ; i < params.length ; i++)
			g.add(ProActive.newActive(className, params[i], nodeList[i % nodeList.length]));

		for (int i=0 ; i < g.size() ; i++) {
			((SPMDMember)g.get(i)).setMyGroup(result);
		}
		
		ProActiveGroup.setScatterGroup(result);
	
		return result;
  }
}
