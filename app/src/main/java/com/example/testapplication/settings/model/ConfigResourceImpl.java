package com.example.testapplication.settings.model;

import io.realm.Realm;

class ConfigResourceImpl implements ConfigResource {

    private Realm realm;

    public ConfigResourceImpl() {
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void saveConfig(final Config config) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(config);
            }
        });
    }

    @Override
    public Config getConfig() {
        Config config = realm.where(Config.class).findFirst();
        return realm.copyFromRealm(config);
    }
}
