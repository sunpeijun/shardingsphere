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

package org.apache.shardingsphere.infra.metadata.schema.fixture.loader;

import org.apache.shardingsphere.infra.config.properties.ConfigurationProperties;
import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.infra.datanode.DataNodes;
import org.apache.shardingsphere.infra.metadata.schema.fixture.rule.DataNodeContainedFixtureRule;
import org.apache.shardingsphere.infra.metadata.schema.loader.spi.ShardingSphereMetaDataLoader;
import org.apache.shardingsphere.infra.metadata.schema.model.physical.PhysicalColumnMetaData;
import org.apache.shardingsphere.infra.metadata.schema.model.physical.PhysicalTableMetaData;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public final class DataNodeContainedFixtureLogicMetaDataLoader implements ShardingSphereMetaDataLoader<DataNodeContainedFixtureRule> {
    
    @Override
    public Optional<PhysicalTableMetaData> load(final String tableName, final DatabaseType databaseType, final Map<String, DataSource> dataSourceMap,
                                                final DataNodes dataNodes, final DataNodeContainedFixtureRule rule, final ConfigurationProperties props) {
        return ("data_node_routed_table_0".equals(tableName) || "data_node_routed_table_1".equals(tableName))
                ? Optional.of(new PhysicalTableMetaData(Collections.emptyList(), Collections.emptyList())) : Optional.empty();
    }
    
    @Override
    public PhysicalTableMetaData decorate(final String tableName, final PhysicalTableMetaData tableMetaData, final DataNodeContainedFixtureRule rule) {
        PhysicalColumnMetaData columnMetaData = new PhysicalColumnMetaData("id", 1, "INT", true, true, false);
        return new PhysicalTableMetaData(Collections.singletonList(columnMetaData), Collections.emptyList());
    }
    
    @Override
    public int getOrder() {
        return 1;
    }
    
    @Override
    public Class<DataNodeContainedFixtureRule> getTypeClass() {
        return DataNodeContainedFixtureRule.class;
    }
}
