/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.metadata.schema.refresh.impl;

import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.infra.metadata.schema.model.ShardingSphereSchema;
import org.apache.shardingsphere.infra.metadata.schema.refresh.MetaDataRefreshStrategy;
import org.apache.shardingsphere.infra.metadata.schema.refresh.TableMetaDataLoaderCallback;
import org.apache.shardingsphere.sql.parser.sql.common.statement.ddl.DropViewStatement;

import java.util.Collection;

/**
 * Drop view statement meta data refresh strategy.
 */
public final class DropViewStatementMetaDataRefreshStrategy implements MetaDataRefreshStrategy<DropViewStatement> {
    
    @Override
    public void refreshMetaData(final ShardingSphereSchema schema, final DatabaseType databaseType, final Collection<String> routeDataSourceNames,
                                final DropViewStatement sqlStatement, final TableMetaDataLoaderCallback callback) {
        sqlStatement.getViews().forEach(each -> removeMetaData(schema, each.getTableName().getIdentifier().getValue()));
    }
    
    private void removeMetaData(final ShardingSphereSchema schema, final String viewName) {
        schema.getSchemaMetaData().remove(viewName);
        schema.getTableAddressingMetaData().getTableDataSourceNamesMapper().remove(viewName);
    }
}
