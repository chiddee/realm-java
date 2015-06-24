/*
 * Copyright 2014 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm;

import io.realm.dynamic.RealmSchema;

/**
 * The RealmMigration class is used to describe the migration of one Realm schema to another.
 * The schema for a Realm is defined by all classes in a project that extend
 * {@link io.realm.RealmObject}, so any changes to these classes will require a migration.
 *
 * To support migrations from any previous schemaVersion to the newest, the following pattern is
 * recommended when writing a migration:
 *
 * <pre>
 * public class CustomMigration implements RealmMigration {
 *   \@Override
 *   public long execute(RealmSchema schema, long oldVersion, long newVersion) {

 *     if (oldVersion == 0) {
 *       // Migrate from v0 to v1
 *       oldVersion++;
 *     }
 *
 *     if (oldVersion == 1) {
 *       // Migrate from v1 to v2
 *       oldVersion++;
 *     }
 *   }
 * }
 * </pre>
 *
 * During development when model classes can change frequently, it is possible to use
 * {@link io.realm.Realm#deleteRealmFile(android.content.Context)}. This will delete the database
 * file and eliminate the need for any migrations.
 *
 * @see io.realm.RealmConfiguration.Builder#schemaVersion(int)
 * @see io.realm.RealmConfiguration.Builder#migration(RealmMigration)
 * @see io.realm.RealmConfiguration.Builder#deleteRealmIfMigrationNeeded()
 */
public interface RealmMigration {

    /**
     * Implement this method in your subclass to perform a migration.
     *
     * @param schema The Realm schema on which to perform the migration.
     * @param oldVersion The schema version of the Realm at the start of the migration.
     * @param newVersion The schema version of the Realm after executing the migration.
     */
    void migrate(RealmSchema schema, long oldVersion, long newVersion);
}

