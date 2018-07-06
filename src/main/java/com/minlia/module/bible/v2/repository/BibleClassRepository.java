package com.minlia.module.bible.v2.repository;

import com.minlia.module.bible.v2.entity.BibleClass;
import com.minlia.module.data.jpa.abstraction.AbstractRepository;

/**
 * @author will
 */
public interface BibleClassRepository extends AbstractRepository<BibleClass, Long> {

  BibleClass findOneByPackageNameAndClassName(String packageName, String className);


}