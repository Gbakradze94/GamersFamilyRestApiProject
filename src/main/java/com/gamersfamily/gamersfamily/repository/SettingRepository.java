package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.setting.Setting;
import com.gamersfamily.gamersfamily.model.setting.SettingCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SettingRepository extends CrudRepository<Setting,String> {
    List<Setting> findByCategory(SettingCategory category);

    Setting findByKey(String key);
}
