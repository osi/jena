/*
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
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 */

package org.seaborne.tdb2.sys;

import org.apache.jena.atlas.lib.NotImplemented ;
import org.apache.jena.graph.Node ;
import org.apache.jena.query.Dataset ;
import org.apache.jena.sparql.core.DatasetGraph ;
import org.seaborne.tdb2.store.DatasetGraphTDB ;
import org.seaborne.tdb2.store.NodeId ;
import org.seaborne.tdb2.store.nodetable.NodeTable ;

/** A collection of helpers to abstract away from internal details of TDB. 
 * Use with care.
 */
public class TDBInternal
{
    /** Return the NodeId for a node.
     * Returns NodeId.NodeDoesNotExist when the node is not found. 
     * Returns null when not a TDB-backed dataset.
     */
    public static NodeId getNodeId(Dataset ds, Node node)
    {
        return  getNodeId(ds.asDatasetGraph(), node) ;
    }
    
    /** Return the NodeId for a node.
     * Returns NodeId.NodeDoesNotExist when the node is not found. 
     * Returns null when not a TDB-backed dataset.
     */
    public static NodeId getNodeId(DatasetGraph ds, Node node)
    {
        DatasetGraphTDB dsg = getDatasetGraphTDB(ds) ;
        return getNodeId(dsg, node) ;
    }
    
    /** Return the NodeId for a node.
     * Returns NodeId.NodeDoesNotExist when the node is not found. 
     * Returns null when not a TDB-backed dataset.
     */
    public static NodeId getNodeId(DatasetGraphTDB dsg, Node node)
    {
        if ( dsg == null )
            return null ;
        NodeTable nodeTable = dsg.getQuadTable().getNodeTupleTable().getNodeTable() ;
        NodeId nodeId = nodeTable.getNodeIdForNode(node) ;
        return nodeId ;
    }

    /** Return the node for a NodeId (if any).
     *  Returns null if the NodeId does not exist in the dataset. 
     */
    public static Node getNode(Dataset ds, NodeId nodeId)
    {
        return getNode(ds.asDatasetGraph(), nodeId) ;
    }
    
    /** Return the node for a NodeId (if any).
     *  Returns null if the NodeId does not exist in the dataset. 
     */
    public static Node getNode(DatasetGraph ds, NodeId nodeId)
    {
        DatasetGraphTDB dsg = getDatasetGraphTDB(ds) ;
        return getNode(dsg, nodeId) ;
    }

    /** Return the node for a NodeId (if any).
     *  Returns null if the NodeId does not exist in the dataset. 
     */
    public static Node getNode(DatasetGraphTDB dsg, NodeId nodeId)
    {
        if ( dsg == null )
            return null ;
        NodeTable nodeTable = dsg.getQuadTable().getNodeTupleTable().getNodeTable() ;
        Node node = nodeTable.getNodeForNodeId(nodeId) ;
        return node ;
    }

    /**
     * Return the DatasetGraphTDB for a Dataset, or null.
     */
    public static DatasetGraphTDB getDatasetGraphTDB(Dataset ds)
    {
        return getDatasetGraphTDB(ds.asDatasetGraph()) ;
    }

    
    /**
     * Return the DatasetGraphTDB for a DatasetGraph, or null.
     * May not be up-to-date.
     */
    public static DatasetGraphTDB getDatasetGraphTDB(DatasetGraph dsg)
    {
        throw new NotImplemented() ;
//        if ( dsg instanceof DatasetGraphTransaction )
//            // Latest.
//            return ((DatasetGraphTransaction)dsg).getDatasetGraphToQuery() ;
//            // Core.
//            //.getBaseDatasetGraph() ;
//        
//        if ( dsg instanceof DatasetGraphTDB )
//            return (DatasetGraphTDB)dsg ;
//        
//        return null ;
    }
    
    /** Return a DatasetGraphTDB that uses the raw storage for tables 
     * Use with care.
     */ 
    public static DatasetGraphTDB getBaseDatasetGraphTDB(DatasetGraph datasetGraph)
    {
        throw new NotImplemented() ;
//        if ( datasetGraph instanceof DatasetGraphTransaction )
//            return ((DatasetGraphTransaction)datasetGraph).getBaseDatasetGraph() ;
//        throw new TDBException("Not a suitable DatasetGraph to get it's base storage: "+Lib.classShortName(datasetGraph.getClass())) ; 
    }
}

