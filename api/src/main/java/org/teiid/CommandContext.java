/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package org.teiid;

import java.io.Serializable;
import java.sql.Statement;
import java.util.Map;
import java.util.TimeZone;

import javax.security.auth.Subject;

import org.teiid.adminapi.DataPolicy;
import org.teiid.adminapi.Session;
import org.teiid.adminapi.VDB;
import org.teiid.jdbc.TeiidSQLWarning;

/**
 * Context information for the currently executing command.
 * Can be used as an argument to UDFs.
 */
public interface CommandContext {
	
	/**
	 * Get the current user name
	 * @return
	 */
	String getUserName();
	
	/**
	 * Get the current vdb name
	 * @return
	 */
	String getVdbName();
	
	/**
	 * Get the current vdb version
	 * @return
	 */
	int getVdbVersion();
	
	/**
	 * Get the connection id
	 * @return
	 */
	String getConnectionId();
	
	/**
	 * Get the connection id
	 * @return
	 * @deprecated see {@link #getConnectionId()}
	 */
	String getConnectionID();
	
	/**
	 * Get the next random double value 
	 * @return
	 */
	double getNextRand();
	
	/**
	 * Sets the seed value and returns the next random double value.  
	 * Additional calls to {@link #getNextRand()} will be based upon the seed value.
	 * @param seed
	 * @return
	 */
	double getNextRand(long seed);
	
	/**
	 * Get the processor batch size set on the BufferManager
	 * @return - the nominal batch size target.  actual batch sizes will vary based 
	 * upon the column types
	 */
	int getProcessorBatchSize();
	
	/**
	 * Get the server {@link TimeZone}
	 * @return
	 */
	TimeZone getServerTimeZone();
	
	/**
	 * Get the current subject
	 * @return
	 */
	Subject getSubject();

	/**
	 * Get the current session
	 * @return
	 */
	Session getSession();

	/**
	 * Get the current command payload
	 * @return may be null if the client did not set a payload
	 */
	Serializable getCommandPayload();

	/**
	 * Get the current request id 
	 * @return
	 */
	String getRequestId();
	
	/**
	 * Get the user's data policies, never null
	 * @return
	 */
	Map<String, DataPolicy> getAllowedDataPolicies();
	
	/**
	 * Get the current vdb
	 * @return
	 */
	VDB getVdb();
	
	/**
	 * Add a listener for command events
	 * @param listener
	 */
	void addListener(CommandListener listener);
	
	/**
	 * Add a listener for command events
	 * @param listener
	 */
	void removeListener(CommandListener listener);
	
	/**
	 * Get the number of times this command has been reused.  Useful 
	 * in continuous executions.
	 * @return
	 */
	long getReuseCount();
	
	/**
	 * Get class loader for VDB. 
	 * @return
	 */
	ClassLoader getVDBClassLoader();
	
	/**
     * Add an exception as a warning.  The exception will be wrapped by a {@link TeiidSQLWarning} for the client. 
     * The warnings can be consumed through the {@link Statement#getWarnings()} method.  
     * @param ex
     */
    void addWarning(Exception ex);

}
