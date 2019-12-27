package com.example.testapplication;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0) {
            schema.create("License")
                    .addField("licenseId", int.class)
                    .addField("licenseNumber", int.class);

            schema.create("Section")
                    .addField("sectionId",int.class,FieldAttribute.PRIMARY_KEY)
                    .addField("yearLevel", String.class)
                    .addField("sectionName",String.class);

            schema.create("Subject")
                    .addField("id", int.class)
                    .addField("subjectName", String.class);

            schema.create("Employee")
                    .addField("employeeId",int.class, FieldAttribute.PRIMARY_KEY)
                    .addField("name",String.class)
                    .addField("age", int.class)
                    .addField("address", String.class)
                    .addField("position", String.class)
                    .addRealmObjectField("license",schema.get("License"))
                    .addRealmListField("subjectList", schema.get("Subject"))
                    .addRealmListField("sectionList", schema.get("Section"));
        }
    }
}
